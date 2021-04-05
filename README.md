# Selenium Java Scripts (not Javascript) for Remind Clone Client

## Setup

edit config.properties file to choose a browser (edge, chrome, firefox, ie, opera, safari, edgelegacy) (default is edge because I don't like chrome hehe).

install specific browser driver and make sure that you added it to PATH environment variable.

Use IntelliJ and you don't need to do anything, just do a cup of coffee while IntelliJ do everything for you :3

or if you don't want to play with JetBrain ultimate weapon, follow these:

### For Linux

Install maven

```sh
sudo apt install maven
```

Install project dependencies

```sh
mvn install
```

### For Windows

As for the folk using Windows, I am sorry. Please try to run the project on your own :3 Or just switch to Linux.

or... go go IntelliJ brr brr ヾ(•ω•`)o

### Setup Selenium Grid

- Download the Selenium Grid 3 jar file from [Selenium download page](https://selenium.dev/downloads/).
- Start the jar file as a hub in your local machine.

```sh
java -jar selenium-server-standalone.jar -role hub
```

- Create a new terminal instance and start the same jar file as a node. You can read more about node configurations [here](https://www.selenium.dev/documentation/en/grid/grid_3/setting_up_your_own_grid/)

```sh
java -jar selenium-server-standalone.jar -role node
```

- Change the `@BeforeClass` method of `AbstractTest` to test the right browser.

## Run the test

I am using VSCode and IntelliJ to write this script. There is a little button above each test class to run the test. Press it.

I rarely if ever use Maven in my life, so I don't understand how to start the test using the command line. :<
