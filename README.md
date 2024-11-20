# JVM Maven Example

This project kickstarts your work in a new JVM/Maven project.
It should get you building as quickly as possible - with best practices
and services baked in. It's designed for the Block Open Source Program, and may
be adapted for other contexts.

## Features

* Multimodule Maven project structure with 2 built-in modules: `api` and `impl`.
* [Hermit](https://cashapp.github.io/hermit/) managed environment (Java, Maven)
* GitHub Actions for Continuous Integration:
  * Testing in both MacOS and Ubuntu environments
  * Every commit to `main` published as `SNAPSHOT` in Block Artifactory
  * Static Analysis ([CodeQL](https://codeql.github.com/))
  * License scanning ([FOSSA](https://fossa.com/))
  * Security Vulnerability detection for dependencies ([FOSSA](https://fossa.com/))
  * Automatic dependency upgrades ([Renovate](https://github.com/renovatebot/renovate))
  * Release and Publishing to [Maven Central](https://central.sonatype.com/)

## Usage

To build a project using this template as a base:

1. [Create a new repository](https://github.com/organizations/block/repositories/new).

<img width="174" alt="image" src="https://github.com/user-attachments/assets/79660411-759b-4a64-9078-2b1740a9fc18">

2. Select
`block/example-jvm-maven` as the `Repository template`.

<img width="327" alt="image" src="https://github.com/user-attachments/assets/cc848e8a-1ec2-497c-878f-fc6af303a6f9">

## Preparing Your Project from This Template

There are a few steps you'll need to take from this
quickstart template to get your project ready for action.

### 🗺️ Change the Maven Coordinates

This example has `groupID` of `xyz.block` and `artifactId`s
of `example-jvm-maven-*`. You'll need to update all `pom.xml` files:

* `./pom.xml`
* `./api/pom.xml`
* `./impl/pom.xml`

...to reflect your desired `artifactId`s. Note that changing the `groupId` may
render your project unable to publish into Maven Central; the account we use
to publish is cleared for the `xyz.block` namespace.
Block employees may assistance by
[opening an internal issue](https://github.com/squareup/ospo/issues/new/choose) with the
Open Source Program Office if they need to use
a different `groupId`.

### 🔑 Get access to required GitHub Actions Secrets

The workflows on this project rely on GitHub Actions secrets,
environment variables, to run the build. The Open Source Program Office
team will, for security purposes, make these secrets available to your
project. The variables in question are:

* `SONATYPE_USERNAME` - A token tied to the Block OSS Sonatype account
* `SONATYPE_PASSWORD` - Password for the above
* `GPG_SECRET_KEY` - Key used for signing releases
* `GPG_SECRET_PASSPHRASE` - Passphrase for above

Your repo will also need access to the Artifactory repo for snapshot releases.

Block employees may request access to these by
[opening an internal issue](https://github.com/squareup/ospo/issues/new/choose) with the
Open Source Program Office.

### 🚀 Get Building

Go get 'em. 🤘🏻🤘🏼🤘🏽🤘🏾🤘🏿

Instructions are in [`CONTRIBUTING.md`](./CONTRIBUTING.md).

---

# README Template

This is the part you adapt for your project's audience.

This stub is meant to help you form a strong community around your work. It's yours to adapt, and may
diverge from this initial structure. Just keep the files seeded in this repo, and the rest is yours to evolve!

## Introduction

Orient users to the project here. This is a good place to start with an assumption
that the user knows very little - so start with the Big Picture and show how this
project fits into it.

Then maybe a dive into what this project does.

Diagrams and other visuals are helpful here. Perhaps code snippets showing usage.

Project leads should complete, alongside this `README`:

* [CODEOWNERS](./CODEOWNERS) - set project lead(s)
* [CONTRIBUTING.md](./CONTRIBUTING.md) - Fill out how to: install prereqs, build, test, run, access CI, chat, discuss, file issues
* [Bug-report.md](.github/ISSUE_TEMPLATE/bug-report.md) - Fill out `Assignees` add codeowners @names
* [config.yml](.github/ISSUE_TEMPLATE/config.yml) - remove "(/add your discord channel..)" and replace the url with your Discord channel if applicable

The other files in this template repo may be used as-is:

* [CODE_OF_CONDUCT.md](./CODE_OF_CONDUCT.md)
* [GOVERNANCE.md](./GOVERNANCE.md)
* [LICENSE](./LICENSE)

## Project Resources

| Resource                                   | Description                                                                    |
| ------------------------------------------ | ------------------------------------------------------------------------------ |
| [CODEOWNERS](./CODEOWNERS)                 | Outlines the project lead(s)                                                   |
| [CODE_OF_CONDUCT.md](./CODE_OF_CONDUCT.md) | Expected behavior for project contributors, promoting a welcoming environment |
| [CONTRIBUTING.md](./CONTRIBUTING.md)       | Developer guide to build, test, run, access CI, chat, discuss, file issues     |
| [GOVERNANCE.md](./GOVERNANCE.md)           | Project governance                                                             |
| [LICENSE](./LICENSE)                       | Apache License, Version 2.0                                                    |
