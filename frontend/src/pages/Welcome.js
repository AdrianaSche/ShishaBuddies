import Page from '../component/Page'
import styled from 'styled-components/macro'
import Loading from '../component/Loading'
import { useHistory } from 'react-router-dom'
import { useEffect } from 'react'

export default function Welcome() {
  const history = useHistory()

  useEffect(() => {
    const timer = setTimeout(() => history.push('/profile'), 2000)
    return () => clearTimeout(timer)
  }, [history])

  return (
    <PageLayout>
      <div>
        <h1>Welcome</h1>
        <Loading />
      </div>
    </PageLayout>
  )
}

const PageLayout = styled(Page)`
  grid-template-rows: 1fr;
  text-align: center;
`
