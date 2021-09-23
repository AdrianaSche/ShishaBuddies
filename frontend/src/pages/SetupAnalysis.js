/*
import Header from '../component/Header'
import TextField from '../component/TextField'
import Page from '../component/Page'
import Main from '../component/Main'
import { useState } from 'react'
import { useAuth } from '../auth/AuthProvider'


export default function SetupAnalysis(){

  const{ token } = useAuth()
const [numOfHeads,setNumOfHeads] = useState()
  const[duration,setDuration] = useState()
  const[comment, setComment] =useState()

  function handleSubmit(event){
    event.preventDefault()
    //3 post-methoden ??
    //und im Backend 3 post-endpunkte bzw 3 get-Endpunkte?
  }

  //jedes Textfield mit Main wrappen und eigene
  //submit-fkt? handleSubmitComment, handleSubmitHead...
  return(
    <Page>
      <Header title="Bewerte Dein Setup!" />
      <Main as="form" onSubmit={handleSubmit}>
        <TextField
          title="Anzahl der Köpfe:"
          name="numberOfHookahHeads"
          value={}
          onChange={}
        />
        <TextField
          title="Rauchdauer:"
          name="smokingDuration"
          value={}
          onChange={}
        />
        <TextField
          title="Kommentar:"
          name="comment"
          value={}
          onChange={}
        />
        <TextField
          title="Setup count:"
          name="setupCount"
          value={}
          onChange={}
        />
        </Main>
      </Page>
  )
}*/
