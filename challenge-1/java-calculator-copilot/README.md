# java-calculator-copilot

A demo calculator application using java-based API backend (Apache Struts 2)

## Usage

### Run Application

```
bin/run.sh
```

### Run Test

```
bin/test.sh
```

## Note (TODO)

There's a case that forwarded port includes `:8080` in the URL and results in `404` error on browser-codespaces. If this happens, the port part (`:8080`) needs to be removed for accessing the application.

- Example
  - Before Change: `https://xxxyyyyzzz-8080.app.github.dev:8080/index.html`
  - After Change: &nbsp;&nbsp;&nbsp;`https://xxxyyyyzzz-8080.app.github.dev/index.html`

## Scenarios / Target Files

- src/main/webapp/index.html
  - Add new button in `<!-- TODO: Buttons -->` section (ex. exponential button).
- src/main/webapp/default.css
  - Add button styling in `TODO` section (rounded button, bold/italic font, etc.).
- src/main/java/example/action/ArithmeticAction.java
  - Add new operation in `TODO: Add operation` (ex. `case "power":`, `case "sin":`, etc.)
- src/test/java/example/ArithmeticIntegrationTest.java
  - Add test case in the button in `TODO: add tests for subtraction` section (ex. define `class Subtraction`)

https://github.com/parroty-demo/java-calculator-copilot/assets/1172471/b787ab33-55cf-4fc0-af5d-8c056cfc5d20

## Links

- https://stackoverflow.com/questions/916285/cant-set-struts2-result-type-to-json
- https://kamegu.hateblo.jp/entry/struts2/json-result
