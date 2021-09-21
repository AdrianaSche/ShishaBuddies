import Avatar from './Avatar'
import './SetupCard.css'

export default function SetupCard({ setup }) {
  return (
    <div className="setup-card">
      <h2>{setup.title}</h2>
      <p>{setup.avatar}</p>
      <Avatar src="https://thispersondoesnotexist.com/image" alt="bild" />
    </div>
  )
}
