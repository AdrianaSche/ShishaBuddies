import { NavLink } from 'react-router-dom'
import styled from 'styled-components/macro'

export default function Navbar({ user, ...props }) {
  return (
    <Wrapper {...props}>
      <NavLink exact to="/">
        Profile
      </NavLink>
      {user && <NavLink to="/settings">Profil verwalten</NavLink>}
      {user && <NavLink to="/setup">Was rauchst Du gerade?</NavLink>}
      {user && <NavLink to="/gallery">Deine Shisha Galerie</NavLink>}
      {!user && <NavLink to="/login">Login</NavLink>}
    </Wrapper>
  )
}

const Wrapper = styled.nav`
  border-top: 1px solid var(--neutral-dark);
  width: 100%;
  padding: var(--size-m);
  display: flex;
  overflow-y: scroll;

  a {
    flex-grow: 1;
    margin: 0 var(--size-l);
    text-align: center;
    text-decoration: none;
    color: var(--neutral-dark);
  }

  a.active {
    color: var(--accent);
  }
`
