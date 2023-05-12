import axios from "axios";
import Message from "@/components/message";

//创建axios实例
const service = axios.create({
    baseURL: "http://localhost:8080",
    timeout: 5000,//超时时间
    headers: {//编译语言
        "Content-type": "application/json;charset=utf-8"
    }
})

// 请求拦截：请求接口的时候，先拦截下来，对你的数据做一个判断，或者携带个token给你
service.interceptors.request.use((config) => {
    if (config.headers.Authorization !== null) { // 只有Authorization不为null才添加token
        if (localStorage.getItem("token")) {
            config.headers.Authorization = `Bearer ${localStorage.getItem("token")}`;
        }
    }
    return config;
}, error => {
    return Promise.reject(error);
});

//响应拦截：后端返回来的结果
service.interceptors.response.use((res) => {
        const code: string = res.data.code; // code 是后端的状态码
        switch (code) {
            case "0000":
                return Promise.resolve(res.data);
            case "0001":
                Message.error(res.data.message);
                return Promise.reject(res.data.message);
            case "0002":
                // 退出到登陆界面
                localStorage.removeItem("token");
                localStorage.removeItem("userInfo");
                window.location.href = "/login";
                Message.warning(res.data.message);
                return Promise.reject(res.data.message);
            default:
                return Promise.reject(res);
        }
    },
    (err) => {
        // 处理错误响应
        return Promise.reject(err);
    }
);

//因为别的地方要用，所以就把实例暴露出去，导出
export default service