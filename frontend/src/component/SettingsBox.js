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
    </Wrapper>
  )
}

const Wrapper = styled.div`
  display: grid;
  grid-template-columns: 17% 17%;
  grid-auto-rows: 7%;
  grid-auto-columns: initial;
  grid-gap: var(--size-l);
  padding: var(--size-xl);
  text-align: center;
  height: 100%;
  width: 100%;
`
