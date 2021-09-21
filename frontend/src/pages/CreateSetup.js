import Header from '../component/Header'
import Page from '../component/Page'
import { useAuth } from '../auth/AuthProvider'
import Main from '../component/Main'
import TextField from '../component/TextField'
import Button from '../component/Button'
import Navbar from '../component/Navbar'
import { useState } from 'react'
import Avatar from '../component/Avatar'
import { useHistory } from 'react-router-dom'
import { createSetup } from '../service/api-service'

const initialSetup = {
  title: '',
  hookah: '',
  hookahHead: '',
  tobacco: '',
  carbon: '',
  carbonTop: '',
  accessories: '',
  smokingDuration: '',
  numOfSmokedHeads: '',
  comment: '',
  setupCount: '',
  avatar: '',
}

export default function CreateSetup() {
  const { token, user } = useAuth()
  const [setup, setSetup] = useState(initialSetup)

  function handleSubmit(event) {
    event.preventDefault()
    createSetup(token, setup)
      .catch(error => console.error(error))
      .finally(() => history.push('/setup-gallery'))
  }

  const handleSetupChange = event =>
    setSetup({ ...setup, [event.target.name]: event.target.value })
  const handleCancel = () => setSetup(initialSetup)
  const history = useHistory()

  return (
    <Page>
      <Header title="Was rauchst Du gerade?" />
      <Main as="form" onSubmit={handleSubmit}>
        <TextField
          title="Titel:"
          name="title"
          value={setup.title}
          onChange={handleSetupChange}
        />
        <TextField
          title="Shisha:"
          name="hookah"
          value={setup.hookah}
          onChange={handleSetupChange}
        />
        <TextField
          title="Kopf:"
          name="hookahHead"
          value={setup.hookahHead}
          onChange={handleSetupChange}
        />
        <TextField
          title="Tabaksorte:"
          name="tobacco"
          value={setup.tobacco}
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
        <TextField
          title="Rauchdauer:"
          name="smokingDuration"
          value={setup.smokingDuration}
          onChange={handleSetupChange}
        />
        <TextField
          title="Anzahl der gerauchten Köpfe:"
          name="numOfSmokedHeads"
          value={setup.numOfSmokedHeads}
          onChange={handleSetupChange}
        />
        <TextField
          title="Kommentar:"
          name="comment"
          value={setup.comment}
          onChange={handleSetupChange}
        />
        <TextField
          title="Wie oft verwendet:"
          name="setupCount"
          value={setup.setupCount}
          onChange={handleSetupChange}
        />
        <Avatar src="https://thispersondoesnotexist.com/image" alt="bild" />
        <Button>speichern</Button>
        <Button onClick={handleCancel}>cancel</Button>
      </Main>
      <Navbar user={user} />
    </Page>
  )
}
