import styled from 'styled-components/macro'
export default function SettingsBox({ settings }) {
  return (
    <Wrapper>
      <p>Anzahl Shishas:</p>
      <p>{settings.numberOfHookahs}</p>
      <p>Anzahl Köpfe: </p>
      <p>{settings.numberOfHookahHeads}</p>
      <p>Anzahl Tabak:</p>
      <p>{settings.numberOfTobaccos}</p>
      <p>Lieblingsshisha:</p>
      <p>{settings.favHookah}</p>
      <p>Lieblingskopf:</p>
      <p>{settings.favHookahHead}</p>
      <p>Lieblingstabak: </p>
      <p>{settings.favTobacco}</p>
      <p>Rauchdauer gesamt:</p>
    </Wrapper>
  )
}

const Wrapper = styled.div`
  color: lightgreen;
  display: grid;
  grid-template-columns: 25% 17%;
  grid-auto-rows: 10%;
  grid-auto-columns: initial;
  grid-gap: var(--size-l);
  padding: var(--size-xl);
  text-align: center;
  height: 100%;
  width: 100%;
`
