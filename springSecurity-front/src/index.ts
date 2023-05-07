// axios配置  可自行根据项目进行更改，只需更改该文件即可，其他文件可以不动
// The axios configuration can be changed according to the project, just change the file, other files can be left unchanged

import type { AxiosResponse } from 'axios';
import type { RequestOptions, Result } from './types';
import type { AxiosTransform, CreateAxiosOptions } from './axiosTransform';

import { VAxios } from './Axios';
import { checkStatus } from './checkStatus';

import { useGlobSetting } from '/@/hooks/setting';
import { useMessage } from '/@/hooks/web/useMessage';

import { RequestEnum, ResultEnum, ContentTypeEnum } from '/@/enums/httpEnum';

import { isString } from '/@/utils/is';
import { getToken } from '/@/utils/auth';
import { useLocale } from '/@/locales/useLocale';
import { setObjToUrlParams, deepMerge } from '/@/utils';
import { useErrorLogStoreWithOut } from '/@/store/modules/errorLog';

import { errorResult } from './const';
import { useI18n } from '/@/hooks/web/useI18n';
import { createNow, formatRequestDate } from './helper';
import { Api } from '/@/api/system/user';
import router from '/@/router';
import { PageEnum } from '/@/enums/pageEnum';

const globSetting = useGlobSetting();
const prefix = globSetting.urlPrefix;
const { createMessage, createErrorModal } = useMessage();

/**
 * @description: 数据处理，方便区分多种处理方式
 */
const transform: AxiosTransform = {
  /**
   * @description: 处理请求数据
   */
  transformRequestHook: (res: AxiosResponse<Result>, options: RequestOptions) => {
    const { t } = useI18n();
    const { isTransformRequestResult, isRequestResultHeader } = options;
    // 不进行任何处理，直接返回
    // 用于页面代码可能需要直接获取code，data，message这些信息时开启
    if (!isTransformRequestResult) {
      if (isRequestResultHeader) {
        return {
          data: res.data,
          headers: res.headers,
        };
      }
      return res.data;
    }
    // 错误的时候返回

    const resData = res.data;
    if (!resData) {
      // return '[HTTP] Request has no return value';
      return errorResult;
    }
    //  这里 code，data，message为 后台统一的字段，需要在 types.ts内修改为项目自己的接口返回格式
    const { code, data, message } = resData;

    // 这里逻辑可以根据项目进行修改
    const hasSuccess = resData && Reflect.has(resData, 'code') && code === ResultEnum.SUCCESS;
    if (!hasSuccess) {
      if (message) {
        // errorMessageMode=‘modal’的时候会显示modal错误弹窗，而不是消息提示，用于一些比较重要的错误
        if (options.errorMessageMode === 'modal') {
          createErrorModal({ title: t('sys.api.errorTip'), content: message });
        } else if (options.errorMessageMode === 'message') {
          createMessage.error(message);
        }
      }
      Promise.reject(new Error(message));
      return errorResult;
    }

    // 接口请求成功，直接返回结果
    if (code == ResultEnum.SUCCESS) {
      return data;
    }
    // token失效
    if (code == ResultEnum.TOKEN_ERROR) {
      // userStore.refreshToken;
    }
    // 接口请求错误，统一提示错误信息
    if (code == ResultEnum.BUSINESS_ERROR) {
      if (message) {
        createMessage.error(resData.message);
        Promise.reject(new Error(message));
      } else {
        const msg = t('sys.api.errorMessage');
        createMessage.error(msg);
        Promise.reject(new Error(msg));
      }
      return errorResult;
    }
    // 登录超时
    if (code === ResultEnum.TIMEOUT) {
      const timeoutMsg = t('sys.api.timeoutMessage');
      createErrorModal({
        title: t('sys.api.operationFailed'),
        content: timeoutMsg,
      });
      Promise.reject(new Error(timeoutMsg));
      return errorResult;
    }
    return errorResult;
  },

  // 请求之前处理config
  beforeRequestHook: (config, options) => {
    const { apiUrl, joinPrefix, joinParamsToUrl, formatDate, joinTime = true } = options;

    if (joinPrefix) {
      config.url = `${prefix}${config.url}`;
    }

    if (apiUrl && isString(apiUrl)) {
      config.url = `${apiUrl}${config.url}`;
    }
    const params = config.params || {};
    if (config.method?.toUpperCase() === RequestEnum.GET) {
      if (!isString(params)) {
        // 给 get 请求加上时间戳参数，避免从缓存中拿数据。
        config.params = Object.assign(params || {}, createNow(joinTime, false));
      } else {
        // 兼容restful风格
        config.url = config.url + params + `${createNow(joinTime, true)}`;
        config.params = undefined;
      }
    } else {
      if (!isString(params)) {
        formatDate && formatRequestDate(params);
        config.data = params;
        config.params = undefined;
        if (joinParamsToUrl) {
          config.url = setObjToUrlParams(config.url as string, config.data);
        }
      } else {
        // 兼容restful风格
        config.url = config.url + params;
        config.params = undefined;
      }
    }
    return config;
  },

  /**
   * @description: 请求拦截器处理
   */
  requestInterceptors: (config) => {
    // 请求之前处理config
    const token = getToken();
    const { getLocale } = useLocale();
    // 1-pc，2-小程序
    config.headers.portalType = 1;
    // 登录和验证码请求的接口不需要传token
    if (config.url !== Api.Login && config.url !== Api.GetLoginVerifyCodeImg) {
      if (token && config.url !== '/upload/im/oss/uploadFile') {
        // jwt token
        config.headers.Authorization = 'bearer ' + token;
      }
    }
    if (getLocale) {
      config.headers['Accept-Language'] = getLocale.value;
    }
    return config;
  },

  /**
   * @description: 响应错误处理
   */
  responseInterceptorsCatch: async (error: any) => {
    const { t } = useI18n();
    const errorLogStore = useErrorLogStoreWithOut();
    errorLogStore.addAjaxErrorInfo(error);
    const { response, code, message } = error || {};
    const res = response?.data;
    let blobMsg = '';
    if (response?.config?.responseType === 'blob') {
      const enc = new TextDecoder('utf-8');
      await res.arrayBuffer().then((buffer) => {
        blobMsg =
          (JSON.parse(enc.decode(new Uint8Array(buffer))) || {})?.message ||
          t('sys.api.errMsgBlob');
      });
    }
    const msg: string =
      response?.config?.responseType === 'blob'
        ? blobMsg
        : response?.data?.message ?? t('sys.api.errMsgJson');
    const err: string = error?.toString?.() ?? '';
    try {
      if (code === 'ECONNABORTED' && message.indexOf('timeout') !== -1) {
        createMessage.error(t('sys.api.apiTimeoutMessage'));
      }
      if (
        err?.includes('Network Error') ||
        err?.includes('Error: Request failed with status code 401')
      ) {
        createErrorModal({
          title: t('sys.api.errMsg401'),
          content: t('sys.api.errMsg401'),
        });
      }
    } catch (error) {
      throw new Error(error);
    }
    checkStatus(error?.response?.status, msg);
    if (error?.response?.status == 401) {
      return router.push(PageEnum.BASE_LOGIN);
    }
    return Promise.reject(error);
  },
};

function createAxios(opt?: Partial<CreateAxiosOptions>) {
  return new VAxios(
    deepMerge(
      {
        timeout: 20 * 1000,
        // 基础接口地址
        // baseURL: globSetting.apiUrl,
        // 接口可能会有通用的地址部分，可以统一抽取出来
        prefixUrl: prefix,
        headers: { 'Content-Type': ContentTypeEnum.JSON },
        // 如果是form-data格式
        // headers: { 'Content-Type': ContentTypeEnum.FORM_URLENCODED },
        // 数据处理方式
        transform,
        // 配置项，下面的选项都可以在独立的接口请求中覆盖
        requestOptions: {
          // 默认将prefix 添加到url
          joinPrefix: true,
          // 需要对返回数据进行处理
          isTransformRequestResult: true,
          // post请求的时候添加参数到url
          joinParamsToUrl: false,
          // 格式化提交参数时间
          formatDate: true,
          // 消息提示类型
          errorMessageMode: 'message',
          // 接口地址
          apiUrl: globSetting.apiUrl,
          //  是否加入时间戳
          joinTime: true,
          // 忽略重复请求
          ignoreCancelToken: true,
        },
      },
      opt || {}
    )
  );
}

function createBlobAxios(opt?: Partial<CreateAxiosOptions>) {
  return new VAxios(
    deepMerge(
      {
        timeout: 60 * 1000,
        // 基础接口地址
        // baseURL: globSetting.apiUrl,
        // 接口可能会有通用的地址部分，可以统一抽取出来
        prefixUrl: prefix,
        headers: { 'Content-Type': ContentTypeEnum.JSON },
        responseType: 'blob',
        // 数据处理方式
        transform,
        // 配置项，下面的选项都可以在独立的接口请求中覆盖
        requestOptions: {
          // 默认将prefix 添加到url
          joinPrefix: true,
          // 需要对返回数据进行处理
          isTransformRequestResult: false,
          // post请求的时候添加参数到url
          joinParamsToUrl: false,
          // 格式化提交参数时间
          formatDate: true,
          // 消息提示类型
          errorMessageMode: 'message',
          // 接口地址
          apiUrl: globSetting.apiUrl,
          //  是否加入时间戳
          joinTime: true,
          // 忽略重复请求
          ignoreCancelToken: true,
        },
      },
      opt || {}
    )
  );
}

function createBlobDocAxios(opt?: Partial<CreateAxiosOptions>) {
  return new VAxios(
    deepMerge(
      {
        timeout: 60 * 1000,
        // 基础接口地址
        // baseURL: globSetting.apiUrl,
        // 接口可能会有通用的地址部分，可以统一抽取出来
        prefixUrl: prefix,
        headers: { 'Content-Type': ContentTypeEnum.JSON },
        responseType: 'blob',
        // 数据处理方式
        transform,
        // 配置项，下面的选项都可以在独立的接口请求中覆盖
        requestOptions: {
          // 默认将prefix 添加到url
          joinPrefix: true,
          // 需要对返回数据进行处理
          isTransformRequestResult: false,
          // 需要返回请求头
          isRequestResultHeader: true,
          // post请求的时候添加参数到url
          joinParamsToUrl: false,
          // 格式化提交参数时间
          formatDate: true,
          // 消息提示类型
          errorMessageMode: 'message',
          // 接口地址
          apiUrl: globSetting.apiUrl,
          //  是否加入时间戳
          joinTime: true,
          // 忽略重复请求
          ignoreCancelToken: true,
        },
      },
      opt || {}
    )
  );
}

function createUploadAxios(opt?: Partial<CreateAxiosOptions>) {
  return new VAxios(
    deepMerge(
      {
        timeout: 60 * 1000,
        // 基础接口地址
        // baseURL: globSetting.apiUrl,
        // 接口可能会有通用的地址部分，可以统一抽取出来
        prefixUrl: prefix,
        headers: { 'Content-Type': ContentTypeEnum.JSON },
        // 如果是form-data格式
        // headers: { 'Content-Type': ContentTypeEnum.FORM_URLENCODED },
        // 数据处理方式
        transform,
        // 配置项，下面的选项都可以在独立的接口请求中覆盖
        requestOptions: {
          // 默认将prefix 添加到url
          joinPrefix: true,
          // 需要对返回数据进行处理
          isTransformRequestResult: true,
          // post请求的时候添加参数到url
          joinParamsToUrl: false,
          // 格式化提交参数时间
          formatDate: true,
          // 消息提示类型
          errorMessageMode: 'message',
          // 接口地址
          apiUrl: globSetting.apiUrl,
          //  是否加入时间戳
          joinTime: true,
          // 忽略重复请求
          ignoreCancelToken: true,
        },
      },
      opt || {}
    )
  );
}

// 默认请求
export const defHttp = createAxios();

// 不返回请求头
export const defHttpV = createBlobAxios();

// 返回请求头
export const defHttpD = createBlobDocAxios();

// 文件上传请求
export const defUploadHttp = createUploadAxios();

// other api url
// export const otherHttp = createAxios({
//   requestOptions: {
//     apiUrl: 'xxx',
//   },
// });