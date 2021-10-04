import styled from 'styled-components/macro'
import Avatar from '../component/Avatar'
import { Link, useParams } from 'react-router-dom'
import { useEffect, useState } from 'react'
import { getSetupByTitle } from '../service/api-service'
import { useAuth } from '../auth/AuthProvider'
import Header from '../component/Header'
import Page from '../component/Page'
import Navbar from '../component/Navbar'
import ButtonDetails from '../component/ButtonDetails'

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
  setupCount: '',
}
export default function SetupDetails() {
  const { title } = useParams()
  const { token, user } = useAuth()
  const [setup, setSetup] = useState(initalSetup)

  useEffect(() => {
    getSetupByTitle(token, title)
      .then(fetchedSetup => setSetup(fetchedSetup))
      .catch(error => console.error(error))
  }, [token, title])

  return (
    <Page>
      <Header title={setup.title} />
      <Avatar
        src="https://shishashop.at/wp-content/uploads/2021/03/lava-united-shisha-silber.jpg"
        alt="setup"
      />
      <ButtonDetails as={Link} to={`/setup/details/edit/${title}`}>
        Details bearbeiten
      </ButtonDetails>
      <MainDetails>
        <Wrapper>
          <p>Shisha:{setup.hookah}</p>
          <p>Kopf:{setup.hookahHead}</p>
          <p>Tabak:{setup.tobacco}</p>
          <p>Kohle:{setup.carbon}</p>
          <p>Aufsatz:{setup.carbonTop}</p>
          <p>Zubehör:{setup.accessories}</p>
          <p>Rauchdauer:{setup.smokingDuration}</p>
          <p>Anzahl der gerauchten Köpfe:{setup.numOfSmokedHeads}</p>
          <p>Wie oft verwendet:{setup.setupCount}</p>
        </Wrapper>

        <Wrapper>
          <p>Dein Kommentar:</p>
          <p>{setup.comment}</p>
        </Wrapper>
      </MainDetails>
      <Navbar user={user} />
    </Page>
  )
}

const MainDetails = styled.main`
  display: flex;
`
const Wrapper = styled.div`
  padding: 5px;
  width: 180px;
  height: 80%;
  text-align: left;
  border: 1px solid #333;
  border-radius: 12px;
  box-shadow: 1px 2px 8px #666;
`
