import Avatar from './Avatar'
import { Link } from 'react-router-dom'
import styled from 'styled-components/macro'
import ButtonGroup from './ButtonGroup'
import CancelButton from './CancelButton'
import { deleteSetup } from '../service/api-service'
import { useAuth } from '../auth/AuthProvider'
import Button from './Button'
import ButtonGroupGallery from './ButtonGroupGallery'

export default function SetupCard({ setup, reload }) {
  const { token } = useAuth()

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
      <ButtonGroupGallery>
        <Button as={Link} to={`/setup/details/${setup.title}`}>
          Details
        </Button>
        <CancelButton onClick={handleDeleteSetup}>delete</CancelButton>
      </ButtonGroupGallery>
    </Wrapper>
  )
}

const Wrapper = styled.div`
  padding: 5px;
  text-align: center;
  align-items: center;
  border: 1px solid #333;
  border-radius: 12px;
  box-shadow: 1px 2px 8px #666;
`
