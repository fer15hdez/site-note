# Install  
*sudo apt install postgresql postgresql-contrib*  
*sudo -i -u postgres*: ("Change to user postgres. The user 'postgres' is a user system")  
*sudo -u postgres createuser --interactive*: ("Create a new user")  
*psql* ("To interact with postgres console. Open the postgres terminal ") 
*\q* ("Exit terminal postgres")

## Connect to server Postgres
*psql -h localhost -U postgres*

====
### Postgres Terminal 
*sudo -u postgres psql* ("Enter to postgres teerminal without switch to user postgres")
*\l* (Te muestra las bases de datos existentes.)  
*\connect [database_name]* ("Connect to a data base")   
*\d* (Te muestra las relaciones (tablas, secuencias, etc.) existentes en la base de datos.)      
*\d [nombre_tabla]* (Para ver la descripción (nombre de columnas, tipo de datos, etc.) de una tabla.)  
*\c [nombre_bd]* (Para conectarte a otra base de datos.)  
*SHOW search_path;* (Para ver la ruta de búsqueda actual.)  
*SET search_path TO [nombre_esquema];* (Para actualizar la ruta de búsqueda.)    
*\q* (Para salir de psql )  
*\du* (List all user)
    
**Note: Is necesary the semicolon at the end of each sentence**  
**Note: In postgres a role and a user are the same**   

### DataBAse
CREATE DATABASE name_db; ("Create a data base with name 'name_db' ")
*drop database test;* ("Delete the database")
*SELECT pg_terminate_backend(pg_stat_activity.pid)
    FROM pg_stat_activity
        WHERE datname = 'myDataBase'
            AND pid <> pg_backend_pid();* ("Close all the connection to the database")
    

### Set and change password 
CREATE USER user_name WITH PASSWORD '123admin'; ("Create user with password")
drop user user_name; ("Delete user")
SET USER admin WITH PASSWORD 'Admin123'; ("From console psql")  
ALTER USER admin WITH PASSWORD 'Admin123'; ("From console psql, change the password")  

### Permision and role
GRANT name_role TO user_name; ("Give permission to user_name on the role name_role")

### Defaul user and password
U: postgres  
P: postgres  

### Make a Backup to DB
*Is necesary to specify the host (-h)*  
*-W -> prompt the password option*  
*-v -> verbose option*  
*-U -> user*  
*-F -> format*  
pg_dump -v -U admin -W -h localhost -F t name_bd > /path/to/name_backup.tar    

