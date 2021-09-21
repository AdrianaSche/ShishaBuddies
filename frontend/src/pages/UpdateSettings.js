import { useEffect, useState } from 'react'
import { getSettings, updateSettings } from '../service/api-service'
import Header from '../component/Header'
import TextField from '../component/TextField'
import { useAuth } from '../auth/AuthProvider'
import Button from '../component/Button'
import Page from '../component/Page'
import Main from '../component/Main'
import { Redirect } from 'react-router-dom'

const userSettings = {
  numberOfHookahs: '',
  numberOfHookahHeads: '',
  numberOfTobaccos: '',
  favHookah: '',
  favHookahHead: '',
  favTobacco: '',
}

export default function UpdateSettings() {
  const { token } = useAuth()
  const [newSettings, setNewSettings] = useState(userSettings)
  const [currentSettings, setCurrentSettings] = useState()
  const [redirectToHome, setRedirectToHome] = useState(false)

  useEffect(() => {
    getSettings(token)
      .then(response => {
        setCurrentSettings(response)
        setNewSettings(response)
      })
      .catch(error => console.error(error))
  }, [token])

  const handleSubmit = event => {
    event.preventDefault()
    updateSettings(newSettings, token)
      .catch(error => console.error(error))
      .then(setRedirectToHome(true))
  }

  const handleSettingsChange = event =>
    setNewSettings({ ...newSettings, [event.target.name]: event.target.value })

  const handleCancel = () => setNewSettings(currentSettings)

  if (redirectToHome) {
    return <Redirect to="/" />
  }
  return (
    <Page>
      <Header title="update Dein Profil" />
      <Main as="form" onSubmit={handleSubmit}>
        <TextField
          title="Anzahl der Shishas:"
          name="numberOfHookahs"
          value={newSettings.numberOfHookahs}
          onChange={handleSettingsChange}
        />
        <TextField
          title="Anzahl der Tabaksorten:"
          name="numberOfTobaccos"
          value={newSettings.numberOfTobaccos}
          onChange={handleSettingsChange}
        />
        <TextField
          title="Anzahl der Köpfe:"
          name="numberOfHookahHeads"
          value={newSettings.numberOfHookahHeads}
          onChange={handleSettingsChange}
        />
        <TextField
          title="Lieblingsshisha:"
          name="favHookah"
          value={newSettings.favHookah}
          onChange={handleSettingsChange}
        />
        <TextField
          title="Lieblingstabak:"
          name="favTobacco"
          value={newSettings.favTobacco}
          onChange={handleSettingsChange}
        />
        <TextField
          title="Lieblingskopf:"
          name="favHookahHead"
          value={newSettings.favHookahHead}
          onChange={handleSettingsChange}
        />
        <Button>speichern</Button>
        <Button onClick={handleCancel}>cancel</Button>
      </Main>
    </Page>
  )
}
