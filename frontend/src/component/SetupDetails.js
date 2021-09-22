import styled from 'styled-components/macro'
import Avatar from './Avatar'
import { Link, useParams } from 'react-router-dom'
import { useEffect, useState } from 'react'
import axios from 'axios'

export default function SetupDetails() {
  const { id } = useParams()

  const [setup, setSetup] = useState()

  //get setup with "id" from backend
  useEffect(() => {
    axios
      .get('api///${id}')
      .then(response => response.data)
      .then(fetchedSetup => setSetup(fetchedSetup))
      .catch(error => console.error(error))
  }, [id])

  if (!setup) {
    return <p>loading...</p>
  }

  return (
    <Wrapper>
      <p>Titel:{setup.title}</p>
      <p>Shisha:{setup.hookah}</p>
      <p> Kopf:{setup.hookahHead}</p>
      <p>Tabak:{setup.tobacco}</p>
      <p>Kohle:{setup.carbon}</p>
      <p>Aufsatz:{setup.carbonTop}</p>
      <p>Zubehör:{setup.accessories}</p>
      <p>Rauchdauer:{setup.smokingDuration}</p>
      <p>Anzahl der gerauchten Köpfe:{setup.numOfSmokedHeads}</p>
      <p>Dein Kommentar:{setup.comment}</p>
      <Avatar src="https://thispersondoesnotexist.com/image" alt="setup" />
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
