import axios from "axios";

export default {
    getConfig() {
        return axios.get('config/getConfig')
    },
    insertOrUpdateConfig(config) {
        return axios.post('config/insertOrUpdateConfig', config)
    }
}