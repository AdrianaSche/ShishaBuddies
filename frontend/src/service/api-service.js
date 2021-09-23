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

export const getSetupByTitle = (token, title) =>
  axios
    .get(`/api/shishaBuddies/user/setup/details/${title}`, headers(token))
    .then(response => response.data)

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

export const createSetup = (token, setup) =>
  axios
    .post('api/shishaBuddies/user/create-setup', setup, headers(token))
    .then(response => response.data)
    .then(dto => dto.setup)

export const getAllSetup = token =>
  axios
    .get('api/shishaBuddies/user/all-setups', headers(token))
    .then(response => response.data)
