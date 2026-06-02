# Create a EC2 (is like a virtual machine)

### Step 1: AWS account and EC2 instance Setup
Visit AWS Console  to create you account and log in. Now, follow simple steps to create an EC2 instance with Ubuntu 22.04 or other preferred OS.  

#### Step 1.1: Select EC2 from AWS Console and launch an instance
Launch is create a instance.  

#### Step 1.2: Setup and initialize your instance
In the setup of instance **is necesary to create** "un par de claves para conectarse de forma segura a la instancia". Is a file.  
***Need to be in a place where is posible to change the permision***, is preference in folder home.  

#### Step 1.3: Setup the security group so that http and https requests to your instance is allowed.
You may add rules like HTTP, HTTPS etc. or simply set it to allow All Traffic

### Step 2: Deploy Django Project on AWS EC2 instance
***Conect to ec2 via ssh***
ID de la instancia  
i-05b13746b3890713b (aws-django)  

    Abra un cliente SSH.  

    Localice el archivo de clave privada. La clave utilizada para lanzar esta instancia es aws-django-key.pem  

    Ejecute este comando, si es necesario, para garantizar que la clave no se pueda ver públicamente.  

 chmod 400 "aws-django-key.pem"  

Conéctese a la instancia mediante su DNS público:  

     ec2-54-167-87-209.compute-1.amazonaws.com  

Ejemplo:  
 ssh -i "aws-django-key.pem" ubuntu@ec2-54-167-87-209.compute-1.amazonaws.com  
#### Step 2.1: Installations on aws ec2 instance

Run the series of below commands after logging in to your Ubuntu instance via putty, ssh or other similar tools.  

sudo apt update  
sudo apt upgrade  
sudo apt install python3-pip  
pip install --upgrade pip             

With pip you may install django and other packages needed for your project like django-crispy-forms, djangorestframework, etc.  
Use of a virtualenv is also recommended  

pip install virtualenv  
virtualenv myenv  
source myenv/bin/activate  

If you have a requirements.txt file with all the project dependencies you may install it all via:  

pip install -r requirements.txt  

#### Step 2.2: Copy your django project code to aws ec2 instance
Run this command from your local command-line or terminal window:  

scp -i *.pem django_project_code.zip ubuntu@public_ip:~/  

Here, ubuntu is the user name. It may sometimes be ec2-user or root. If you have a password instead of the pem file you will be prompted  
to enter the same. -r option can used to recursively add all project file if you want to avpid the zip file. If you have used zip the used  
the below command to unzip:  

sudo apt install unzip  
unzip django_project_code.zip  


