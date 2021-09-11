import axios from 'axios'

export const getToken = credentials =>
  axios
    .post('api/shishaBuddies/auth/access-token', credentials)
    .then(response => response.data)
    .then(dto => dto.token)

export const createUser = user =>
  axios
    .post('api/shishaBuddies/user/new-user', user)
    .then(response => response.data)
    .then(dto => dto.user)

/*const headers = token => ({
  headers: {
    Authorization: `Bearer ${token}`,
  },
})*/
