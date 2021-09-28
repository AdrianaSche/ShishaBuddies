import Header from '../component/Header'
import Page from '../component/Page'
import Main from '../component/Main'
import { useEffect, useState } from 'react'
import { useAuth } from '../auth/AuthProvider'
import { getSetupByTitle, updateSetup } from '../service/api-service'
import Button from '../component/Button'
import { useHistory, useParams } from 'react-router-dom'
import TextArea from '../component/TextArea'
import SetupCountField from '../component/SetupCountField'
import Navbar from '../component/Navbar'
import '../component/CancelButton'
import CancelButton from '../component/CancelButton'
import ButtonGroupCreateSetup from '../component/ButtonGroupCreateSetup'

const initialSetup = {
  title: '',
  hookah: '',
  hookahHead: '',
  tobacco: '',
  carbon: '',
  carbonTop: '',
  accessories: '',
  smokingDuration: '',
  numOfSmokedHeads: '',
  comment: '',
  setupCount: '',
  avatar: '',
}
// von hier wieder zurück zu dem setup/details/title_of_setup, was geupdatet werden soll
export default function SetupAnalysis() {
  const { title } = useParams()
  const { token, user } = useAuth()
  const [newSetup, setNewSetup] = useState(initialSetup)
  const [currentSetup, setCurrentSetup] = useState(initialSetup)
  const history = useHistory()

  useEffect(() => {
    getSetupByTitle(token, title)
      .then(response => {
        setCurrentSetup(response)
        setNewSetup(response)
      })
      .catch(error => console.error(error))
    //.finally(() => history.goBack())

    // .finally(() => history.push(`setup/details/${title}`))
  }, [token, title])

  const handleSubmit = event => {
    event.preventDefault()
    updateSetup(title, newSetup, token).catch(error => console.error(error))
  }

  const handleSetupChange = event =>
    setNewSetup({ ...newSetup, [event.target.name]: event.target.value })

  const handleCancel = () => setNewSetup(currentSetup)

  return (
    <Page>
      <Header title="Bewerte Deine Shisha-Session!" />
      <Main as="form" onSubmit={handleSubmit}>
        <SetupCountField
          title="Rauchdauer(in min):"
          name="smokingDuration"
          value={newSetup.smokingDuration}
          onChange={handleSetupChange}
        />

        <SetupCountField
          title="Wie oft verwendet:"
          name="setupCount"
          value={newSetup.setupCount}
          onChange={handleSetupChange}
        />

        <SetupCountField
          title="Anzahl der gerauchten Köpfe:"
          name="numOfSmokedHeads"
          value={newSetup.numOfSmokedHeads}
          onChange={handleSetupChange}
        />

        <TextArea
          title="Kommentar:"
          name="comment"
          value={newSetup.comment}
          onChange={handleSetupChange}
        />

        <ButtonGroupCreateSetup>
          <Button>speichern</Button>
          <CancelButton onClick={handleCancel}>cancel</CancelButton>
        </ButtonGroupCreateSetup>
      </Main>
      <Navbar user={user} />
    </Page>
  )
}
