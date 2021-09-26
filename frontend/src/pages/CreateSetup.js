import Header from '../component/Header'
import Page from '../component/Page'
import { useAuth } from '../auth/AuthProvider'
import Main from '../component/Main'
import TextField from '../component/TextField'
import Button from '../component/Button'
import Navbar from '../component/Navbar'
import { useState } from 'react'
import Avatar from '../component/Avatar'
import { useHistory } from 'react-router-dom'
import { createSetup } from '../service/api-service'
import styled from 'styled-components/macro'
import CancelButton from '../component/CancelButton'
import TextFieldTitle from '../component/TextFieldTitle'
import ButtonGroup from '../component/ButtonGroup'

const initialSetup = {
  title: '',
  hookah: '',
  hookahHead: '',
  tobacco: '',
  carbon: '',
  carbonTop: '',
  accessories: '',
  smokingDuration: 0,
  numOfSmokedHeads: 0,
  comment: '',
  setupCount: 0,
  avatar: '',
}

export default function CreateSetup() {
  const { token, user } = useAuth()
  const [setup, setSetup] = useState(initialSetup)

  function handleSubmit(event) {
    event.preventDefault()
    createSetup(token, setup)
      .catch(error => console.error(error))
      .finally(() => history.push('/setup-gallery'))
  }

  const handleSetupChange = event =>
    setSetup({ ...setup, [event.target.name]: event.target.value })
  const handleCancel = () => setSetup(initialSetup)
  const history = useHistory()

  return (
    <Page>
      <Header title="Was rauchst Du gerade?" />
      <SetupMain as="form" onSubmit={handleSubmit}>
        <TextFieldTitle
          title="Titel:"
          name="title"
          value={setup.title}
          onChange={handleSetupChange}
        />

        <Wrapper>
          <TextField
            title="Shisha:"
            name="hookah"
            value={setup.hookah}
            onChange={handleSetupChange}
          />
        </Wrapper>

        <Wrapper>
          <TextField
            title="Kopf:"
            name="hookahHead"
            value={setup.hookahHead}
            onChange={handleSetupChange}
          />
        </Wrapper>
        <Wrapper>
          <TextField
            title="Tabaksorte:"
            name="tobacco"
            value={setup.tobacco}
            onChange={handleSetupChange}
          />
        </Wrapper>
        <Wrapper>
          <TextField
            title="Kohle:"
            name="carbon"
            value={setup.carbon}
            onChange={handleSetupChange}
          />
        </Wrapper>
        <Wrapper>
          <TextField
            title="Aufsatz:"
            name="carbonTop"
            value={setup.carbonTop}
            onChange={handleSetupChange}
          />
        </Wrapper>
        <Wrapper>
          <TextField
            title="Zubehör:"
            name="accessories"
            value={setup.accessories}
            onChange={handleSetupChange}
          />
        </Wrapper>
        <ButtonGroup>
          <Button>speichern</Button>
          <CancelButton onClick={handleCancel}>cancel</CancelButton>
        </ButtonGroup>
      </SetupMain>

      <Navbar user={user} />
    </Page>
  )
}

const Wrapper = styled.div`
  padding: 50px;
  width: 200px;
  text-align: center;
  border: 1px solid #333;
  border-radius: 12px;
  box-shadow: 1px 2px 8px #666;
`

const SetupMain = styled.div`
  margin: 9px;
  color: lightgreen;
  display: flex;
  float: left;
  flex-wrap: wrap;
  padding: var(--size-xl);
  text-align: center;
  height: 100%;
  width: 100%;
`
