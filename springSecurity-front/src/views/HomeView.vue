<template>
  <main>
    <RouterLink to="/login">登陆</RouterLink>
    <button @click="logoutFunction">登出</button>
    <button @click="getTestFunction">GET测试</button>
    <button @click="postTestFunction">POST测试</button>
  </main>
</template>

<script lang="ts">
import Message from "@/components/message";
import {getTest, postTest} from "@/api/home";
import {logout} from "@/api/login";

export default {
    name: 'HomeView',
    setup() {

        function logoutFunction(): void {
            localStorage.removeItem("token");
            localStorage.removeItem("userInfo");
            window.location.href = "/login";
            Message.warning("退出成功");
            logout(localStorage.getItem("userId")).then(response => {
                console.log(response);
            }).catch(error => {
                console.error(error);
            })
        }

        function getTestFunction(): void {
            getTest().then(response => {
                console.log(response);
            }).catch(error => {
                console.error(error);
            })
        }

        function postTestFunction(): void {
            postTest().then(response => {
                console.log(response);
            }).catch(error => {
                console.error(error);
            })
        }

        return {
            getTestFunction,
            postTestFunction,
            logoutFunction,
        };
    },
};
</script>