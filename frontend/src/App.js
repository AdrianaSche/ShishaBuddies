import Login from './pages/Login'
import Profile from './pages/Profile'
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom'
import AuthProvider from './auth/AuthProvider'
import RegisterBasic from './pages/RegisterBasic'
import Settings from './pages/Settings'
import UpdateSettings from './pages/UpdateSettings'
import CreateSetup from './pages/CreateSetup'
import SetupPage from './pages/SetupPage'
import SetupDetails from './pages/SetupDetails'
import SetupAnalysis from './pages/SetupAnalysis'
import Logout from './pages/Logout'
export default function App() {
  return (
    <AuthProvider>
      <Router>
        <Switch>
          <Route exact path="/" component={Profile} />
          <Route path="/login" component={Login} />
          <Route path="/register" component={RegisterBasic} />
          <Route path="/settings" component={Settings} />
          <Route path="/logout" component={Logout} />

          <Route path="/setup/details/edit/:title" component={SetupAnalysis} />
          <Route path="/setup/details/:title" component={SetupDetails} />
          <Route path="/update-settings" component={UpdateSettings} />
          <Route path="/setup-gallery" component={SetupPage} />
          <Route path="/setup" component={CreateSetup} />
        </Switch>
      </Router>
    </AuthProvider>
  )
}
