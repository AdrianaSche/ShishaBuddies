import { useAuth } from '../auth/AuthProvider'
import { useEffect, useState } from 'react'
import { getAllSetup } from '../service/api-service'
import Page from '../component/Page'
import Header from '../component/Header'
import Main from '../component/Main'

import { Redirect } from 'react-router-dom'
import SetupCard from '../component/SetupCard'
import styled from 'styled-components/macro'

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
      <Wrapper>
        {setups.length > 0 &&
          setups.map(setup => <SetupCard key={setup.id} setup={setup} />)}
      </Wrapper>
    </Page>
  )
}

const Wrapper = styled.div`
  margin: 12px;
  color: lightgreen;
  display: flex;
  float: left;
  flex-wrap: wrap;
  padding: var(--size-xl);
  text-align: center;
  height: 100%;
  width: 100%;
`
