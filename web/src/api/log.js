import axios from "axios";

export default {
    getLogByTaskId(log) {
        return axios.post('log/getLogByTaskId', log)
    }
}