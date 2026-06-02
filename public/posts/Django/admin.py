from django.contrib.gis import admin
from django.db import models
from leaflet.admin import LeafletGeoAdmin
from SAF.models import Farm
from import_export import fields, resources
from import_export.admin import ImportExportModelAdmin
from import_export.widgets import ForeignKeyWidget

# Register your models here.

from SAF import models



class GenderSpecieResource(resources.ModelResource):
    class Meta:
        model = models.GenderSpecie


class GenderSpecieAdmin(ImportExportModelAdmin):
    resource_class = GenderSpecieResource

class SpecieResource(resources.ModelResource):
    class Meta:
        model = models.Specie


class SpecieAdmin(ImportExportModelAdmin):
    resource_class = SpecieResource

class ParcelaAdmin(LeafletGeoAdmin):

    settings_overrides = {
       'DEFAULT_CENTER': (-10.033207, -77.044158),
    }
class FincaAdmin(LeafletGeoAdmin):

    settings_overrides = {
       'DEFAULT_CENTER': (-10.033207, -77.044158),
    }
# Register your models here.
admin.site.register(models.Finca,FincaAdmin)
admin.site.register(models.PlanSAF)
admin.site.register(models.PlanSAF_Result)
#admin.site.register(models.InterventionStartUpActivity)
admin.site.register(models.GenderSpecie,GenderSpecieAdmin)

