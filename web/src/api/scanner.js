import axios from "axios";

export default {
    selectAllScanner() {
        return axios.get('scanner/list')
    }
}