import styled from 'styled-components/macro'

export default styled.main`
  display: grid;
  grid-template-columns: 1fr 1fr;
  height: 100%;
  justify-content: center;
  padding: 5px;
  grid-gap: var(--size-m);
  overflow-y: scroll;
`
