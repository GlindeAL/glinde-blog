import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import Element from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import './assets/css/global.css'
import './assets/icon/iconfont.css'
import axios from 'axios'
import mavonEditer from 'mavon-editor'
import 'mavon-editor/dist/css/index.css'

Vue.prototype.$http = axios
axios.defaults.baseURL = "https://glinde.me:8443"
axios.defaults.timeout = 5000
axios.interceptors.request.use(
    config => {
      if (localStorage.getItem("token") != null) {
        config.headers["token"] = localStorage.getItem("token");
      }

      return config;
    },
    err => Promise.reject(err)
)

Vue.use(Element);
Vue.use(mavonEditer);

Vue.config.productionTip = false


Vue.prototype.checkAuth = async function checkAuth() {
  let {data:res} = await this.$http.get("/check")
  if(res.flag==="error"&&res.msg===-1){
    window.localStorage.removeItem("token")
    window.localStorage.removeItem("uid")
    window.localStorage.removeItem("username")
    await Vue.prototype.$router.push("/welcome")
  }
}

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
