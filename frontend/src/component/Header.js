import styled from 'styled-components/macro'
export default function Header({ title }) {
  return (
    <Wrapper>
      <h1>{title}</h1>
    </Wrapper>
  )
}

const Wrapper = styled.header`
  width: 100%;
  text-align: center;
  background: var(--background-dark);
  color: var(--accent);
`
