import axios from '../api/axios'

const employeeService = {
  getAll() {
    return axios.get('/Employee')
  },

  getById(id) {
    return axios.get(`/Employee/${id}`)
  },

  create(data) {
    return axios.post('/Employee', data)
  },

  update(id, data) {
    return axios.put(`/Employee/${id}`, data)
  },

  delete(id) {
    return axios.delete(`/Employee/${id}`)
  },

  capPhat(empId, deviceId) {
    return axios.post(`/Employee/${empId}/cap-phat/${deviceId}`)
  },

  search(keyword) {
    return axios.get('/Employee/search', {
      params: {
        name: keyword
      }
    })
  }
}

export default employeeService