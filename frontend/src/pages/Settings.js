import TextField from '../component/TextField'
import Header from '../component/Header'
import Page from '../component/Page'
import Main from '../component/Main'
import { useState } from 'react'
import { useAuth } from '../auth/AuthProvider'
import { createSettings } from '../service/api-service'
import Button from '../component/Button'
import Navbar from '../component/Navbar'
import { useHistory } from 'react-router-dom'

const initialSettings = {
  numberOfHookahs: '',
  numberOfHookahHeads: '',
  numberOfTobaccos: '',
  favHookah: '',
  favHookahHead: '',
  favTobacco: '',
}
export default function Settings() {
  const [settings, setSettings] = useState(initialSettings)
  const { token, user } = useAuth()
  const history = useHistory()

  function handleSubmit(event) {
    event.preventDefault()
    createSettings(token, settings)
      .catch(error => console.error(error))
      .finally(() => history.push('/'))
  }

  const handleSettingsChange = event =>
    setSettings({ ...settings, [event.target.name]: event.target.value })

  const handleCancel = () => setSettings(initialSettings)

  return (
    <Page>
      <Header title="erweitertes Profil" />
      <Main as="form" onSubmit={handleSubmit}>
        <TextField
          title="Anzahl der Shishas:"
          name="numberOfHookahs"
          value={settings.numberOfHookahs}
          onChange={handleSettingsChange}
        />
        <TextField
          title="Anzahl der Tabaksorten:"
          name="numberOfTobaccos"
          value={settings.numberOfTobaccos}
          onChange={handleSettingsChange}
        />
        <TextField
          title="Anzahl der Köpfe:"
          name="numberOfHookahHeads"
          value={settings.numberOfHookahHeads}
          onChange={handleSettingsChange}
        />
        <TextField
          title="Lieblingsshisha:"
          name="favHookah"
          value={settings.favHookah}
          onChange={handleSettingsChange}
        />
        <TextField
          title="Lieblingstabak:"
          name="favTobacco"
          value={settings.favTobacco}
          onChange={handleSettingsChange}
        />
        <TextField
          title="Lieblingskopf:"
          name="favHookahHead"
          value={settings.favHookahHead}
          onChange={handleSettingsChange}
        />
        <Button>speichern</Button>
        <Button onClick={handleCancel}>cancel</Button>
      </Main>
      <Navbar user={user} />
    </Page>
  )
}
