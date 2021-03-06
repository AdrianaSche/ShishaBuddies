import { createGlobalStyle } from 'styled-components'

export default createGlobalStyle`

  :root {
    --background-dark: #111;
    --background-light: #222;
    --accent: #8FBC8F;
    --neutral-dark: #666;
    --neutral-light: #efefef;
    --error: var(--accent);

    --size-xs: 4px;
    --size-s: 8px;
    --size-m: 12px;
    --size-l: 16px;
    --size-xl: 24px;
    --size-xxl: 32px;
    --size-xxxl: 48px;
  }

  * {
    box-sizing: border-box;
  }

  html, body {
    margin: 0;
    font-family: monospace;
  }
`
