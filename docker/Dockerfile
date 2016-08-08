FROM daflockinger/archjava8:v0.1

RUN pacman -Sy --noconfirm unzip

WORKDIR /playfluffy1

ADD target/universal/playfluffy1-1.0-SNAPSHOT.zip /playfluffy1/

RUN unzip playfluffy1-1.0-SNAPSHOT.zip

EXPOSE 9000

ENTRYPOINT playfluffy1-1.0-SNAPSHOT/bin/playfluffy1 -Dconfig.file=/playfluffy1/conf/application.production.conf -Dplay.crypto.secret="8uKdLfn_r67kbWTh_IN;0Mlmq?0JZtmYMpwIy7PKEiMGZ73rAV@pJMWnugzL"
