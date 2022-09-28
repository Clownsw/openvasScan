import axios from "axios";

export default {
    createTask(task) {
        return axios.post(`task/createTask`, task)
    },
    taskList(currentPage, pageSize) {
        return axios.get(`task/taskList?currentPage=${currentPage}&pageSize=${pageSize}`)
    },
    autoViewSupportList() {
        return axios.get('task/autoViewSupportList')
    }
}