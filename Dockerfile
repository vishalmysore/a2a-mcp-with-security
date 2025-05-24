FROM openjdk:18

# Set working directory
WORKDIR /ai

# Define the version as a build argument
ARG VERSION=0.0.3

# Download the JAR file using curl with the version variable
RUN curl -L -o /ai/mcpdemo.jar https://github.com/vishalmysore/a2a-mcp-with-security/releases/download/release/a2a-mcp-security-${VERSION}.jar

# Expose the port
EXPOSE 7860

# Copy the entrypoint script to the container
COPY entrypoint.sh /entrypoint.sh

# Make the script executable
RUN chmod +x /entrypoint.sh

# Set the entrypoint
ENTRYPOINT ["/entrypoint.sh"]