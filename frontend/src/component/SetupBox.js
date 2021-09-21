import styled from 'styled-components/macro'
import Avatar from './Avatar'
export default function SetupBox({ setup }) {
  return (
    <Wrapper>
      <p>Titel:</p>
      <p>{setup.title}</p>
      <p>Shisha:</p>
      <p>{setup.hookah}</p>
      <p> Kopf: </p>
      <p>{setup.hookahHead}</p>
      <p>Tabak:</p>
      <p>{setup.tobacco}</p>
      <p>Kohle:</p>
      <p>{setup.carbon}</p>
      <p>Aufsatz:</p>
      <p>{setup.carbonTop}</p>
      <p>Zubehör:</p>
      <p>{setup.accessories}</p>
      <p>Rauchdauer:</p>
      <p>{setup.smokingDuration}</p>
      <p>Anzahl der gerauchten Köpfe:</p>
      <p>{setup.numOfSmokedHeads}</p>
      <p>Dein Kommentar:</p>
      <p>{setup.comment}</p>
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
