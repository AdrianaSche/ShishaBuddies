import TextField from '../component/TextField'
import Page from '../component/Page'
import Header from '../component/Header'
import Main from '../component/Main'
import { useState } from 'react'
import { createUser } from '../service/api-service'
import { Redirect } from 'react-router-dom'
import { useAuth } from '../auth/AuthProvider'
import Button from '../component/Button'
import CancelButton from '../component/CancelButton'
import Error from '../component/Error'

const initialState = {
  lastName: '',
  firstName: '',
  userName: '',
  email: '',
  password: '',
}
export default function RegisterBasic() {
  const [userdata, setUserdata] = useState(initialState)
  const { token, login } = useAuth()
  const [error, setError] = useState()
  function handleSubmit(event) {
    event.preventDefault()
    createUser(userdata)
      .then(() => login(userdata).catch(setError))
      .catch(setError)
    // .finally(() => login(userdata).catch(setError))
  }

  if (token) {
    return <Redirect to="/" />
  }
  const handleUserdataChange = event =>
    setUserdata({ ...userdata, [event.target.name]: event.target.value })

  const handleCancel = () => setUserdata(initialState)

  return (
    <Page>
      <Header title="Registrierung" />
      <Main as="form" onSubmit={handleSubmit}>
        <TextField
          title="Nachname"
          name="lastName"
          value={userdata.lastName}
          onChange={handleUserdataChange}
        />
        <TextField
          title="Vorname"
          name="firstName"
          value={userdata.firstName}
          onChange={handleUserdataChange}
        />
        <TextField
          title="Username"
          name="userName"
          value={userdata.userName}
          onChange={handleUserdataChange}
        />
        <TextField
          title="Email"
          name="email"
          value={userdata.email}
          onChange={handleUserdataChange}
        />
        <TextField
          title="Password"
          name="password"
          value={userdata.password}
          onChange={handleUserdataChange}
        />
        <Button>registrieren</Button>
        <CancelButton onClick={handleCancel}>cancel</CancelButton>
      </Main>
      {error && <Error>{error.response.data.message}</Error>}
    </Page>
  )
}
