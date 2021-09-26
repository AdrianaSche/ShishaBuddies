import Avatar from './Avatar'
import './SetupCard.css'
import { Link } from 'react-router-dom'
import styled from 'styled-components/macro'

export default function SetupCard({ setup }) {
  return (
    <Wrapper>
      <h2>{setup.title}</h2>
      <p>{setup.avatar}</p>

      <Avatar
        src="https://rauchland.de/media/image/product/1717/lg/nizo-shisha-mit-reise-kuehltasche.jpg"
        alt="bild"
      />
      <Link to={`/setup/details/${setup.title}`}>Details</Link>
    </Wrapper>
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
