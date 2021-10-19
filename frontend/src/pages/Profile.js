import Header from '../component/Header'
import { useAuth } from '../auth/AuthProvider'
import Page from '../component/Page'
import Main from '../component/Main'
import { Redirect } from 'react-router-dom'
import Navbar from '../component/Navbar'
import { useEffect, useState } from 'react'
import {
  getSettings,
  getTotalAmountOfHeads,
  getTotalSmokingDuration,
} from '../service/api-service'
import SettingsBox from '../component/SettingsBox'
import Button from '../component/Button'
import Avatar from '../component/Avatar'
import ButtonGroup from '../component/ButtonGroup'

export default function Profile() {
  const { user, logout, token } = useAuth()

  const [settings, setSettings] = useState([])
  const [smokingDuration, setSmokingDuration] = useState()
  const [amountHeads, setAmountHeads] = useState()

  const resetTracking = () => {
    setSmokingDuration(0)
    setAmountHeads(0)
  }

  useEffect(() => {
    getSettings(token)
      .then(setSettings)
      .catch(error => console.error(error))
    getTotalSmokingDuration(token)
      .then(setSmokingDuration)
      .catch(error => console.error(error))
    getTotalAmountOfHeads(token)
      .then(setAmountHeads)
      .catch(error => console.error(error))
  }, [token])

  if (!user) {
    return <Redirect to="/login" />
  }

  return (
    <Page>
      <Header title={user.username} />
      <Main>
        <Avatar src="https://thispersondoesnotexist.com/image" alt="bild" />
        <SettingsBox
          settings={settings}
          smokingDuration={smokingDuration}
          headCount={amountHeads}
        />
        <ButtonGroup>
          <Button onClick={logout}>Log out</Button>
          <Button onClick={resetTracking}>reset</Button>
        </ButtonGroup>
      </Main>
      <Navbar user={user} />
    </Page>
  )
}
