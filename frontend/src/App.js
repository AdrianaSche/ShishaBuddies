import Login from './pages/Login'
import Profile from './pages/Profile'
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom'
import AuthProvider from './auth/AuthProvider'
import RegisterBasic from './pages/RegisterBasic'
import Settings from './pages/Settings'

export default function App() {
  return (
    <AuthProvider>
      <Router>
        <Switch>
          <Route exact path="/" component={Profile} />
          <Route path="/login" component={Login} />
          <Route path="/register" component={RegisterBasic} />
          <Route path="/settings" component={Settings} />
        </Switch>
      </Router>
    </AuthProvider>
  )
}
