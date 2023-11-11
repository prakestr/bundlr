FROM jenkins/jenkins:lts
USER root

# Install Docker CLI in the Jenkins container
RUN curl -sSL https://get.docker.com/ | sh

# Install Chrome for Selenium tests
RUN apt-get update -qqy \
  && apt-get -qqy install \
    libxi6 \
    libgconf-2-4 \
    libnss3-dev \
    fonts-liberation \
    xdg-utils \
    libappindicator3-1 \
    libasound2 \
    libatk-bridge2.0-0 \
    libatspi2.0-0 \
    libgtk-3-0 \
    libxss1 \
    libxtst6 \
    lsb-release \
    xauth \
    xvfb \
    wget \
    gnupg2 \
    && wget https://dl.google.com/linux/direct/google-chrome-stable_current_amd64.deb \
    && dpkg -i google-chrome-stable_current_amd64.deb; apt-get -fy install

# Clean up
RUN apt-get clean && rm -rf /var/lib/apt/lists/* /var/cache/apt/*

USER jenkins

