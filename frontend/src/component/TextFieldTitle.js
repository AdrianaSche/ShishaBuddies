import styled from 'styled-components/macro'
import Label from './Label'

export default function TextFieldTitle(props) {
  return (
    <Wrapper>
      <Label {...props}>
        {props.title}
        <Input
          placeholder="Titel"
          type={props.type}
          value={props.value}
          onChange={props.onChange}
          name={props.name}
        />
      </Label>
    </Wrapper>
  )
}

const Input = styled.input`
  width: 100%;
  font-size: 1em;
  float: left;
  padding: var(--size-xs);
  padding-bottom: var(--size-l);
  margin-top: var(--size-xs);
  margin-bottom: var(--size-s);
  border-radius: var(--size-xs);
`
const Wrapper = styled.div`
  display: flex;
  justify-content: left;
  text-align: center;
`
