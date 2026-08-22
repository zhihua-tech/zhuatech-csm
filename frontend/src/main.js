/* Copyright 2026 Shanghai Rujing Zhihua Information Technology Co., Ltd. · https://www.zhuatech.cn/ */
import {createApp} from 'vue'; import {createPinia} from 'pinia'; import App from './App.vue'; import router from './router'; import './assets/main.css';
createApp(App).use(createPinia()).use(router).mount('#app')
