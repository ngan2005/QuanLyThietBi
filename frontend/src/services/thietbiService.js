import { Search } from 'lucide-vue-next'
import axios from '../api/axios'

const thietbiService = {
  getAll() {
    return axios.get('/devices')
  },

  getById(id) {
    return axios.get(`/devices/${id}`)
  },

  create(data) {
    return axios.post('/devices', data)
  },

  update(id, data) {
    return axios.put(`/devices/${id}`, data)
  },

  delete(id) {
    return axios.delete(`/devices/${id}`)
  },
  search(keyword) {
    return axios.get('/devices/search', {
      params: {
        keyword: keyword
      }
    })
  }
}

export default thietbiService