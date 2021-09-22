import styled from 'styled-components/macro'
import Avatar from '../component/Avatar'
import { Link, useParams } from 'react-router-dom'
import { useEffect, useState } from 'react'
import { getSetupByTitle } from '../service/api-service'
import { useAuth } from '../auth/AuthProvider'
import Header from '../component/Header'
import Main from '../component/Main'
import Page from '../component/Page'
import Button from '../component/Button'

const initalSetup = {
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
}
export default function SetupDetails() {
  const { title } = useParams()
  const { token } = useAuth()
  const [setup, setSetup] = useState(initalSetup)

  useEffect(() => {
    getSetupByTitle(token, title)
      .then(fetchedSetup => setSetup(fetchedSetup))
      .catch(error => console.error(error))
  }, [setup, title])

  if (!setup) {
    return <p>loading...</p>
  }

  return (
    <Wrapper>
      <Page>
        <Header title="Deine Session" />
        <p>{setup.title}</p>
        <p>Shisha:{setup.hookah}</p>
        <p>Kopf:{setup.hookahHead}</p>
        <p>Tabak:{setup.tobacco}</p>
        <p>Kohle:{setup.carbon}</p>
        <p>Aufsatz:{setup.carbonTop}</p>
        <p>Zubehör:{setup.accessories}</p>
        <p>Rauchdauer:{setup.smokingDuration}</p>
        <p>Anzahl der gerauchten Köpfe:{setup.numOfSmokedHeads}</p>
        <p>Dein Kommentar:{setup.comment}</p>
        <Link to={'/setup/details/edit-setup'}>Details bearbeiten</Link>
        <Avatar
          src="https://shishashop.at/wp-content/uploads/2021/03/lava-united-shisha-silber.jpg"
          alt="setup"
        />
      </Page>
    </Wrapper>
  )
}

const Wrapper = styled.div`
  color: lightgreen;
  display: grid;
  grid-template-columns: 15% 10%;
  grid-auto-rows: 10%;
  grid-auto-columns: initial;
  grid-gap: var(--size-l);
  padding: var(--size-xl);
  text-align: center;
  height: 100%;
  width: 100%;
`
