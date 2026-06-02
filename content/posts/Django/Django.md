Django
=======

CODE
-----
#### Make a project
    django-admin startproject nameOfProject 
#### Create an app   
    Python manage.py startapp nameOfApp 
#### Create a migration empty
    python manage.py makemigrations --empty yourAppName 
#### Migrate 
    python manage.py migrate [appName]
#### Create superuser
*python manage.py createsuperuser --username=joe --email=joe@example.com*


#### Run server
django-admin runserver [addrport]  
python manage.py  runserver [addrport]  
0.0.0.0 significa que se puede conectar desde cualquier ip.

#### Create superuser
python manage.py createsuperuser --username=joe --email=joe@example.com

