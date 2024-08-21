# Use an appropriate base image
FROM quay.io/keycloak/keycloak:21.1.1

# Set environment variables
ENV KEYCLOAK_ADMIN=admin
ENV KEYCLOAK_ADMIN_PASSWORD=admin

# Expose the default Keycloak port
EXPOSE 8080

# Start Keycloak in development mode
ENTRYPOINT ["/opt/keycloak/bin/kc.sh", "start-dev", "--http-port=8080"]
