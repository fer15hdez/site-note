from venv import create
from gymApp.models import Customer, Service, Enterprise, User, Visit,genderOption
from rest_framework import serializers


class CustomerSerializer(serializers.Serializer):
    personal_id = serializers.IntegerField()
    name = serializers.CharField(max_length=255)   
    dateJoined = serializers.DateTimeField() 
    gender = serializers.CharField(max_length=150)
    birthday = serializers.DateField()
    customer_id = serializers.PrimaryKeyRelatedField(
        many=True, 
        queryset=Entity.objects.all()) # Serializer ManyToOne relationship
    # The option "read_only=True" just allow read the field 
   
    class Meta:
        model = Customer
        fields = ['personal_id', 'name', 'lastname', 'lastname2',
                  'picture', 'dateJoined','gender', 'birthday', 
                  'service', 'enterprise'] # That are the field to serializer
        
    def create(self, validate_data)    :
        """
        Create and return a new 'Customer' instance, given the validated data.
        """
        return Customer.objects.create(**validate_data)
    
    def update(self, instance, validate_data):
        """
        Update and return an existing 'Customer' instance, given the validated data.
        """
        instance.personal_id = validate_data.get('personal_id',instance.personal_id)
        """
        ...
        And the rest of field
        """   
        instance.save()
        return instance      
    
class CustomerSerializer(serializers.ModelSerializer):    
    # "serializers.ModelSerializer" work like a formModel. That generate all the field
    # automatic, even relationship field.
     class Meta:
        model = Customer
        fields = ['personal_id', 'name', 'lastname', 'lastname2',
                  'picture', 'dateJoined','gender', 'birthday', 
                  'service', 'enterprise'] # That are the field to serializer
        

    
  