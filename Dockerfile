# Use an appropriate base image
FROM openjdk:17-jdk-slim

# Set environment variables
ENV KEYCLOAK_USER=admin
ENV KEYCLOAK_PASSWORD=admin
ENV KEYCLOAK_ADMIN=admin
ENV KEYCLOAK_ADMIN_PASSWORD=admin

# Copy the local Keycloak distribution
COPY keycloak-25.0.2 /opt/keycloak

# Expose the default Keycloak port
EXPOSE 8080

# Start Keycloak
ENTRYPOINT ["/opt/keycloak/bin/kc.sh", "start-dev"]