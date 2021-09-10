import TextField from '../component/TextField'
import Page from '../component/Page'
import Header from '../component/Header'
import Main from '../component/Main'
import { useState } from 'react'

const initialState = {
  lastname: '',
  firstname: '',
  email: '',
  password: '',
}
export default function RegisterBasic() {
  const [userdata, setUserdata] = useState(initialState)

  function handleSubmit(event) {
    event.preventDefault()
    //post to backend
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
          name="lastname"
          value={userdata.lastname}
          onChange={handleUserdataChange}
        />
        <TextField
          title="Vorname"
          name="firstname"
          value={userdata.firstname}
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
