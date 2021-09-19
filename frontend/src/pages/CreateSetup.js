import Header from '../component/Header'
import Page from '../component/Page'
import { useAuth } from '../auth/AuthProvider'
import Main from '../component/Main'
import TextField from '../component/TextField'
import Button from '../component/Button'
import Navbar from '../component/Navbar'
import { useState } from 'react'
import Avatar from '../component/Avatar'
import { Redirect } from 'react-router-dom'

const initialSetup = {
  hookah: '',
  tobacco: '',
  head: '',
  carbon: '',
  carbonTop: '',
  accessories: '',
  avatar: '',
}

//was der user eingibt--> funktioniert nur, wenn die DB im Backend gefixt ist
export default function CreateSetup() {
  const { token, user } = useAuth()
  const [setup, setSetup] = useState(initialSetup)
  const [redirectToSetupDetails, setRedirectToSetupDetails] = useState(false)

  const handleSetupChange = event =>
    setSetup({ ...setup, [event.target.name]: event.target.value })
  const handleCancel = () => setSetup(initialSetup)

  function handleSubmit(event) {
    event.preventDefault()
    setRedirectToSetupDetails(true)
    //post to backend:
    //createSetup(token,setup).then(setReDirectToYourSetup(true)).catch(error => console.error(error))
    console.log(setup)
  }

  if (redirectToSetupDetails) {
    return <Redirect to="/setup_details" />
  }

  return (
    <Page>
      <Header title="Was rauchst Du gerade?" />
      <Main as="form" onSubmit={handleSubmit}>
        <TextField
          title="Shisha:"
          name="hookah"
          value={setup.hookah}
          onChange={handleSetupChange}
        />
        <TextField
          title="Tabaksorte:"
          name="tobacco"
          value={setup.tobacco}
          onChange={handleSetupChange}
        />
        <TextField
          title="Kopf:"
          name="head"
          value={setup.head}
          onChange={handleSetupChange}
        />
        <TextField
          title="Kohle:"
          name="carbon"
          value={setup.carbon}
          onChange={handleSetupChange}
        />
        <TextField
          title="Aufsatz:"
          name="carbonTop"
          value={setup.carbonTop}
          onChange={handleSetupChange}
        />
        <TextField
          title="Zubehör:"
          name="accessories"
          value={setup.accessories}
          onChange={handleSetupChange}
        />
        <Avatar src="https://thispersondoesnotexist.com/image" alt="bild" />
        />
        <Button>speichern</Button>
        <Button onClick={handleCancel}>cancel</Button>
      </Main>
      <Navbar user={user} />
    </Page>
  )
}
