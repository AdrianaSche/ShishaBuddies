import styled from 'styled-components/macro'
export default function SettingsBox({ settings, smokingDuration }) {
  return (
    <div>
      <Wrapper>
        <p>Anzahl Shishas:</p>
        <P>{settings.numberOfHookahs}</P>
        <img src="hoo.png" alt="hookah" />
      </Wrapper>
      <Wrapper>
        <p>Anzahl Köpfe: </p>
        <P>{settings.numberOfHookahHeads}</P>
        <img src="kopf.png" alt="kopf" />
      </Wrapper>
      <Wrapper>
        <p>Tabaksorten:</p>
        <P>{settings.numberOfTobaccos}</P>
        <img src="tabak.png" alt="tabak" />
      </Wrapper>
      <Wrapper>
        <p>Lieblingsshisha:</p>
        <P>{settings.favHookah}</P>
        <img src="hoo.png" alt="hookah" />
      </Wrapper>
      <Wrapper>
        <p>Lieblingskopf:</p>
        <P>{settings.favHookahHead}</P>
        <img src="kopf.png" alt="head" />
      </Wrapper>
      <Wrapper>
        <p>Lieblingstabak: </p>
        <P>{settings.favTobacco}</P>
        <img src="tabak.png" alt="tabak" />
      </Wrapper>
      <Wrapper1>
        <Analyze>{smokingDuration}</Analyze>
        <p>gerauchte Minuten</p>
      </Wrapper1>
      <Wrapper1>
        <Analyze>{smokingDuration}</Analyze>
        <p>gerauchte Köpfe</p>
      </Wrapper1>
    </div>
  )
}

const Analyze = styled.div`
  font-size: 24px;
  color: red;
`
const P = styled.div`
  font-size: 24px;
  color: #8fbc8f;
`

const Wrapper = styled.div`
  background-color: #404040;

  display: grid;
  padding: 3px;
  float: left;
  flex-wrap: wrap;
  width: 150px;
  text-align: center;
  border: 1px solid #333;
  border-radius: 12px;
  box-shadow: 1px 2px 8px #666;
`
const Wrapper1 = styled.div`
  background-color: #202020;
  margin: 12px;
  display: grid;
  padding: 3px;
  color: white;
  float: left;
  width: 200px;
  text-align: center;
  //border: 1px solid #333;
  //border-radius: 12px;
  //box-shadow: 1px 2px 8px #666;
`

/*const Wrapper = styled.div`
  margin: 12px;
  color: lightgreen;
  display: flex;
  float: left;
  flex-wrap: wrap;
  padding: var(--size-xl);
  text-align: center;
  height: 100%;
  width: 100%;
`*/
