/* Copyright 2026 Shanghai Rujing Zhihua Information Technology Co., Ltd. */
import axios from 'axios'
const http=axios.create({baseURL:import.meta.env.VITE_API_BASE_URL||'/api',timeout:10000})
http.interceptors.request.use(c=>{const token=localStorage.getItem('zhuatech-csm-token');if(token)c.headers.Authorization=`Bearer ${token}`;return c})
export default http
