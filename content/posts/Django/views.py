# -*- encoding: utf-8 -*-
"""
Copyright (c) 2019 - present AppSeed.us
"""
from itertools import count

from django import template
from django.contrib.auth.decorators import login_required
from django.http import HttpResponse, HttpResponseRedirect
from django.shortcuts import get_object_or_404
from django.template import loader
from django.urls import reverse, reverse_lazy
from django.views.generic import ListView, DetailView, CreateView, UpdateView, DeleteView
from SAF.models import Farm, PlanSAF, PlanSAF_Result
from SAF.forms import FarmForm

@login_required(login_url="/login/")
def index(request,*args, **kwargs):
    model = Farm
    form_class = FarmForm # Is defined to use a form_model in the view. You must still specify the model. Example FarmForm
    initial = {'key': 'value'}
    context = {'segment': 'index'}
    farm_list = Farm.objects.all()[:20]
    count_farm = Farm.objects.count()

    last_farm = Farm.objects.all().order_by("-id")
    planSAF_farm = PlanSAF.objects.all()
    list_pk_farm_tmp = []
    list_pk_farm = []
    for safFarm in planSAF_farm:
            list_pk_farm_tmp.append(safFarm.farm)
    for item in list_pk_farm_tmp:
        if item not in list_pk_farm:
            list_pk_farm.append(item)

    html_template = loader.get_template('home/index.html')
    form = form_class(initial=initial)
    return HttpResponse(html_template.render({'form': form,
                                              'farm_list': farm_list,
                                              'count_farm': count_farm,
                                              'last_farm': last_farm.count(),
                                              'count_farm_planSAF': len(list_pk_farm),
                                              'segment':'index'}, request))



class FarmListView(ListView):
    model = Farm # Model to list
    context_object_name = "farm_list" # The name to appear in the template
    paginate_by = 10 # For use in pagination
    template_name = 'home/farm_list.html'  # Spicifie the template


class FarmDetailView(DetailView):
    model = Farm
    context_object_name = "farm_datail"

    def get_queryset(self):
        self.Farm = get_object_or_404(Farm, name=self.kwargs['farm'])

        return PlanSAF_Result.objects.filter(planSAF=PlanSAF.objects.filter(farm=self.Farm))


# CreateView use myapp/name_entity_form.html or use template_name suffix
class FarmCreateView(LoginRequiredMixin, CreateView): # LoginRequiredMixin The user need to bee login.
    model = Farm
    fields = ['name']


    def get_context_data(self, **kwargs):
        initial = {'key': 'value'}
        form_class = FarmForm
        farm_list = Farm.objects.all()[:20]
        porciento_con = 0
        count_farm = Farm.objects.count()
        last_farm = Farm.objects.filter(created_by=self.request.user).order_by("-id")
        planSAF_farm = PlanSAF.objects.filter(created_by=self.request.user)
        list_pk_farm_tmp = []
        list_pk_farm = []
        for safFarm in planSAF_farm:
            list_pk_farm_tmp.append(safFarm.farm)
        for item in list_pk_farm_tmp:
            if item not in list_pk_farm:
                list_pk_farm.append(item)
        if count_farm > 0:
            porciento_con = (len(list_pk_farm) * 100) / count_farm
        form = form_class(initial=initial)
        context={'form': form,
         'farm_list': farm_list,
         'count_farm': count_farm,
         'last_farm': last_farm.count(),
         'porciento_con': porciento_con,
         'count_farm_planSAF': len(list_pk_farm),'segment': 'index'}
        return context

    def get_success_url(self):
        if self.request.method == 'POST' and "_continue" in self.request.POST:
            return reverse('home', kwargs={'pk': self.object.id})
        else:
            return reverse('home')

    def form_valid(self, form):
        form.instance.created_by = self.request.user # To assign the user loged in instance of form.

        return super().form_valid(form)
    self.request.user # The user loged

    def form_valid(self, form): # 
        form.instance.created_by = self.request.user # To auto insert the user that create the table

        return super().form_valid(form)    


# UpdateView use myapp/name_entity_form.html or use template_name suffix
class FarmUpdateView(UpdateView):
    model = Farm
    fields = ['name']


# DeleteView uses myapp/name_entity_confirm_delete.html or use template_name suffix
class FarmDeleteView(DeleteView):
    model = Farm
    success_url = reverse_lazy('farm_list')

# Inserting a lot data in a DB (Postgres)
NameOfEntity.objects.bulk_create(ListOfObjectToInsert)