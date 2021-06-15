import 'core-js/stable'
import Vue from 'vue'
import App from './App'
import router from './router'
import store from './store'
import CoreuiVue from '@coreui/vue'
import { iconsSet as icons } from './assets/icons/icons.js'
import babelPolyfill from 'babel-polyfill'
import 'bootstrap'
import 'bootstrap/dist/css/bootstrap.min.css'
import VeeValidate from 'vee-validate'
import { library } from '@fortawesome/fontawesome-svg-core'
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'
import {
  faHome, faUser, faUserPlus, faSignInAlt, faSignOutAlt
} from '@fortawesome/free-solid-svg-icons'
import axios from "axios";

library.add(faHome, faUser, faUserPlus, faSignInAlt, faSignOutAlt)


axios.interceptors.response.use(undefined, function(error) {
  if (error) {
    const originalRequest = error.config;
    if (error.response.status === 401 && !originalRequest._retry) {
      originalRequest._retry = true;
      store.dispatch("LogOut");
      return router.push("/login");
    }
  }
});



Vue.config.productionTip = false
Vue.component('font-awesome-icon', FontAwesomeIcon)

Vue.config.performance = true
Vue.use(CoreuiVue)
Vue.use(VeeValidate)
Vue.prototype.$log = console.log.bind(console)

new Vue({
  el: '#app',
  router,
  store,
  icons,
  template: '<App/>',
  components: {
    App
  }
})
