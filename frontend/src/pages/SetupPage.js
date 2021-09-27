import { useAuth } from '../auth/AuthProvider'
import { useEffect, useState } from 'react'
import { getAllSetup } from '../service/api-service'
import Page from '../component/Page'
import Header from '../component/Header'

import { Redirect } from 'react-router-dom'
import SetupCard from '../component/SetupCard'
import SetupGallery from '../component/SetupGallery'
import Navbar from '../component/Navbar'

export default function SetupPage() {
  const { user, token } = useAuth()
  const [setups, setSetups] = useState([])
  const [setupAvailable, setSetupAvailable] = useState(false)

  useEffect(() => {
    getAllSetup(token)
      .then(setSetups)
      .catch(error => console.error(error))
    setSetupAvailable(true)
  }, [token])

  if (!setupAvailable) {
    return <p>noch keine Sessions!</p>
  }
  if (!user) {
    return <Redirect to="/login" />
  }
  return (
    <Page>
      <Header title="Deine Shisha Galerie" />
      <SetupGallery>
        {setups.length > 0 &&
          setups.map(setup => <SetupCard key={setup.title} setup={setup} />)}
      </SetupGallery>
      <Navbar user={user} />
    </Page>
  )
}
