<template>
  <div class="login">
    <div class="login-header">
      <h1>登&nbsp;陆</h1>
    </div>
    <form class="login-form">
      <div class="form-group">
        <label for="username">用户名</label>
        <input type="text" id="username" v-model="username"/>
      </div>
      <div class="form-group">
        <label for="password">密&nbsp;&nbsp;&nbsp;&nbsp;码</label>
        <input type="password" id="password" v-model="password"/>
      </div>
      <div class="form-group">
        <button class="login-button" @click.prevent="login">登&nbsp;陆</button>
      </div>
    </form>
  </div>
</template>

<script>
import {login} from "@/api/login";
import router from "@/router";

export default {
    data() {
        return {
            username: '',
            password: ''
        };
    },
    methods: {
        login: function () {
            const loginForm = {"username": this.username, "password": this.password};
            login(loginForm).then(response => {
                //使用 localStorage.setItem，将token保存到localStorage
                localStorage.setItem("token", response.data.token);
                // 并且跳转到首页
                router.push('/')
            }).catch(error => {
                console.error(error);
            })
        }


    }
};
</script>

<style scoped>
.login {
    height: 100vh;
    background: url('@/assets/background.jpeg');
    background-size: cover;
    display: flex;
    justify-content: center;
    align-items: center;
}

.login-header {
    display: flex;
    justify-content: center;
    align-items: center;
    width: 100%;
    height: 120px;
    background: linear-gradient(135deg, #43cea2 0%, #185a9d 100%);
    box-shadow: 0 0 20px rgba(0, 0, 0, 0.2);
}

h1 {
    margin: 0;
    font-size: 36px;
    font-weight: bold;
    color: #fff;
}

.login-form {
    width: 350px;
    padding: 40px;
    background-color: #fff;
    border-radius: 5px;
    box-shadow: 0 5px 10px rgba(0, 0, 0, 0.2);
}

.form-group {
    margin-bottom: 20px;
}

label {
    display: block;
    font-size: 18px;
    font-weight: bold;
    margin-bottom: 10px;
    color: #333;
}

input[type='text'],
input[type='password'] {
    width: 100%;
    padding: 10px;
    font-size: 16px;
    border: none;
    background-color: #f5f5f5;
    border-radius: 5px;
    box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
}

.login-button {
    display: block;
    width: 100%;
    padding: 10px;
    font-size: 18px;
    font-weight: bold;
    color: #fff;
    background-color: #43cea2;
    border: none;
    border-radius: 5px;
    box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
    cursor: pointer;
    transition: all 0.3s ease;
}

.login-button:hover {
    background-color: #185a9d;
}
</style>
