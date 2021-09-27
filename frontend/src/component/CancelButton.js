import styled from 'styled-components/macro'
import { css } from 'styled-components'

export default styled.button`
  padding: var(--size-m);
  background: black;
  border: 1px solid var(--accent);
  color: red;
  font-size: 1em;
  border-radius: var(--size-s);

  :disabled {
    border-color: var(--neutral-dark);
    background: var(--neutral-dark);
    color: var(--neutral-light);
  }

  ${props =>
    props.secondary &&
    css`
      background: none;
      color: red;
      border: 1px solid var(--accent);

      :disabled {
        border-color: var(--neutral-dark);
        background: none;
        color: var(--neutral-dark);
      }
    `}
`
