import axios from "axios";

export default {
    createTask(task) {
        return axios.post(`task/createTask`, task)
    },
    taskList() {
        return axios.get(`task/list`)
    }
}