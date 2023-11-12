FROM jenkins/jenkins:lts
USER root

# Install Docker CLI in the Jenkins container
RUN curl -sSL https://get.docker.com/ | sh

# Clean up
RUN apt-get clean && rm -rf /var/lib/apt/lists/* /var/cache/apt/*

USER jenkins
