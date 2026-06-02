# Install
*sudo apt-get install [program]*
*--no-install-recommends*: solo instala los paquetes nombrados.   
*rm -rf /var/lib/apt/lists/\* * :  borra la lista de los paquetes que estan disponibles en repositorio.  
**Upgrade only one**
*sudo apt-get --only-upgrade install namePackage*: 
**Package .deb, .rpm, etc**
*sudo dpkg -i example.deb*

## Variable de entorno para proxy
**Configurar variables desde la linia de comando solo afecta a la sesion actual**
<code>export http_proxy=http://username:password@proxyserver.net:port/ </code>  
<code>printenv</code> -> Muestra todas las variables de entornos de la sesion.     
<code>printenv VARIBLE</code> -> Muestra el valor de la varible.  
<code>unset VAR </code> Resetea el valor de la varible a su valor original.  
<code>set</code> Muestra todas las varibles.  
<code>export PATH="$PATH:/opt/misprogramas"</code> En el archivo (~/.bashrc) se pone el codigo anterior. Esto solo afecta a la sesion usuario del actual.
La parte de la ruta '$PATH:' permite adicionar a los valores de $PATH el nuevo valor que se especifica.  
Para configurar variables para todo el sistema, es recomendable añadirlas a /etc/profile, /etc/bash.bashrc o /etc/environment

## Permission
The owner of a file can change the permissions for user ( u ), group ( g ), or others ( o ) by adding ( + ) or subtracting ( - ) the read, write, and execute permissions.
read ( r ), write(w), execute(x)  
-R (recursive option)  
`sudo chmod o=+rwx /var/www/html/`
## List permissions   
`ls -l /path/dir/`
The order is d(if is a dir) u(rwx)g(rwx)o(rwx)

- `Lectura (r) 4`:	Permite ver el contenido del archivo o listar un directorio.
- `Escritura (w) 2`:	Permite modificar el archivo o crear/borrar archivos en un directorio.
- `Ejecución (x) 1`:	Permite ejecutar un archivo como programa o entrar en un directorio.

    - 7 (4+2+1) = rwx (Acceso total)
    - 6 (4+2+0) = rw- (Lectura y escritura)
    - 5 (4+0+1) = r-x (Lectura y ejecución)
    - 4 (4+0+0) = r-- (Solo lectura)

### Esta cadena de 10 caracteres se divide así:
 Ej. cadena de permisos `-rwxr-xr--`  
 - El primer carácter: Indica el tipo ( - para archivo, d para directorio).
 - Los siguientes tres (rwx): Permisos del Propietario.
 - Los tres centrales (r-x): Permisos del Grupo.
 - Los tres finales (r--): Permisos de Otros.

***

## Descomprimir
**Extraer el archivo .tar.gz al directorio de trabajo actual:** <br>
<code>tar -xf filename.tar.gz</code><br>
Este comando extraerá (-x) el archivo (-f) especificado (en este caso, filename.tar.gz) al directorio actual. <br>
**Extraer el archivo .tar.gz a un directorio de trabajo diferente:**<br>
<code>tar -xf filename.tar.gz -C /home/user/files</code> <br>
**Comprimir con GZIP** <br>
<code>gzip filename.tar</code> <br>
**Descomprimir el archivo .tar.gz con gzip:** <br>
<code>gzip -d filename.tar</code> <br>
**Comprimir el archivo .tar y conservar la copia original:** <br>
<code>gzip -c filename.tar</code>
**Comprime el archivo .tar y guárdalo como un archivo diferente:** <br>
<code>gzip -c filename.tar > newfilename.tar.gz</code> <br>
**Comprimir varios archivos:** <br>
<code>gzip file1 file2</code> <br>
**Descomprimir múltiples archivos:** <br>
<code>gzip -d file1 file2</code> <br>
**Comprime todos los archivos de un directorio:** <br>
<code>gzip -r directory1</code><br>

****

# Gestión de usuarios y grupos en Linux
*nano /etc/group* : Show all the group 
In this document line by line show the gruop and user.  
*sambashare:x:129:lorenzo,pepe,juan* :  
*sambashare* : el nombre del grupo.  
*x* : una contraseña cifrada.  
*129* : el número de identificación del grupo **GID**  
*lorenzo,pepe,juan,* : es un listado de los usuarios que pertenecen al grupo separados por comas.  

### List the group 
*cut -d : -f 1 /etc/group*
### Show which group the user belongs to
*cat /etc/group | grep nameUser | cut -d: -f1*  

### Crear y eliminar grupos
*sudo groupadd [grupo]* : create a new group.  
*sudo groupadd [grupo1], [grupo2], [grupo3]* : create many groups.  
*sudo groupdel [group]* : delete a group.  

## USER
*nano /etc/passwd* : show all the users.  
In this document show all user, the structure is:  
*userName:x:1000:1000:userName,,,:/home/userName:/bin/bash*  
*userName* : es el alias del usuario que utiliza para registrarse.  
*x* : representa que la contraseña cifrada se encuentra en **/etc/shadow**  
*1000* : es el número de identificación del usuario **UID**.  
*1000* : representa el número de identificación del grupo principal al que pertenece el usuario, lo que se conoce como GID.  
*userName,,,* : es la información adicional que has proporcionado al crear la cuenta en cuestión.  
*/home/lorenzo* : es la ruta de inicio del nuevo usuario, *el hogar del usuario.*  
*/bin/bash* : es el shell que utiliza el usuario en cuestión.  

#### Show all the user
*cat /etc/passwd | cut -d: -f1*

### Crear y eliminar usuarios
sudo adduser [usuario]: create a user.  
sudo deluser [usuario]: delte a user.  

## Usuarios y grupos
*groups [usuario]*: show which group the user belongs to.  

### Add a user to a group
*sudo usermod -a -G [grupos] [usuario]*
### take out a user from a group
sudo deluser [usuario] [grupo]

***
## Free swap memory 
*sudo swapoff -a* : turn off and clean swap memory
*sudo swapon -a* : turn on swap memory


## SMB
#### smbclient  
Option -A: is to autenticate from a file, where are the param to log in.  
*smbclient  -A /file/where are param/smbclient_param  //10.7.1.67/Buzon\ Correos\ Seguridad(shared file)*  
**Format of parameter**  
username = fvelazquez  
password = MyPassword  
domain   = epepc.cupet.cu    

*smbclient -L server.ip   -U MyUsername -W domain*  

## SSH
*ssh user@ip.address –p7654*

## File
**df -h** -> "Disk Filesystem"  
**du** -> abreviación de "Disk Usage"  
**du** *[options]* *[location of directory or file]*  
**du -h /home/user/Desktop/ | sort –rn** -> ordenará todos los archivos y carpetas de mayor a menor.  
**du -h /home/user/Desktop | grep '^\s*[0-9\.]\+G'** -> todos los archivos mayores a 1 GB.M para mega.  
**du -h /home/user/Desktop/ --exclude="*.txt"** -> Excluir tipos de archivos.       

## Certificados
- `curl --insecure -vvI <url> 2>&1 | awk 'BEGIN { cert=0 } /^\* SSL connection/ { cert=1 } /^\*/ { if (cert) print }'` : Muestra la info 