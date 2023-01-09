import axios from "axios";
import store from '../store'
import ElementUI from 'element-ui';

axios.defaults.baseURL = 'http://192.166.0.92:1111/'
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