import axios from "axios";

export default {
    login(user) {
        return axios.post('user/login', user)
    },
    register(user) {
        return axios.post('user/register', user)
    },
    tokenSign(token) {
        return axios.post('user/tokenSign', token)
    },
    captcha() {
        return axios.get('user/captcha')
    },
    captchaTtl(imageId) {
        return axios.post('user/captchaTtl', {
            imageId: imageId
        })
    }
}