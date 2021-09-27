import { useAuth } from '../auth/AuthProvider'
import { useEffect, useState } from 'react'
import { getAllSetup } from '../service/api-service'
import Page from '../component/Page'
import Header from '../component/Header'

import { Redirect } from 'react-router-dom'
import SetupCard from '../component/SetupCard'
import styled from 'styled-components/macro'
import ButtonGroup from '../component/ButtonGroup'
import CancelButton from '../component/CancelButton'
import Button from '../component/Button'
import MainGallery from '../component/MainGallery'

export default function SetupGallery() {
  const { user, token } = useAuth()
  const [setups, setSetups] = useState([])

  useEffect(() => {
    getAllSetup(token)
      .then(setSetups)
      .catch(error => console.error(error))
  }, [token])

  if (!user) {
    return <Redirect to="/login" />
  }
  return (
    <Page>
      <Header title="Deine Shisha Galerie" />
      <MainGallery>
        <Wrapper>
          {setups.length > 0 &&
            setups.map(setup => <SetupCard key={setup.title} setup={setup} />)}
        </Wrapper>
        <ButtonGroup>
          <Button>zurück</Button>
          <CancelButton>cancel</CancelButton>
        </ButtonGroup>
      </MainGallery>
    </Page>
  )
}

const Wrapper = styled.div`
  display: grid;
  grid-template-columns: 47% 47%;
  grid-auto-rows: min-content;
  grid-auto-columns: initial;
  grid-gap: var(--size-m);
  padding: var(--size-m);
  height: 100%;
  width: 100%;
`
