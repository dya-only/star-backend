FROM amazoncorretto:17-alpine as builder-jre

RUN apk add --no-cache --repository=http://dl-cdn.alpinelinux.org/alpine/edge/main/ binutils=2.41-r0

RUN $JAVA_HOME/bin/jlink \
  --module-path "$JAVA_HOME/jmods" \
  --verbose \
  --add-modules ALL-MODULE-PATH \
  --strip-debug \
  --no-man-pages \
  --no-header-files \
  --compress=2 \
  --output /jre

FROM alpine AS runtime

ENV JAVA_HOME=/jre
ENV PATH="$JAVA_HOME/bin:$PATH"
ARG JAR_FILE=example-0.0.1-SNAPSHOT.jar

COPY --from=builder-jre /jre $JAVA_HOME

WORKDIR /app

COPY ./build/libs/*.jar /app/app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/app.jar"]