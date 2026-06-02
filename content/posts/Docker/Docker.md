# Docker
- To start the project is used the command "docker-compose" in the v2 is "docker compose"

## Manage imges

`docker image --help`: show all the command available.  
`build:` to build a image from a ***dockerfile***  : Ex. docker build -t nameImage:tag /path/dockerfile  
`history`: show the history of a image, how was build.  
`inspect`: detail and inside of a image. `sudo docker image inspect IMAGE ID`  
`docker image ls`: show all the download images.  
`docker image ls -a`: muestra imágenes intermedias.  
`-q`: para ver los números de identificación.  
`prune`:  borrar todas las imágenes que no estés utilizando.  
`docker image tag` **server:tagname** `newName/newName:latest` :  change the name of repository.  
`docker image tag` **idImage** `newName/newName:latest` :   change the name of repository.  
`docker image rm $(docker images --filter since=b721d1cdaac7 -q) -f`: Delete the image and the intermediate images  


### Descargar una imagen de un repositorio
```pull```: download images from a repository. Ex: ```docker image pull ubuntu```.  
```docker image pull ubuntu:xenial```: a specific version.  
```docker pull nameUser/nameImage```: son imagenes de usuarios.  
```push```: upload a image to a repository.  

```docker rmi <nombre de la imagen>```: to remove image.    
```docker rmi <nombre_imagen> <nombre_imagen> <nombre_imagen> ...```: elimianr varias imagenes. Puede ser con el ID o el nombre.  
```rm```:  to remove container.  

### Comandos básicos con imágenes  
```ls```: para saber las imágenes que tienes en tu equipo.   
  
### Save images
`sudo docker save nameImage | gzip > /path/nameImage.tar.gz`  
`sudo docker load  < ruta/nombrecontainercomprimido.tar`  
`sudo docker load -i  nameContainerOrImage.tar.gz`  


***

## Manage Container
```docker container --help```  
```docker run atareao/hola```: create and start a container.  
```docker ps```: show all the container.  
```docker container ls```: show all the container.  
```docker ps -a```: show all the container, even stoped.  
*Los contenedores se ejecutan solo mientras se ejecuta su comando predeterminado*
*(**Mantiene ejecutandose el contenedor**)Para iniciar una sesión pseudo-TTY con el contenedor, podemos usar el indicador **-t** . El contenedor no saldrá hasta que finalice la sesión.*

### Export and Import
*docker export [CONTAINER ID] > /home/export.tar* : Export
*cat /home/export.tar | docker import - some-name:latest*: Import 

### Naming Container
```docker run -d --name myContainer atareao/hola``` : Create a container.    
Si intentas llamar a dos contenedores por el mismo nombre, como te puedes imaginar, se producirá un error, y no iniciará el segundo contenedor.  

```docker container rename ID newName```: rename a container.  
*sleep 100* : stay the container runing. Puting in end of line. Ex: *docker run atareao/hola sleep 5*  
*-e*: allows passing environment variables.  
*--rm*: delete the container when it is stoped.  
*--network [nameOfNetwork]*: define a que red va a pertenecer

### Start, stop and pause
```docker start IDcontainer```:  run a previously created container.  
```docker stop IDcontainer```:  
```docker restart 1e0e92b8255e```:  
```docker pause 1e0e92b8255e```:  
```docker unpause 1e0e92b8255e```:  

`*-d` : ejecuta el container en 2do plano.  

### Listar contenedores
*Filtrados por estado*
`docker ps -a -f status=exited`

### Borrar una lista de contenedores
`docker rm $(docker ps -a -f status=exited -q)` : The param -q give the list to be raise.

### Copying between host and container
`docker cp archivo.txt midocker:/toDir`  
`docker cp midocker:archivo2.txt /toDirInHost`

### Exponiendo puertos
`docker run -d -it --name test01 -p 81:80 nginx:alpine`: option `-p` when [host port]:.[contianer port]. `-it` option to run container and iteract in the terminal.   

### Exponiendo volúmenes
`-v`: ex. `docker run -d -p  80:80 -p 443:443 -v "$(pwd)"/dir/host:/dir/of/docker --name nameContainer atareao/imageDocker`  
*Nota* : Para evitar problemas con **permisos** el dir del host se debe crear antes de iniciar el contenedor, sino docker crea el dir con permisos root.

### Inside to container
`docker exec -it midocker bash`: permite trabajar desde la terminal en el container.  
`exit`: salir del container.  

### Delete container
`docker rm midocker`: Delete one by one.  
`docker container prune`: Delete all stopped container.   

### Conecting container
`*--link,` añade una nueva entrada en el `/etc/hosts` que apunta al IP del contenedor identficado por `--link=CONTAINER_NAME`.  

## Run Specific image
### PostgreSQL
`docker run -d --name postgres -e POSTGRES_PASSWORD=mysecretpassword -p 5432:5432 postgres`

***

## Dockerfile
1. `ADD` copia un archivo del host al contenedor.  
1. `CMD` el agumento que pasas por defecto.  
1. `ENTRYPOINT` el comando que se ejecuta por defecto al arrancar el contenedor.  
1. `ENV` permite declarar una variable de entorno en el contenedor.  
1. `EXPOSE` abre un puerto del contenedor. Ex. *EXPOSE 8080*  or *EXPOSE 8080/udp*
1. `FROM` indica la imagen base que utilizarás para construir tu imagen personalizada. Esta opción es obligatoria, y además debe ser la primera instrucción del ***Dockerfile***.  
1. `MAINTAINER` es una valor opcional que te permite indicar quien es el que se encarga de mantener el ***Dockerfile***.  
1. `ONBUILD` te permite indicar un comando que se ejecutará cuando tu imagen sea utilizada para crear otra imagen.  
1. `RUN` ejecuta un comando y guarda el resultado como una nueva capa.  
1. `USER` define el usuario por defecto del contenedor.  
1. `VOLUME` crea un volumen que es compartido por los diferentes contenedores o con el *host*.  
1. `WORKDIR` define el directorio de trabajo para el contenedor.  

*Syntax* : docker build [OPTIONS] PATH | URL | -   
*Exmaple* :  `docker build -t tagName:latest .` (The dot is the path. It look for a Dockerfile file. It is posible define different path or link)

### Executing multiple command
`ENTRYPOINT ["bash", "./migrate.sh"]` (This command launches a Bash shell and executes the script in the "migrate.sh" file line by line.)  

***

## VOLUME 
`docker volume create mi_volumen`: Crear un volumen  
`docker volume ls`: Listar volumen  
`docker volume inspect mi_volumen` : Inspeccionar un volumen  
`docker volume rm mi_volumen`: Eliminar un volumen  
`docker volume prune`: Eliminar todos los volúmenes no usados  

***

## Docker Compose
### The file .yml
``` yml
version: '3'
services:
    container1:
        image: atareao/chiquito:composer
        ports:
            - "5000:5000"
    container2:
        image: mariadb
        ports:
            - "3306:3306"
        volumes:
            - ./data:/var/lib/mysql
        environment:
            MYSQL_ROOT_PASSWORD: password 
            
```
**volume** : volume_name:/path/in/container

### Basic command
`docker-compose up`: levanta los contenedores.  
`docker-composer up -d`: hace lo mismo que la instrucción pero en modo desvinculado, detached mode.  
`docker-compose ps`: te permite ver los contenedores que están en funcionamiento.  
`docker compose stop`: es la herramienta encargada de detener los diferentes contenedores.   
`docker-composer down`: detiene los contenedores.  

***

## Redes
Ex.: `docker network ls`  
`docker network inspect bridge`  
`docker network create --driver bridge red1`  
`docker network connect red1 nameContainer`  

`connect`: te permitirá conectar un contenedore a un red.  
`disconnect`: desconecta un contenedor de una red.  
`create`: es el comando que debes utilizar para crear una red.  
`inspect`: te permite obtener información detallada de una red.  
`ls`: es el comando a utilizar para ver las redes que tienes.  
`prune`: es el comando con el que borrará todas las redes que no estés utilizando.  
`rm`: te permite borrar una o mas redes.  

### Tipos de controladores de red en Docker
**driver:** este es el controlador por defecto. Es la mejor solución para conectar contenedores que se encuentran corriendo en el mismo anfitrión.  
**host:** este tipo de controlador elimina el aislamiento entre el contenedor y el anfitrión.  
**overlay:** Permite conectar diferentes contenedores en diferentes nodos.  
**macvila:** permite asingar una dirección MAC a un contenedor.  
**none:** inhabilita todas las redes.  
