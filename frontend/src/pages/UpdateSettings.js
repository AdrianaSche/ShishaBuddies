import { useEffect, useState } from 'react'
import { getSettings, updateSettings } from '../service/api-service'
import Header from '../component/Header'
import TextField from '../component/TextField'
import { useAuth } from '../auth/AuthProvider'
import Button from '../component/Button'
import Page from '../component/Page'
import { Redirect } from 'react-router-dom'
import CancelButton from '../component/CancelButton'
import styled from 'styled-components/macro'
import MainUpdate from '../component/MainUpdate'
import ButtonGroupCreateSetup from '../component/ButtonGroupCreateSetup'
import Navbar from '../component/Navbar'

const userSettings = {
  numberOfHookahs: '',
  numberOfHookahHeads: '',
  numberOfTobaccos: '',
  favHookah: '',
  favHookahHead: '',
  favTobacco: '',
}

export default function UpdateSettings() {
  const { token, user } = useAuth()
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
      .finally(() => setRedirectToHome(true))
  }

  const handleSettingsChange = event =>
    setNewSettings({ ...newSettings, [event.target.name]: event.target.value })

  const handleCancel = () => {
    setNewSettings(currentSettings)
    setRedirectToHome(true)
  }

  if (redirectToHome) {
    return <Redirect to="/" />
  }
  return (
    <Page>
      <Header title="update Dein Profil" />
      <MainUpdate as="form" onSubmit={handleSubmit}>
        <Wrapper>
          <TextField
            title="Anzahl der Shishas:"
            name="numberOfHookahs"
            value={newSettings.numberOfHookahs}
            onChange={handleSettingsChange}
          />
        </Wrapper>
        <Wrapper>
          <TextField
            title="Anzahl der Tabaksorten:"
            name="numberOfTobaccos"
            value={newSettings.numberOfTobaccos}
            onChange={handleSettingsChange}
          />
        </Wrapper>
        <Wrapper>
          <TextField
            title="Anzahl der Köpfe:"
            name="numberOfHookahHeads"
            value={newSettings.numberOfHookahHeads}
            onChange={handleSettingsChange}
          />
        </Wrapper>
        <Wrapper>
          <TextField
            title="Lieblingsshisha:"
            name="favHookah"
            value={newSettings.favHookah}
            onChange={handleSettingsChange}
          />
        </Wrapper>
        <Wrapper>
          <TextField
            title="Lieblingstabak:"
            name="favTobacco"
            value={newSettings.favTobacco}
            onChange={handleSettingsChange}
          />
        </Wrapper>
        <Wrapper>
          <TextField
            title="Lieblingskopf:"
            name="favHookahHead"
            value={newSettings.favHookahHead}
            onChange={handleSettingsChange}
          />
        </Wrapper>
        <ButtonGroupCreateSetup>
          <Button>speichern</Button>
          <CancelButton onClick={handleCancel}>cancel</CancelButton>
        </ButtonGroupCreateSetup>
      </MainUpdate>
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
