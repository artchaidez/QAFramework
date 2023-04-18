# QAFramework
Java QA Framework

Download IntelliJ Community \
https://www.jetbrains.com/idea/download/#section=mac \
https://www.jetbrains.com/idea/download/#section=windows 

For reference, what was used when creating this project: \
Build System: Gradle \
JDK: Amazon Corretto 17.0.5 (Windows) \
     Eclipse Temurin 17.0.4 (Mac) \
Gradle DSL: Groovy 

# Instructions to run tests on Selenium Grid
Grid must first be created and correct images pulled. From here, use `docker run`, `docker compose`, or run yml file.
Can be run on Docker Desktop as well.

Add grid: 
```
docker network create grid
``` 
Remove grid (if needed): 
```
docker network rm grid
```

Runs on ==> http://localhost:4445/wd/hub

### Run on Windows/ Jenkins
https://github.com/SeleniumHQ/docker-selenium \
Pull correct images: 
```
docker pull selenium/hub
``` 
```
docker pull selenium/node-chrome
```

Docker run: 
```
docker run -d -p 4445:4444 --net grid --name selenium-hub-pc selenium/hub
``` 
```
docker run -d --net grid -e SE_EVENT_BUS_HOST=selenium-hub-pc 
--shm-size="2g" -e SE_EVENT_BUS_PUBLISH_PORT=4442 
-e SE_EVENT_BUS_SUBSCRIBE_PORT=4443 -e SE_NODE_MAX_SESSIONS=3 
-e SE_NODE_MAX_SESSIONS=3 selenium/node-chrome
```
Could also run yml files directly or use docker compose:
```
docker compose -f SetupSeleniumGridJenkins.yml up
```
```
docker compose -f SetupSeleniumGridJenkins.yml down
```

### Run on M1 Mac
https://github.com/seleniumhq-community/docker-seleniarm#experimental-mult-arch-aarch64armhfamd64-images

Need to use experimental Seleniarm Docker images on M1 \
Pull correct images: https://hub.docker.com/u/seleniarm 
```
docker pull seleniarm/hub
```
```
docker pull seleniarm/node-chromium
```

Docker run: 
```
docker run -d -p 4445:4444 --net grid --name seleniarm-hub-m1 seleniarm/hub:latest
```
```
docker run -d --net grid -e SE_EVENT_BUS_HOST=seleniarm-hub-m1 
--shm-size="2g" -e SE_EVENT_BUS_PUBLISH_PORT=4442 
-e SE_EVENT_BUS_SUBSCRIBE_PORT=4443 -e SE_NODE_MAX_SESSIONS=3 
-e SE_NODE_MAX_SESSIONS=3 seleniarm/node-chromium:latest
```

Could also run yml files directly or use docker compose:
```
docker compose -f SetupSeleniumGridM1.yml up
```
```
docker compose -f SetupSeleniumGridM1.yml down
```
