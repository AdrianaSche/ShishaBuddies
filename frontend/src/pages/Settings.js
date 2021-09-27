import TextField from '../component/TextField'
import Header from '../component/Header'
import Page from '../component/Page'
import { useState } from 'react'
import { useAuth } from '../auth/AuthProvider'
import { createSettings } from '../service/api-service'
import Button from '../component/Button'
import Navbar from '../component/Navbar'
import { useHistory } from 'react-router-dom'
import MainCreateSetup from '../component/MainCreateSetup'
import styled from 'styled-components/macro'
import CancelButton from '../component/CancelButton'
import ButtonGroupCreateSetup from '../component/ButtonGroupCreateSetup'

const initialSettings = {
  numberOfHookahs: '',
  numberOfHookahHeads: '',
  numberOfTobaccos: '',
  favHookah: '',
  favHookahHead: '',
  favTobacco: '',
  smokingDurationInMinutes: '',
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
      <MainCreateSetup as="form" onSubmit={handleSubmit}>
        <Wrapper>
          <TextField
            title="Anzahl der Shishas:"
            name="numberOfHookahs"
            value={settings.numberOfHookahs}
            onChange={handleSettingsChange}
          />
        </Wrapper>
        <Wrapper>
          <TextField
            title="Anzahl der Tabaksorten:"
            name="numberOfTobaccos"
            value={settings.numberOfTobaccos}
            onChange={handleSettingsChange}
          />
        </Wrapper>
        <Wrapper>
          <TextField
            title="Anzahl der Köpfe:"
            name="numberOfHookahHeads"
            value={settings.numberOfHookahHeads}
            onChange={handleSettingsChange}
          />
        </Wrapper>
        <Wrapper>
          <TextField
            title="Lieblingsshisha:"
            name="favHookah"
            value={settings.favHookah}
            onChange={handleSettingsChange}
          />
        </Wrapper>
        <Wrapper>
          <TextField
            title="Lieblingstabak:"
            name="favTobacco"
            value={settings.favTobacco}
            onChange={handleSettingsChange}
          />
        </Wrapper>
        <Wrapper>
          <TextField
            title="Lieblingskopf:"
            name="favHookahHead"
            value={settings.favHookahHead}
            onChange={handleSettingsChange}
          />
        </Wrapper>
        <ButtonGroupCreateSetup>
          <Button>speichern</Button>
          <CancelButton onClick={handleCancel}>cancel</CancelButton>
        </ButtonGroupCreateSetup>
      </MainCreateSetup>
      <Navbar user={user} />
    </Page>
  )
}

const Wrapper = styled.div`
  width: 200px;
  text-align: center;
  border: 1px solid #333;
  border-radius: 12px;
  box-shadow: 1px 2px 8px #666;
`
