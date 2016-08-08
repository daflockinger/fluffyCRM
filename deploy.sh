docker stop playfluffy1
docker rm playfluffy1
docker rmi -f playfluffy1-1.0
./activator flywayMigrate -Dflyway.url=jdbc:mariadb://192.168.0.17:3306/playfluffy1
docker build -f docker/Dockerfile -t playfluffy1-1.0 .
docker run -d --name playfluffy1 playfluffy1-1.0  /bin/bash
