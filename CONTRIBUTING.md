# Contribution Guide

## Build Prerequisites

### Hermit

This project uses [Hermit](https://cashapp.github.io/hermit) to 
manage the environment like Maven and Java versions.
See [this page](https://cashapp.github.io/hermit/usage/get-started/) to set up Hermit on your machine - make sure to
download the open source build and activate it for the project.

Once you've installed Hermit and before running builds on this repo,
run from the root of this repo:

```shell
source ./bin/activate-hermit
```

This will set your environment up correctly in your terminal emulator.

## Building with Maven

This project is built with the
[Maven Project Management](https://maven.apache.org/) tool.
It is installed automatically via Hermit above.

If you want to build an artifact on your local filesystem, you can do so by running the
following command - either at the top level or in
any of the subprojects:

```shell
mvn clean verify
```

This will first clean all previous builds and compiled code, then:
compile, test, and build the artifacts in each of the submodules
of this project in the `$moduleName/target` directory.

If you'd like to skip packaging and test only, run:

```shell
mvn test
```

You may also run a single test; `cd` into the submodule of choice,
then use the `-Dtest=` parameter to denote which test to run, for example:

```shell
cd impl; \
mvn test -Dtest=TestClassName
```

To install builds into your local Maven repository, run from the root:

```shell
mvn install
```

For more, see the documentation on the [Maven Lifecycle](https://maven.apache.org/guides/introduction/introduction-to-the-lifecycle.html).

## Release

### SNAPSHOT Releases

In Java we use the SNAPSHOT convention to build and publish a 
pre-release package that can be consumed for 
preview/testing/development purposes.

These `SNAPSHOT`s are generated and published **AUTOMATICALLY** whenever
there's a new push to `main` or on a PR. The SNAPSHOT will be given
version with format `commit-$shortSHA-SNAPSHOT`, for example
`commit-00b12aa-SNAPSHOT` and is available from the
[Block OSS Snapshots Repository](https://blockxyz.jfrog.io/artifactory/block-oss-snapshots-maven2/).

If you want to manually kick that off to preview some changes introduced in a branch, or for some reason regenerate the same snapshot:

1. Open the `CI` Workflow on the `Actions` tab in GitHub, press the **Run workflow button** selecting the branch you want to generate the snapshot from.

<img width="404" alt="image" src="https://github.com/user-attachments/assets/08e1c3dc-b98a-4f83-b0a1-a70d8bf31ecb">

2. In the version field, insert the current version, a short meaningful identifier and the `-SNAPSHOT` suffix, ie:

  - 0.11.0.pr123-SNAPSHOT
  - 0.11.0.shortsha-SNAPSHOT
  - 0.11.0.fixsomething-SNAPSHOT

3. Run workflow!

You **MUST** use the `-SNAPSHOT` suffix, otherwise it's not a valid preview `SNAPSHOT` and it will be rejected.

`SNAPSHOT`s will be available in [Block's OSS Artifactory `block-oss-snapshots-maven2` Repository](https://blockxyz.jfrog.io/artifactory/block-oss-snapshots-maven2).
Consuming projects may bring in these `SNAPSHOT` 
dependencies by configuring the Block OSS Artifactory repo, 
for instance in `pom.xml` like:

```shell
<repositories>
  <repository>
    <id>block-oss-snapshots</id>
    <name>block-oss-snapshots</name>
    <releases>
      <enabled>false</enabled>
    </releases>
    <snapshots>
      <enabled>true</enabled>
    </snapshots>
    <url>https://blockxyz.jfrog.io/artifactory/block-oss-snapshots-maven2/</url>
  </repository>
</repositories>
```

...or, in Gradle's `gradle.settings.kts`, like:

```shell
dependencyResolutionManagement {
  repositories {
      mavenCentral()
      // Thirdparty dependencies of Block OSS projects not in Maven Central
      maven("https://blockxyz.jfrog.io/artifactory/block-oss-snapshots-maven2/")
  }
}
```

### Releasing and Publishing New Versions

To release a new version, execute the following steps:

1. Open the `Release and Publish` Workflow on the `Actions` tab in GitHub, press the **Run workflow button** selecting the branch you want to generate the snapshot from.

<img width="403" alt="image" src="https://github.com/user-attachments/assets/3b21ddea-87c5-4107-b080-339b3efe90ba">

2. In the version field, declare the version to be released. ie:

  - 0.15.2
  - 0.17.0-alpha-3
  - 1.6.3

**Choose an appropriate version number based on [semver](https://semver.org/) rules. Remember that versions are immutable once published to Maven Central; they cannot be altered or removed.**

3. Press the `Run workflow` button and leave the main branch selected (unless its a rare case where you don't want to build from the main branch for the release).

4. This:

- Builds
- Tests
- Creates artifacts for binaries and sources
- Signs artifacts with GPG Key
- Tags git with release number "v$version"
- Keeps development version in the pom.xml to 0.0.0-main-SNAPSHOT
- Pushes changes to git
- Publishes to Maven Central
- Creates GitHub Release "v$version"

### Publishing a `SNAPSHOT` from a Local Dev Machine

Please take care to only publish `-SNAPSHOT` builds (ie.
when the `<version>` field of the `pom.xml` ends in
`-SNAPSHOT`.) unless there's good reason
to deploy a non-`SNAPSHOT` release. Releases are typically handled via automation
in GitHub Actions s documented above.

To deploy to Block's Artifactory instance for sharing with others, you
need your Artifactory username and password handy (available to Block-employed engineers).
Set environment variables:

```shell
export ARTIFACTORY_USERNAME=yourUsername; \
export ARTIFACTORY_PASSWORD=yourPassword
```

...then run:

```shell
mvn deploy --settings .maven_settings.xml
```

## Communications

### Issues

Anyone from the community is welcome (and encouraged!) to raise issues via 
GitHub Issues on this repo.

### Discussions

Design discussions and proposals take place in GitHub Issues, above.

We advocate an asynchronous, written debate model - 
so write up your thoughts and invite the community to join in!

### Continuous Integration

Build and Test cycles are run on every commit to every branch on GitHub Actions.

## Contribution

We review contributions to the codebase via GitHub's Pull Request mechanism. We have 
the following guidelines to ease your experience and help our leads respond quickly 
to your valuable work:

* Start by proposing a change either in Issues (most appropriate for small 
  change requests or bug fixes) or in Discussions (most appropriate for design 
  and architecture considerations, proposing a new feature, or where you'd 
  like insight and feedback)
* Cultivate consensus around your ideas; the project leads will help you 
  pre-flight how beneficial the proposal might be to the project. Developing early 
  buy-in will help others understand what you're looking to do, and give you a 
  greater chance of your contributions making it into the codebase! No one wants to 
  see work done in an area that's unlikely to be incorporated into the codebase.
* Fork the repo into your own namespace/remote
* Work in a dedicated feature branch. Atlassian wrote a great 
  [description of this workflow](https://www.atlassian.com/git/tutorials/comparing-workflows/feature-branch-workflow)
* When you're ready to offer your work to the project, first:
* Squash your commits into a single one (or an appropriate small number of commits), and 
  rebase atop the upstream `main` branch. This will limit the potential for merge 
  conflicts during review, and helps keep the audit trail clean. A good writeup for 
  how this is done is 
  [here](https://medium.com/@slamflipstrom/a-beginners-guide-to-squashing-commits-with-git-rebase-8185cf6e62ec), and if you're 
  having trouble - feel free to ask a member or the community for help or leave the commits as-is, and flag that you'd like 
  rebasing assistance in your PR! We're here to support you.
* Open a PR in the project to bring in the code from your feature branch.
* The maintainers noted in the `CODEOWNERS` file will review your PR and optionally 
  open a discussion about its contents before moving forward.
* Remain responsive to follow-up questions, be open to making requested changes, and...
  You're a contributor!
* And remember to respect everyone in our global development community. Guidelines 
  are established in our `CODE_OF_CONDUCT.md`.
