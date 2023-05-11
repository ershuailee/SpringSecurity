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

export function test() {
    return service({
        url: "/test",
        method: "GET",
    })
}