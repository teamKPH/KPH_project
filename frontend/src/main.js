import Vue from "vue"
import App from "./App.vue"
import router from "./router"
import store from "./store"
import vuetify from "./plugins/vuetify"
import axios from "axios"

Vue.config.productionTip = false

axios.defaults.baseURL = "http://localhost:5000/api"

new Vue({
  router,
  store,
  vuetify,
  beforeCreate() {
    if (localStorage.getItem("access_token") !== null) {
      this.$store.dispatch("getAccountInfo")
    }
  },
  render: h => h(App)
}).$mount("#app")
