import axios from "axios";
import router from "@/router";
import store from '../store'
import ElementUI from 'element-ui';
import userApi from "@/api/user";

axios.defaults.baseURL = 'http://localhost:1111/'
axios.defaults.headers.token = store.getters.GET_TOKEN

axios.interceptors.request
    .use((config) => {
        return config;
    }, (error => {
        return Promise.reject(error);
    }))

axios.interceptors.response
    .use((resp) => {
        const code = resp.data.code;
        if (code !== 1000 && code !== 2030) {
            ElementUI.Message.error(resp.data.message)
        }
        return resp;
    }, (error) => {
        return Promise.reject(error);
    })

router.beforeEach((to, from, next) => {
    let r = async () => {
        const fullPath = to.fullPath;
        if (fullPath === '/register') {
            next()
        } else {
            let resp = await userApi.tokenSign({
                token: store.getters.GET_TOKEN
            })

            const tokenSign = resp.data.data;

            if (tokenSign && fullPath === '/login') {
                next('/')
            } else {
                if (!tokenSign && fullPath !== '/login') {
                    next("/login")
                } else {
                    next()
                }
            }
        }
    }
    r().then(() => {
    })
})