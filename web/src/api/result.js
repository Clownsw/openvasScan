import axios from "axios";

export default {
    selectResultById(taskId) {
        return axios.get(`result/one?taskId=${taskId}`)
    }
}