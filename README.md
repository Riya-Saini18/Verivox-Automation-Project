# Verivox-Automation-Project

The project automates 4 scenerios that are part of the assignment shared.

## Getting Started

### Dependencies

* JDK(latest version is preferred)
* Maven(```version-3.8.5```)

### Setting up the project

* Clone the project on local machine OR download the zipped file shared via email.
* Download and install JDK on your system(```JDK-18.0.1``` preferred).
* Download and install maven(```version-3.8.5```).
* Add 2 environment variables ```export MAVEN_HOME=~/apache-maven-3.8.5``` and ```export PATH=$PATH:$MAVEN_HOME/bin``` in any of the env file(eg. in ```~/.zshenv```); make sure to source the updated file (eg. ```source ~/.zshenv``` if ~/.zshenv is used).
* Check the maven version in your system ```maven --version```. It should be ```3.8.5```.

### Executing

* Switch to the directory where your project is present (```~/E2EProject```)
* run the following command:
```
mvn clean test -DsuiteXMLFile=testng.xml
```

## Author

Riya Saini(riyasaini18195@gmail.com)
