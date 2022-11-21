FROM ubuntu:18.04

LABEL authors="Ayokunle Sunmola <seayokunle@ualr.edu>"

RUN apt-get update
RUN apt-get install --yes software-properties-common
RUN add-apt-repository ppa:openjdk-r/ppa
RUN apt-get install -y openjdk-14-jdk
RUN export JAVA_HOME=/usr/lib/jvm/openjdk-14-jdk
RUN export PATH=$PATH:$JAVA_HOME/bin
RUN update-alternatives --config java
RUN apt-get install -y maven
COPY pom.xml /usr/local/service/pom.xml
COPY src /usr/local/service/src
COPY config /usr/local/service/config
#COPY others /usr/local/service/others
#COPY auto /usr/local/service/auto
#COPY migration-files /usr/local/service/migration-files
WORKDIR /usr/local/service
# Install and setup
RUN mvn clean package -Dmaven.test.skip=true

CMD ["java", "-Dspring.profiles.active=base,live", "-jar", "/usr/local/service/target/Blogtracker-0.0.1-SNAPSHOT.jar"]
