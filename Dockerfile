# Ensure Main.class exists in this path: src/main/java/org/example

# open the OpenJDK image as the base image
FROM openjdk

# Create a new app directory for my application files
# RUN only applies to image; not to host machine
# Multiple RUN commands possible
RUN mkdir /app

# Copy the app files from host machine into new app directory
# first arg is for host machine (src/main/java), second arg is for the image (/app)
COPY src/main/java /app

# Set the directory for executing future commands
WORKDIR /app

# Run the Main class
# Must be in this path: src/main/java
CMD java org/example/Main

# Build image in terminal
# docker build -t qa-test:2.0 .
# -t specifies image name and tag
# 2.0 in qa-test:2.0 specifies version
# period at the end specifies current directory is where dockerfile is found

# To run image, go to correct directory: src/main/java
# docker run qa-test:2.0