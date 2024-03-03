# rinha-backend-2024-q1

Para fazer o build da aplicação utilize:
```shell script
./mvnw package -Dnative -DskipTests "-Dquarkus.native.container-build=true" "-Dquarkus.native.builder-image=graalvm"
```

Para criação da imagem docker utilize:
```shell script
docker build -f src/main/docker/Dockerfile.native -t fabianoaono/rinha-2024-q1-java .
```

Para executar a aplicação utilize:
```shell script
docker compose up
```
