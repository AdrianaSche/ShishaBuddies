import TextField from '../component/TextField'
import Page from '../component/Page'
import Header from '../component/Header'
import Main from '../component/Main'
import { useState } from 'react'
import { createUser } from '../service/api-service'

const initialState = {
  lastName: '',
  firstName: '',
  userName: '',
  email: '',
  password: '',
}
export default function RegisterBasic() {
  const [userdata, setUserdata] = useState(initialState)

  function handleSubmit(event) {
    event.preventDefault()
    createUser(userdata).catch(error => console.error(error))
  }

  const handleUserdataChange = event =>
    setUserdata({ ...userdata, [event.target.name]: event.target.value })

  const handleCancel = () => setUserdata(initialState)

  return (
    <Page>
      <Header title="Registrierung (Basis)" />
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
        <button>registrieren</button>
        <button onClick={handleCancel}>cancel</button>
        <button> erweiterte Profileinstellungen</button>
      </Main>
    </Page>
  )
}
//"erweiterte Profileinstellungen" müssen ausgegraut/nicht nutzbar,
//solange die Basics nicht ausgefüllt sind!
