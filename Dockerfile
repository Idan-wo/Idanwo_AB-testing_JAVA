FROM ubuntu
LABEL authors="Ayokunle Sunmola <seayokunle@ualr.edu>"
RUN apt-get update
RUN apt-get install --yes software-properties-common
RUN add-apt-repository ppa:openjdk-r/ppa
RUN dpkg --purge --force-depends ca-certificates-java
RUN apt-get install -y ca-certificates-java
RUN apt-get install -y openjdk-17-jdk
RUN export JAVA_HOME=/usr/lib/jvm/openjdk-17-jdk
RUN export PATH=$PATH:$JAVA_HOME/bin
# RUN update-alternatives --config java
RUN apt-get install -y maven

COPY pom.xml /usr/local/service/pom.xml
COPY src /usr/local/service/src
COPY config /usr/local/service/config
WORKDIR /usr/local/service
# Install and setup
EXPOSE 1738
RUN mvn clean package -Dmaven.test.skip=true
RUN rm -rf /var/lib/apt/lists/*
RUN rm -rf /var/cache/apt/archives
RUN apt-get clean

CMD ["java", "-Dspring.profiles.active=prod", "-jar", "--enable-preview","/usr/local/service/target/Idanwo-0.0.1-SNAPSHOT.jar"]
