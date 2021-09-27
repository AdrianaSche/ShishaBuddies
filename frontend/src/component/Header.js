import styled from 'styled-components/macro'
import './Header.css'
export default function Header({ title }) {
  return (
    <Wrapper>
      <img
        className="header_img"
        src="https://shisha-steamulation.de/wp-content/uploads/2021/05/waterfall-Blow-OffSleeveBow-Off-Adapter-UpPlate-lp.jpg"
        alt="hookah"
      />
      <h1>{title}</h1>
      <img
        className="header_img"
        src="https://shisha-steamulation.de/wp-content/uploads/2021/05/waterfall-Blow-OffSleeveBow-Off-Adapter-UpPlate-lp.jpg"
        alt="hookah"
      />
    </Wrapper>
  )
}

const Wrapper = styled.header`
  display: flex;
  align-items: center;
  justify-content: space-between;
  width: 100%;
  text-align: center;
  background: var(--background-dark);
  color: var(--accent);
`
