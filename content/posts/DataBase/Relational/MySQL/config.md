## Exportar una base de datos MySQL o MariaDB
<code>mysqldump -u nombre_usuario -p nombre_bbdd > nombre_archivo_dump.sql</code>

1. nombre_usuario hace referencia al nombre del usuario de la base de datos.
1. nombre_bbdd hay que sustituirlo por el nombre de la base de datos que quieres exportar.
1. nombre_archivo_dump.sql es el archivo que se generará con toda la información de la base de datos.

#### Export diferent table
<code>mysqldump -u nombre_usuario -p nombre_bbdd nombre_tabla_1 nombre_tabla_2 nombre_tabla_3 > nombre_archivo_dump.sql</code>

#### Create a db
<code>mysql -u root –p</code><br>
<code>mysql> CREATE DATABASE nueva_bbdd;</code>

### Import a db
<code>mysql -u nombre_usuario -p nueva_bbdd < nombre_archivo_dump.sql</code>