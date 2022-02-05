FROM maven:3.8.3-openjdk-11-slim AS build
RUN mkdir -p /workspace
WORKDIR /workspace
COPY pom.xml /workspace
COPY src /workspace/src
RUN echo quit | openssl s_client -showcerts -servername parlantos.com -connect parlantos.com:443 2>/dev/null | awk '/BEGIN/,/END/{ if(/BEGIN/){c=""};c=c $0 "\n"}END{print c}' >ca.crt || true
RUN keytool -importcert -alias startssl -keystore /usr/local/openjdk-11/lib/security/cacerts -storepass changeit -file ca.crt -noprompt
RUN mvn -B package --file pom.xml -DskipTests

FROM openjdk:11-slim
ARG SNOWFLAKE_BASE_URL
ENV snowflake_base_url=${SNOWFLAKE_BASE_URL}
ARG GUILD_BASE_URL
ENV guildDataService_base_url=${GUILD_BASE_URL}
ARG OKTA_TOKEN
ENV okta_token=${OKTA_TOKEN}
ARG OKTA_ORG_URL
ENV okta_org_url=${OKTA_ORG_URL}
ARG OKTA_ISSUER
ENV okta_issuer=${OKTA_ISSUER}
ARG OKTA_CLIENTID
ENV okta_clientId=${OKTA_CLIENTID}
ARG OKTA_CLIENTSECRET
ENV okta_clientSecret=${OKTA_CLIENTSECRET}
COPY --from=build /workspace/target/*.jar app.jar
EXPOSE 8083
ENTRYPOINT ["java","-Dserver.port=8083","-jar","app.jar"]
