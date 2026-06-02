# -*- encoding: utf-8 -*-
"""
Copyright (c) 2019 - present AppSeed.us
"""

from django.urls import path, re_path
from apps.home import views
from apps.home.views import *

from django.conf.urls.static import static
from django.conf import settings
urlpatterns = [

    # The home page
    path('farm/', FarmCreateView.as_view(), name='home'),

    path('', views.indexFrontEnd, name='index_front_end'),


    # path('', UserProfileView.as_view(), name='user_profile'),
    path('farm/<int:pk>/parcela', FarmCreateView.as_view(), name='parcela'),
    path('farm/<int:pk>/<int:farm_pk>/parcela', FarmCreateView.as_view(), name='parcela_creada'),
    path('perfil/', UserProfileView.as_view(), name='user_profile'),
    path('farm/<int:pk>', FarmListView.as_view(), name='farm_list'),
    path('farm/<int:pk>/', FarmDetailView.as_view(), name='farm_detail'),
    path('farm/add/', FarmCreateView.as_view(), name='farm_create'),
    path('farm/<int:pk>/update/', FarmUpdateView.as_view(), name='farm_update'),
    path('farm/delete/', FarmDeleteView.as_view(), name='farm_delete'),

    # path('plansaf/', PlanSAFListView.as_view(), name='plansaf_list'),
    path('plantsaf/<int:pk>/create', PlanSAFCreateView.as_view(), name='plantsaf_create'),
    path('plantsaf/<int:pk>/', PlanSAFDetailView.as_view(), name='plantsaf_detail'),
    path('plantsaf/<int:pk>/update/', PlanSAFUpdateView.as_view(), name='plantsaf_update'),
    path('plantsaf/<int:pk>/delete/', PlanSAFDeleteView.as_view(), name='plantsaf_delete'),

    path('specie/', SpecieListView.as_view(), name='specie_list'),
    path('specie/<int:pk>/', SpecieDetailView.as_view(), name='specie_detail'),
    path('specie/add/', SpecieCreateView.as_view(), name='specie_create'),
    path('specie/<int:pk>/update/', SpecieUpdateView.as_view(), name='specie_update'),
    path('specie/<int:pk>/delete/', SpecieDeleteView.as_view(), name='specie_delete'),

    # path('intervention/', InterventionCreateView.as_view(), name='intervention_create'),
    path('intervention/<int:pk>/<int:id>/',  InterventionCreateView.as_view(), name='intervention_create'),
    path('intervention/<int:pk>/', InterventionDetailView.as_view(), name='intervention_detail'),
    path('intervention/<int:pk>/update/', InterventionUpdateView.as_view(), name='intervention_update'),
    path('intervention/<int:pk>/<int:id>/update/', InterventionIIUpdateView.as_view(), name='intervention_updatei'),
    path('intervention/densidad/', DensityCreateView.as_view(), name='densidad_create'),
    path('intervention/startupactivity/', views.startUpActivityCreate, name='startUpActivity_create'),
    path('intervention/activity/', views.activityCreate, name='intervention_activity_create'),
    path('intervention/supply/', views.startUpSupplyCreate, name='startUpSupply_create'),
    path('intervention/supply/product', views.supplyCreate, name='supply_product_create'),
    path('intervention/raleo/', SiembraRaleo.as_view(), name='siembra_raleo'),
    path('intervention/delete/', InterventionDeleteView.as_view(), name='intervention_delete'),
    path('intervention/product/', ObtenerProdEspView.as_view(), name='product_siembra'),
    path('intervention/productobj/', ObtenerProdObjView.as_view(), name='product_obj'),
    path('intervention/product/', ObtenerProdEspView.as_view(), name='product_siembra'),
    path('intervention/productobj/', ObtenerProdObjView.as_view(), name='product_obj'),
    path('intervention/produccion/', ProduccionCreateView.as_view(), name='produccion_create'),

                  # path('intervention/', AmazonsafCreateView.as_view(), name='amazonsaf_create'),
    # path('intervention/<int:pk>/',  AmazonsafCreateView.as_view(), name='amazonsaf_create'),
    # path('intervention/<int:pk>/', AmazonsafDetailView.as_view(), name='amazonsaf_detail'),
    # path('intervention/<int:pk>/update/', AmazonsafUpdateView.as_view(), name='amazonsaf_update'),
    # path('intervention/delete/', AmazonsafDeleteView.as_view(), name='amazonsaf_delete'),

    path('sowing/<int:pk>/', SowingCreateView.as_view(), name='sowing_create'),

    path('sowing/', SowingCreateView.as_view(), name='sowing_create'),
    # path('intervention/<int:pk>/', PlanSAFDetailView.as_view(), name='plantsaf_detail'),
    # path('intervention/<int:pk>/update/', PlanSAFUpdateView.as_view(), name='plantsaf_update'),
    # path('intervention/<int:pk>/delete/', PlanSAFDeleteView.as_view(), name='plantsaf_delete'),

    # path('finca/', PlanSAFListView.as_view(), name='plansaf_list'),

    path('finca/', FincaCreateView.as_view(), name='finca_create'),
    path('finca/creada/<int:pk>/', FincaCreateView.as_view(), name='finca_creada'),

    path('finca/<int:pk>/', FincaDetailView.as_view(), name='finca_detalle'),
    path('finca/<int:pk>/update/', FincaUpdateView.as_view(), name='finca_update'),
    path('finca/delete/', views.fincaDelete, name='finca_delete'),

    path('product/', ProductViewCreate.as_view(), name='product_create'),
    path('product/<int:pk>/', ProductDetailView.as_view(), name='product_detail'),
    path('product/<int:pk>/update/', ProductUpdateView.as_view(), name='product_update'),
    # path('finca/<int:pk>/delete/', FincaDeleteView.as_view(), name='finca_delete'),

    path('startupsupply/', StartupSupplyCreateView.as_view(), name='StartupSupply_create'),
    path('startupsupply/<int:pk>/', StartupSupplyDetailView.as_view(), name='StartupSupply_detail'),
    path('startupsupply/<int:pk>/update/', StartupSupplyUpdateView.as_view(), name='StartupSupply_update'),
    # path('startupsupply/<int:pk>/delete/', FincaDeleteView.as_view(), name='StartupSupply_delete'),

    path('startupactivity/', StartUpActivityCreateView.as_view(), name='StartupActivity_create'),
    # path('startupactivity/<int:pk>/', StartupSupplyDetailView.as_view(), name='StartupActivity_detail'),
    path('startupactivity/<int:pk>/update/', StartUpActivityUpdateView.as_view(), name='StartupActivity_update'),
    # path('startupactivity/<int:pk>/delete/', FincaDeleteView.as_view(), name='StartupActivity_delete'),
    path('charts/CarbonSaf/', ChartsCarbonSAF.as_view(), name='chartsCarbonSAF'),
    path('charts/AmazonSaf/', ChartsAmazonSAF.as_view(), name='chartsAmazonSAF'),
    path('charts/<int:pk>/<int:id>/CarbonSaf/', ChartsCarbonSAF.as_view(), name='chartsCarbonSAFIntervencion'),
    path('charts/<int:pk>/<int:id>/AmazonSaf/', ChartsAmazonSAF.as_view(), name='chartsAmazonSAFIntervencion'),
    path('charts/CalculosSAF/', ChartsCalculosSAF.as_view(), name='chartsCalculosSAF'),
    path('charts/CarbonSaf/calculo', pruebaCarbonsaf, name='pruebaCarbonSaf'),
    path('charts/CarbonSaf/<int:pk>/', ResultadosChartsCarbonSaf.as_view(), name='resultadosChartCarbonSaf'),
    path('glosary/', Glosary.as_view(), name='glosario'),


                  # Matches any html file
    re_path(r'^.*\.*', views.pages, name='pages'),

] + static(settings.MEDIA_URL, document_root=settings.MEDIA_ROOT)

