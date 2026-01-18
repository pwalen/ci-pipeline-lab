FROM jenkins/jenkins:lts

# Install plugins as code
COPY plugins.txt /usr/share/jenkins/ref/plugins.txt
RUN jenkins-plugin-cli --plugin-file /usr/share/jenkins/ref/plugins.txt

EXPOSE 8080
