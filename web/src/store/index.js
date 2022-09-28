import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        TOKEN: localStorage.getItem("token")
    },
    getters: {
        GET_TOKEN: state => {
            return state.TOKEN;
        }
    },
    mutations: {
        SET_TOKEN: (state, payload) => {
            state.TOKEN = payload;
            localStorage.setItem("token", payload);
        }
    },
    actions: {},
    modules: {}
})
