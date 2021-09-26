import Page from '../component/Page'
import Header from '../component/Header'
import Navbar from '../component/Navbar'
import Button from '../component/Button'
import Main from '../component/Main'
import Username from '../component/Username'
import { useAuth } from '../auth/AuthProvider'

export default function Logout() {
  const { user, logout } = useAuth()
  return (
    <Page>
      <Header title="Logout" />
      <Main>
        <p>
          You are logged in as <Username>{user.username}</Username>
        </p>
        <Button onClick={logout}>Log out</Button>
      </Main>
      <Navbar user={user} />
    </Page>
  )
}
