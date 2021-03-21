import Vue from "vue"
import Vuex from "vuex"
import axios from "axios"

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    userInfo: null,
    isLogin: false
  },
  mutations: {
    loginSuccess(state, payload) {
      state.isLogin = true
      state.userInfo = payload
    },
    logout(state) {
      state.isLogin = false
      state.userInfo = null
      localStorage.removeItem("access_token")
    }
  },
  actions: {
    getAccountInfo({ commit }) {
      let token = localStorage.getItem("access_token")
      axios
        .get("/userinfo", {
          headers: {
            "X-AUTH-TOKEN": token
          }
        })
        .then(response => {
          commit("loginSuccess", response.data.data)
        })
        .catch(error => {
          console.log(error)
        })
    }
  },
  modules: {}
})
