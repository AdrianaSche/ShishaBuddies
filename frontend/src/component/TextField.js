import styled from 'styled-components/macro'
import Label from './Label'

export default function TextField(props) {
  return (
    <Label {...props}>
      {props.title}
      <Input
        type={props.type}
        value={props.value}
        onChange={props.onChange}
        name={props.name}
      />
    </Label>
  )
}

const Input = styled.input`
  width: 100%;
  font-size: 1em;
  padding: var(--size-xs);
  margin-top: var(--size-xs);
  border-radius: var(--size-xs);
`
