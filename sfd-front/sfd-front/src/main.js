// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import Element from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import axios from 'axios'
import qs from 'qs'
import param from '@/common/param.js'
import req from '@/common/req.js'
import Config from '@/Config'
import moment from 'moment'
Vue.prototype.$moment = moment
moment.locale('zh-cn')

Vue.prototype.$param = param
Vue.prototype.$req = req
Vue.prototype.$qs = qs
Vue.prototype.$Config = Config
Vue.use(Element, axios)
Vue.prototype.$ajax = axios

Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
