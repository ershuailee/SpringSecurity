import './assets/main.css'

import { createApp } from 'vue'
import { createPinia } from 'pinia'

import App from './App.vue'
import router from './router'
import axios from 'axios'

// 全局后端请求接口
axios.defaults.baseURL = 'http://localhost:8080'



createApp(App).use(createPinia()).use(router).mount('#app')