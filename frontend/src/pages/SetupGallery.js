//render all setups of loggedIn user
import setups from './setup.json'
import Page from '../component/Page'
import Header from '../component/Header'
import SetupDetails from './SetupDetails'
import SetupBox from '../component/SetupBox'
import { useEffect, useState } from 'react'
import { useAuth } from '../auth/AuthProvider'
import { getSettings } from '../service/api-service'

export default function SetupGallery() {
  /*
    const {user, token} = useAuth()
    const [setup,setSetup] =useState([])
    const [search, setSearch] = useState('')

    useEffect(() => {
      getSetup(token)
        .then(setSetup)
        .catch(error => console.error(error))
    }, [token])
    */

  //state token
  //lade all seine Setups
  //die einzelnen Setups müssen in Cards gerendert werden

  return (
    <Page>
      <Header title="Deine Shisha Galerie" />
    </Page>
  )
}
