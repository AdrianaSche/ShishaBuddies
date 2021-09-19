import Header from '../component/Header'
import Page from '../component/Page'
import { useHistory } from 'react-router-dom'
import Button from '../component/Button'

export default function SetupDetails() {
  const history = useHistory()
  const handleOnclick = () => history.push('/')
  return (
    <Page>
      <Header title="Dein heutiges Setup" />
      <Button onClick={handleOnclick}>zurück</Button>
    </Page>
  )
}
