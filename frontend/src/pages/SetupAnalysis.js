import Header from '../component/Header'
import TextField from '../component/TextField'
import Page from '../component/Page'
import Main from '../component/Main'
import { useEffect, useState } from 'react'
import { useAuth } from '../auth/AuthProvider'
import { getSetupByTitle, updateSetup } from '../service/api-service'
import Button from '../component/Button'
import { useParams } from 'react-router-dom'
import TextArea from '../component/TextArea'
import SetupCountField from '../component/SetupCountField'

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

export default function SetupAnalysis() {
  const { title } = useParams()
  const { token } = useAuth()
  const [newSetup, setNewSetup] = useState(initialSetup)
  const [currentSetup, setCurrentSetup] = useState(initialSetup)

  useEffect(() => {
    getSetupByTitle(token, title)
      .then(response => {
        setCurrentSetup(response)
        setNewSetup(response)
      })
      .catch(error => console.error(error))
  }, [token, title])

  const handleSubmit = event => {
    event.preventDefault()
    updateSetup(title, newSetup, token).catch(error => console.error(error))
  }

  const handleSetupChange = event =>
    setNewSetup({ ...newSetup, [event.target.name]: event.target.value })

  return (
    <Page>
      <Header title="Bewerte Dein Setup!" />
      <Main as="form" onSubmit={handleSubmit}>
        {/* <TextField
          title="Anzahl der Köpfe:"
          name="numberOfHookahHeads"
          value={newSetup.numOfSmokedHeads}
          onChange={handleSetupChange}
        />*/}
        <SetupCountField
          title="Rauchdauer(in min):"
          name="smokingDuration"
          value={newSetup.smokingDuration}
          onChange={handleSetupChange}
        />

        <SetupCountField
          title="Setup count:"
          name="setupCount"
          value={newSetup.setupCount}
          onChange={handleSetupChange}
        />
        <TextArea
          title="Kommentar:"
          name="comment"
          value={newSetup.comment}
          onChange={handleSetupChange}
        />
        <Button>speichern</Button>
      </Main>
    </Page>
  )
}
