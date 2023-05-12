//我要用到的一些接口
import service from "../axios";

// 登录接口
export function login(data: unknown) {
    return service({
        url: "/user/login",
        method: "POST",
        headers: {
            Authorization: null,
        },
        data
    })
}

// 登出接口
export function logout(data: unknown) {
    return service({
        url: "/user/logout",
        method: "GET",
        data
    })
}