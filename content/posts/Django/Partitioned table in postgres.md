### path/of/project/setting.py
 <pre><code>
 INSTALLED_APPS = [
    #'logs_analysis.analysis_squid_kerio',
    'analysis_squid_kerio',
    'django.contrib.admin',
    'django.contrib.auth',
    'django.contrib.contenttypes',
    'django.contrib.sessions',
    'django.contrib.messages',
    'django.contrib.staticfiles',

    "django.contrib.postgres", 
    "psqlextra",] 
</code></pre>
The last two line need to be added
 
## Model 
    from psqlextra.types import PostgresPartitioningMethod
    from psqlextra.models import PostgresPartitionedModel

<pre>
<code>
class LogsKerioPartitioned(PostgresPartitionedModel):
    class PartitioningMeta:
        method = PostgresPartitioningMethod.RANGE
        key = ["date_time"]
    ip_addres = models.PositiveBigIntegerField("IP")
    user_name = models.CharField("Usuario", max_length=200)
    date_time = models.DateTimeField("Fecha")
    time_zone = models.CharField("Zona horario", max_length=100)
    http_method = models.CharField("Metodo http", max_length=50)
    url = models.CharField("Url", max_length=10000)
    
    def __str__(self):
        return str(self.ip_addres) + str(': ') + str(self.user_name)

</code>
</pre>

## Create the migration with ***python manage.py pgmakemigrations***. 
Next, create some empty migration files - one for each partition.
You can create an empty migration with ***python manage.py makemigrations --empty yourappname***. Then, use ***django-postgres-extra*** to set up the migrations:
<pre><code>
from psqlextra.backend.migrations.operations import PostgresAddRangePartition

class Migration(migrations.Migration):
    dependencies = [
        ('people', '0001_initial'),
    ]
    
    operations = [
        PostgresAddRangePartition(
           model_name="person",
           name="people_partitioned_birthdays_1800_to_1850",
           from_values='1800-01-01',
           to_values='1850-12-31',
        ),
    ]
</code></pre>

Again, **you'll need to create one of these for every partition you need**.

### Creating a migration to delete one of your partitions is basically the same:
<pre><code>
from django.db import migrations, models

from psqlextra.migrations.operations import PostgresDeleteListPartition

class Migration(migrations.Migration):
    operations = [
        PostgresDeleteListPartition(
           model_name="person",
           name="people_partitioned_birthdays_1800_to_1850",
        ),
    ]
</code></pre>