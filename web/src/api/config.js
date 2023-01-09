import axios from "axios";

export default {
    selectAllConfig() {
        return axios.get('config/list')
    }
}