FROM openjdk:18-slim

# Set environment variable for WebDriverManager cache
ENV WDM_CACHE_PATH=/tmp/wdm-cache

# Set working directory
WORKDIR /ai

# Install dependencies
RUN apt-get update && apt-get install -y \
    curl unzip gnupg wget ca-certificates \
    fonts-liberation libappindicator3-1 libasound2 libatk-bridge2.0-0 \
    libatk1.0-0 libcups2 libdbus-1-3 libgdk-pixbuf2.0-0 libnspr4 libnss3 \
    libx11-xcb1 libxcomposite1 libxdamage1 libxrandr2 xdg-utils libu2f-udev \
    && apt-get clean && rm -rf /var/lib/apt/lists/*

# Install Google Chrome 120 (manually)
RUN wget -q https://dl.google.com/linux/direct/google-chrome-stable_current_amd64.deb && \
    apt-get update && apt-get install -y ./google-chrome-stable_current_amd64.deb && \
    rm google-chrome-stable_current_amd64.deb

# Install ChromeDriver 120.0.6099.224 (exact match for Chrome 120)
RUN CHROMEDRIVER_VERSION=120.0.6099.10900 && \
    curl -sSLO "https://storage.googleapis.com/chrome-for-testing-public/${CHROMEDRIVER_VERSION}/linux64/chromedriver-linux64.zip" && \
    unzip chromedriver-linux64.zip && \
    mv chromedriver-linux64/chromedriver /usr/local/bin/chromedriver && \
    chmod +x /usr/local/bin/chromedriver && \
    rm -rf chromedriver-linux64.zip chromedriver-linux64

# Download your application JAR
ARG VERSION=0.0.1
RUN curl -fsSL -o /ai/mcpdemo.jar https://repo1.maven.org/maven2/io/github/vishalmysore/a2a-mcp-security/${VERSION}/a2a-mcp-security-${VERSION}.jar

# Create WebDriverManager cache directory
RUN mkdir -p /tmp/wdm-cache && chmod -R 777 /tmp/wdm-cache

# Expose app port
EXPOSE 7860

# Copy entrypoint script
COPY entrypoint.sh /entrypoint.sh
RUN chmod +x /entrypoint.sh

# Start app
ENTRYPOINT ["/entrypoint.sh"]
