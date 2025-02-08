# GitHub Actions - Maven Build and Test Workflow

This repository contains a GitHub Actions workflow that automatically builds and tests a Maven project whenever changes are pushed to the repository.

## Workflow Overview

### Features:
- Runs on every push to any branch.
- Uses Ubuntu as the runner.
- Checks out the repository code.
- Sets up JDK 11 using the Eclipse Temurin distribution.
- Runs `mvn clean verify` to build and test the project.
- Uploads test reports as an artifact.

## Workflow YAML File

The workflow is defined in `.github/workflows/maven-ci.yml` and includes the following steps:

```yaml
name: Maven Build and Test

on:
  push:
    branches:
      - main
      - '**'

jobs:
  build-and-test:
    runs-on: ubuntu-latest

    steps:
      - name: Checkout Repository
        uses: actions/checkout@v4

      - name: Set Up JDK 11
        uses: actions/setup-java@v3
        with:
          distribution: 'temurin'
          java-version: '11'

      - name: Build and Test
        run: mvn clean verify

      - name: Upload Test Report
        uses: actions/upload-artifact@v4
        with:
          name: test-reports
          path: target/cucumber-html-reports/*.html
```

## How It Works

1. **Trigger:** The workflow runs automatically when a push is made to any branch.
2. **Checkout Code:** The repository is checked out using `actions/checkout@v4`.
3. **Set Up Java:** The workflow installs JDK 11 using the Temurin distribution.
4. **Build & Test:** Executes `mvn clean verify` to build the project and run tests.
5. **Upload Reports:** The test results (XML files) are uploaded as an artifact.

## How to View Test Reports

1. Go to the **Actions** tab in GitHub repository.
2. Select the latest workflow run.
3. Find the **Artifacts** section and download the `test-reports`.

## Conclusion
This GitHub Actions workflow ensures that every push is tested automatically, maintaining code quality and catching issues early.