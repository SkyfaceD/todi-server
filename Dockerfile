FROM openjdk:8-alpine

RUN mkdir /app

COPY ./build/install/todi/ /app/

WORKDIR /app/bin

CMD ["./todi"]