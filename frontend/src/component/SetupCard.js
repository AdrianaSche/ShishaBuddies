import Avatar from './Avatar'
import './SetupCard.css'
import Button from './Button'
import { Link } from 'react-router-dom'

export default function SetupCard({ setup }) {
  return (
    <div className="setup-card">
      <h2>{setup.title}</h2>
      <p>{setup.avatar}</p>

      <Avatar
        src="https://rauchland.de/media/image/product/1717/lg/nizo-shisha-mit-reise-kuehltasche.jpg"
        alt="bild"
      />
      <Link to={`/details/${setup.id}`}>Details</Link>
    </div>
  )
}
