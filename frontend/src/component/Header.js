import styled from 'styled-components/macro'
import './Header.css'
export default function Header({ title }) {
  return (
    <Wrapper>
      <img //Bild hinzugefügt
        className="header_img"
        src="https://shisha-steamulation.de/wp-content/uploads/2021/05/waterfall-Blow-OffSleeveBow-Off-Adapter-UpPlate-lp.jpg"
        alt="hookah"
      />
      <h1>{title}</h1>
    </Wrapper>
  )
}

const Wrapper = styled.header`
  display: flex; //bild neben heading
  align-items: center; //Anordnung bild
  width: 100%;
  text-align: center;
  background: var(--background-dark);
  color: var(--accent);
`
