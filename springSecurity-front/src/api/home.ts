import service from "@/axios";

export function getTest() {
    return service({
        url: "/getTest",
        method: "GET",
    })
}

export function postTest() {
    return service({
        url: "/postTest",
        method: "POST",
    })
}

