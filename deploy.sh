docker stop playfluffy1
docker rm playfluffy1
docker build -f docker/Dockerfile -t playfluffy1-1.0 .
docker run -d --name playfluffy1 playfluffy1-1.0
