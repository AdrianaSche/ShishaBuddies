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

const headers = token => ({
  headers: {
    Authorization: `Bearer ${token}`,
  },
})

export const createSettings = (token, settings) =>
  axios
    .post('api/shishaBuddies/user/settings', settings, headers(token))
    .then(response => response.data)
    .then(dto => dto.settings)

export const getSettings = token =>
  axios
    .get('api/shishaBuddies/user/user-settings', headers(token))
    .then(response => response.data)

export const updateSettings = (newSettings, token) =>
  axios
    .put('api/shishaBuddies/user/update-settings', newSettings, headers(token))
    .then(response => response.data)

export const createSetup = (setup, token) =>
  axios
    .post('api/shishaBuddies/setup/create', setup, headers(token))
    .then(response => response.data)

/*export const getSetups= (token) =>
  axios.get()*/

export const getSetup = token =>
  axios
    .get('api/shishaBuddies/setup', headers(token))
    .then(response => response.data)
