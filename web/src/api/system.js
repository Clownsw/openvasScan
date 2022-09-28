import axios from "axios";

export default {
    systemTest() {
        return axios.get('system/test')
    },
    getSysConfig() {
        return axios.get('system/getSysConfig')
    },
    updateSysConfig(sysConfig) {
        return axios.post('system/updateSysConfig', sysConfig)
    }
}