FROM jenkins/jenkins:lts

# Install plugins as code
COPY plugins.txt /usr/share/jenkins/ref/plugins.txt
RUN jenkins-plugin-cli --plugin-file /usr/share/jenkins/ref/plugins.txt

# Copy Configuration as Code files
COPY casc/ /usr/share/jenkins/ref/casc/

# Tell Jenkins where CASC config lives
ENV CASC_JENKINS_CONFIG=/usr/share/jenkins/ref/casc

EXPOSE 8080
