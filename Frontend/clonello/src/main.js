import "@babel/polyfill";
import "mutationobserver-shim";
import Vue from "vue";
import "./plugins/bootstrap-vue";
import App from "./App.vue";
import VeeValidate from 'vee-validate';
import { router } from "./_helpers";
import store from "./store";
import "@fortawesome/fontawesome-free/css/all.css";
import "@fortawesome/fontawesome-free/js/all.js";

Vue.config.productionTip = false;
Vue.use(VeeValidate);


new Vue({
  router,
  store,
  render: h => h(App)
}).$mount("#app");
