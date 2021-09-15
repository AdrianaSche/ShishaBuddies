import { useState } from 'react'
import Page from '../component/Page'
import Header from '../component/Header'
import Main from '../component/Main'
import TextField from '../component/TextField'
import { Link, Redirect } from 'react-router-dom'
import { useAuth } from '../auth/AuthProvider'
import RegisterBasic from './RegisterBasic'

const initialState = {
  userName: '',
  password: '',
}

export default function Login(props) {
  const { login, user } = useAuth()
  const [credentials, setCredentials] = useState(initialState)
  console.log(credentials)

  const handleCredentialsChange = event =>
    setCredentials({ ...credentials, [event.target.name]: event.target.value })

  const handleCancel = () => setCredentials(initialState)

  const handleSubmit = event => {
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
          name="userName"
          value={credentials.userName}
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
