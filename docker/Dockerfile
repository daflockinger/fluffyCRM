FROM daflockinger/archjava8

EXPOSE 9000 8888

WORKDIR /playfluffy1

ADD target/universal/playfluffy1-1.0-SNAPSHOT.zip /playfluffy1/

RUN unzip playfluffy1-1.0-SNAPSHOT.zip

ENTRYPOINT playfluffy1-1.0-SNAPSHOT/bin/playfluffy1
