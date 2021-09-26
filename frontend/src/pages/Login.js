import { useState } from 'react'
import Page from '../component/Page'
import Header from '../component/Header'
import Main from '../component/Main'
import TextField from '../component/TextField'
import { Redirect, useHistory } from 'react-router-dom'
import { useAuth } from '../auth/AuthProvider'
import Button from '../component/Button'
import CancelButton from '../component/CancelButton'
import styled from 'styled-components/macro'
import ButtonGroup from '../component/ButtonGroup'
import Error from '../component/Error'
import Loading from '../component/Loading'
import Navbar from '../component/Navbar'

const initialState = {
  userName: '',
  password: '',
}

export default function Login(props) {
  const { login, user } = useAuth()
  const [credentials, setCredentials] = useState(initialState)
  const [loading, setLoading] = useState(false)
  const [error, setError] = useState()
  const history = useHistory()
  console.log(credentials)

  const handleCredentialsChange = event =>
    setCredentials({ ...credentials, [event.target.name]: event.target.value })

  const handleCancel = () => setCredentials(initialState)

  const handleSubmit = event => {
    event.preventDefault()
    setLoading(true)
    setError()
    login(credentials).catch(error => {
      setError(error)
      setLoading(false)
    })
  }

  if (user) {
    return <Redirect to="/" />
  }

  const handleRegister = () => {
    const path = '/register'
    history.push(path)
  }

  return (
    <Page>
      <Header title="Willkommen zu Shisha Buddies!" />
      {loading && <Loading />}
      {!loading && (
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

          <ButtonGroup>
            <Button> einloggen</Button>
            <Button type="button" onClick={handleRegister}>
              registrieren
            </Button>
            <CancelButton type="button" onClick={handleCancel}>
              cancel
            </CancelButton>
          </ButtonGroup>
        </Main>
      )}
      {error && <Error>{error.message}</Error>}
      <Navbar user={user} />
    </Page>
  )
}
