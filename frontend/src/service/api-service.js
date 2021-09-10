import axios from 'axios'

export const getToken = credentials =>
  axios
    .post('api/shishaBuddies/auth/access-token', credentials)
    .then(response => response.data)
    .then(dto => dto.token)

const headers = token => ({
  headers: {
    Authorization: `Bearer ${token}`,
  },
})
