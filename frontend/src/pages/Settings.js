import TextField from '../component/TextField'
import Header from '../component/Header'
import Page from '../component/Page'
import Main from '../component/Main'
import { useState } from 'react'

const userSettings = {
  numberOfHookahs: '',
  numberOfHookahHeads: '',
  numberOfTobaccos: '',
  favHookah: '',
  favHookahHead: '',
  favTobacco: '',
}
export default function Settings() {
  const [settings, setSettings] = useState(userSettings)

  function handleSubmit(event) {
    event.preventDefault()
    //post to backend
  }

  const handleSettingsChange = event =>
    setSettings({ ...settings, [event.target.name]: event.target.value })

  const handleCancel = () => setSettings(userSettings)

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
        <button>speichern</button>
        <button type="button">Shisha Galerie anlegen</button>
        <button onClick={handleCancel}>cancel</button>
      </Main>
    </Page>
  )
}
