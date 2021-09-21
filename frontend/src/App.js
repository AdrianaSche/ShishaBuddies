import Login from './pages/Login'
import Profile from './pages/Profile'
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom'
import AuthProvider from './auth/AuthProvider'
import RegisterBasic from './pages/RegisterBasic'
import Settings from './pages/Settings'
import UpdateSettings from './pages/UpdateSettings'
import CreateSetup from './pages/CreateSetup'
import SetupGallery from './pages/SetupGallery'
export default function App() {
  return (
    <AuthProvider>
      <Router>
        <Switch>
          <Route exact path="/" component={Profile} />
          <Route path="/login" component={Login} />
          <Route path="/register" component={RegisterBasic} />
          <Route path="/settings" component={Settings} />
          <Route path="/setup" component={CreateSetup} />
          <Route path="/update-settings" component={UpdateSettings} />
          {/*<Route path="/setup_details" component={SetupGallery} />*/}
          <Route path="/setup-gallery" component={SetupGallery} />
        </Switch>
      </Router>
    </AuthProvider>
  )
}
