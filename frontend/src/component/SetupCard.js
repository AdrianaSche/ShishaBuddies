import Avatar from './Avatar'
import './SetupCard.css'
import { Link } from 'react-router-dom'
import styled from 'styled-components/macro'
import ButtonGroup from './ButtonGroup'
import CancelButton from './CancelButton'

export default function SetupCard({ setup }) {
  return (
    <Wrapper>
      <h2>{setup.title}</h2>
      <p>{setup.avatar}</p>

      <Avatar
        src="https://rauchland.de/media/image/product/1717/lg/nizo-shisha-mit-reise-kuehltasche.jpg"
        alt="bild"
      />
      <br />
      <ButtonGroup>
        <Link to={`/setup/details/${setup.title}`}>Details</Link>
        <CancelButton>delete</CancelButton>
      </ButtonGroup>
    </Wrapper>
  )
}

const Wrapper = styled.div`
  padding: 5px;
  width: 200px;
  text-align: center;
  align-items: center;
  border: 1px solid #333;
  border-radius: 12px;
  box-shadow: 1px 2px 8px #666;
`
