import datetime
from django import forms
from django.forms import ModelForm
from SAF.models import Farm, PlanSAF
from leaflet.forms.widgets import LeafletWidget


class FarmForm(ModelForm):
    class Meta:
        model = Farm
        exclude = ('id', 'created_by',)
        widgets = {
            'name': forms.TextInput(attrs={'class': 'form-control', 'placeholder': 'Nombre de la finca'}),
            'location': LeafletWidget(),
        }


class PlanSAFForm(ModelForm):
    farm = forms.ModelChoiceField(queryset=Farm.objects.all(),
                                  empty_label="Seleciona uns finca",
                                  to_field_name="name",
                                  widget=forms.Select(
                                      attrs={'class': 'form-control'
                                             }
                                  )

                                  )

    class Meta:
        model = PlanSAF
        fields = '__all__'
        widgets = {
            'name': forms.TextInput(attrs={'class': 'form-control', 'placeholder': 'Nombre'}),
            'tasa_de_interes': forms.NumberInput(attrs={'class': 'form-control', 'placeholder': 'Tasa de interés'}),

            'tasa_minima_aceptable_de_rendimiento': forms.NumberInput(
                attrs={'class': 'form-control',
                       'placeholder': 'Tasa mínima aceptable de rendimiento'}),
            'tasa_de_Descuento_del_Proyecto': forms.NumberInput(
                attrs={'class': 'form-control',
                       'placeholder': 'Tasa de descuento del Proyecto'}),

            # 'farm': forms.Select(
            #                      attrs={'class': 'form-control'
            #                             })



        }
