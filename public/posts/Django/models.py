from django.contrib.auth.models import User
from django.db import connection
from django.contrib.gis.db import models
from django.contrib.gis.geos import Point

from django.core.validators import MaxValueValidator, MinValueValidator

# Create your models here.


class Farm(models.Model):
    TIPO_TENENCIA = [
        ("example1", "example1"),
        ("example2", "example2"),
    ]
    
    name = models.CharField("Nombre", max_length=50) # With "Nombre" in quotation the class model can be anywhere in the file.  
    created_by = models.ForeignKey(User, on_delete=models.CASCADE) # Is auto_created the user who create the table. Need modify the method in the 
    # view form_valid
    location = models.PointField("Localizacion", srid=4326,blank=False,null=False)
    date_created = models.DateTimeField("Fecha de creado", auto_now_add=True) # Is auto_created the datetime when is created the table.
    unitary_price = models.FloatField("Precio Unitario")
    source = models.ForeignKey("Source", on_delete=models.CASCADE, default=None) # The Entity Source between "" is for model declared after the 
    # present model, that mean below.
    parameter = models.ManyToManyField(Parameter, verbose_name="nombre para mostrar") # Relationship Many To Many. The field is just putting class model. Automaticaly 
    # is created a new table with the two pk from the relantionship.
    tipo_tenencia = models.CharField("Tipo de tenencia", choices=TIPO_TENENCIA,max_length=150) # Choice option
    age = models.IntegerField("Edad", validators=[MaxValueValidator(100), MinValueValidator(1)]) # Int value. With validator param given a range of 
    # value (need to import "django.core.validators")

    class Meta: # Model metadata is "anything that's not a field"
        verbose_name = "Granja" # Show the name friendly to user in the admin template.


    def __str__(self): # Show the srt name of objet, need return a string. 
        return self.name

class Customer(User):    # (inheritance) The model Customer create a relation with model User (type: OneToOne)
    def custom_upload_to(instance, filename):
        """Defining what is the name of picture and where will be stored"""
        try:
            ext = instance.picture.name[-4:] # taking the extension of picture
            filename = str(instance.username) + "_" + str(instance.personal_id) + str(ext)
        except Customer.DoesNotExist:
            pass
        return 'img/customer/' + str(filename)
  
    picture = models.ImageField("Foto", blank=True, default=None, upload_to=custom_upload_to)

    def __str__(self):
        return self.first_name

    def __unicode__(self):
        return 


    






