import Header from '../component/Header'
import { useAuth } from '../auth/AuthProvider'
import Page from '../component/Page'
import Main from '../component/Main'
import { Redirect } from 'react-router-dom'
import Navbar from '../component/Navbar'
import { useEffect, useState } from 'react'
import { getSettings } from '../service/api-service'
import styled from 'styled-components/macro'

export default function Profile() {
  const { user, logout, token } = useAuth()

  const [settings, setSettings] = useState([])

  useEffect(() => {
    getSettings(token)
      .then(setSettings)
      .catch(error => console.error(error))
  }, [token])

  if (!user) {
    return <Redirect to="/login" />
  }

  return (
    <Page>
      <Main>
        <Header title={user.username} />
        <Ul>
          <li>Anzahl Shishas: {settings.numberOfHookahs}</li>
          <li>Anzahl Köpfe: {settings.numberOfHookahHeads}</li>
          <li>Anzahl Tabak: {settings.numberOfTobaccos}</li>
          <li>Lieblingsshisha: {settings.favHookah} </li>
          <li>Lieblingskopf: {settings.favHookahHead} </li>
          <li>Lieblingstabak: {settings.favTobacco}</li>
        </Ul>
      </Main>
      <Navbar user={user} />
      <button onClick={logout}> Logout</button>
      <button>edit Profile</button>
    </Page>
  )
}

const Ul = styled.ul`
  list-style-type: none;
  padding: var(--size-l);
`
