import Avatar from './Avatar'
import './SetupCard.css'
import { Link, Redirect, useHistory, useParams } from 'react-router-dom'
import styled from 'styled-components/macro'
import ButtonGroup from './ButtonGroup'
import CancelButton from './CancelButton'
import { deleteSetup } from '../service/api-service'
import { useAuth } from '../auth/AuthProvider'
import { useState } from 'react'
import Button from './Button'

export default function SetupCard({ setup, reload }) {
  //const { title } = useParams()
  const { token } = useAuth()
  const history = useHistory()
  // const [redirect, setRedirect] = useState(false)

  const handleDeleteSetup = () => {
    deleteSetup(setup.title, token)
      .catch(error => console.error(error))
      .finally(() => reload())
  }

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
        <Button as={Link} to={`/setup/details/edit/${setup.title}`}>
          Details
        </Button>
        <CancelButton onClick={handleDeleteSetup}>delete</CancelButton>
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
