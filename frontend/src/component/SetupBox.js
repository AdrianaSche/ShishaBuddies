import styled from 'styled-components/macro'
export default function SetupBox({ setups }) {
  return (
    <Wrapper>
      <p>Shisha:</p>
      <p>{setups.hookah}</p>
      <p> Kopf: </p>
      <p>{setups.head}</p>
      <p>Tabak:</p>
      <p>{setups.tobacco}</p>
      <p>Aufsatz:</p>
      <p>{setups.carbonTop}</p>
      <img src="https://thispersondoesnotexist.com/image" alt="setup" />
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
