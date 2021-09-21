import { useAuth } from '../auth/AuthProvider'
import { useEffect, useState } from 'react'
import { getAllSetup } from '../service/api-service'
import Page from '../component/Page'
import Header from '../component/Header'
import Main from '../component/Main'
import SetupBox from '../component/SetupBox'
import { Redirect } from 'react-router-dom'
import SetupCard from '../component/SetupCard'

export default function SetupGallery() {
  const { user, token } = useAuth()
  const [setups, setSetups] = useState([])

  useEffect(() => {
    getAllSetup(token)
      .then(setSetups)
      .catch(error => console.error(error))
      .finally(() => console.log(setups))
  }, [token])

  if (!user) {
    return <Redirect to="/login" />
  }
  return (
    <Page>
      <Main>
        <Header title="Deine Shisha Galerie" />
        {setups.length > 0 && setups.map(setup => <SetupCard setup={setup} />)}
      </Main>
    </Page>
  )
}
