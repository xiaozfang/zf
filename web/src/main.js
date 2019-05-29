// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import axios from 'axios'
import VueRouter from 'vue-router'
import iView from 'iview'
import 'iview/dist/styles/iview.css'

Vue.use(VueRouter);
Vue.use(iView);

Vue.config.productionTip = false

const instance = axios.create({
  baseURL: 'http://localhost:32001'
})
// **路由请求拦截**
instance.interceptors.request.use(request => {
  if (request.method === 'post') {
    // request.headers['Content-Type'] = 'application/x-www-form-urlencoded';
  }
  if (sessionStorage.getItem('Authorization')){
    request.headers.Authorization = sessionStorage.getItem('Authorization');
  }
  return request
})

// **路由响应拦截**
instance.interceptors.response.use(response => {
  if (response.status === 401){
    // 清除信息 跳转到登录页面
    sessionStorage.clear();
    window.location.href = 'http://localhost:8080'
  }
  return response
})

Vue.prototype.$http = instance

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: {App},
  template: '<App/>'
})
