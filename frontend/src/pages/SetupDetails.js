import Header from '../component/Header'
import Page from '../component/Page'
import { useHistory } from 'react-router-dom'
import Button from '../component/Button'
// eslint-disable-next-line
import { useState } from 'react'

export default function SetupDetails(props) {
  // eslint-disable-next-line
  const history = useHistory()

  const handleOnclick = () => history.push('/')
  return (
    <Page>
      <Header title="Dein heutiges Setup" />
      <p></p>
      <Button onClick={handleOnclick}>zurück</Button>
    </Page>
  )
}
