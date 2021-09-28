import styled from 'styled-components/macro'
import ProfileGallery from './ProfileGallery'
export default function SettingsBox({ settings, smokingDuration, headCount }) {
  return (
    <ProfileGallery>
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
        <Analyze>{headCount}</Analyze>
        <p>gerauchte Köpfe</p>
      </Wrapper1>
    </ProfileGallery>
  )
}

const Analyze = styled.div`
  font-size: 24px;
  color: red;
  margin: 10px;
`
const P = styled.div`
  font-size: 20px;
  color: #8fbc8f;
`

const Wrapper = styled.div`
  background-color: #404040;
  justify-content: center;
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
  display: grid;
  padding: 1px;
  color: white;
  float: left;
  width: 100px;
  text-align: center;
`
