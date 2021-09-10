import { useState } from 'react'
import styled from 'styled-components/macro'
import Page from '../component/Page'
import Header from '../component/Header'
import Main from '../component/Main'
import { getToken } from '../service/api-service'
import TextField from '../component/TextField'
import { Redirect } from 'react-router-dom'
import { useAuth } from '../auth/AuthProvider'

const initialState = {
  username: '',
  password: '',
}

export default function Login() {
  const { login, user } = useAuth()
  const [credentials, setCredentials] = useState(initialState)
  console.log(credentials)

  const handleCredentialsChange = event =>
    setCredentials({ ...credentials, [event.target.name]: event.target.value })

  const handleCancel = () => setCredentials(initialState)

  function handleSubmit(event) {
    event.preventDefault()
    login(credentials).catch(error => console.error(error))
  }

  if (user) {
    return <Redirect to="/" />
  }
  return (
    <Page>
      <Header title="Login" />
      <Main as="form" onSubmit={handleSubmit}>
        <TextField
          title="Username"
          name="username"
          value={credentials.username}
          onChange={handleCredentialsChange}
        />
        <TextField
          title="Password"
          name="password"
          value={credentials.password}
          onChange={handleCredentialsChange}
        />

        <button>log' mich ein!</button>
        <button type="button" onClick={handleCancel}>
          cancel
        </button>
      </Main>
    </Page>
  )
}

const Wrapper = styled.form`
  display: grid;
`
