# Demo Openrewrite

1. Use spring-boot 2.7 base project using tag `base-spring-boot-2.7`

  ```bash
  git checkout base-spring-boot-2.7
  ```

2. Review openrewrite proposals

  ```bash
  mvn rewrite:dryRun
  ```

3. Apply openrewrite proposals

  ```bash
  mvn rewrite:run
  ```