from django.shortcuts import render
from rest_framework import viewsets
from .serializers import CustomerSerializer
from .serializers import LoanOfferSerializer
from .models import Customer
from .models import LoanOffer

# Create your views here.


class AllCustomersView(viewsets.ModelViewSet):
    serializer_class = CustomerSerializer
    queryset = Customer.objects.all()

class AllLoanOffersView(viewsets.ModelViewSet):
    serializer_class = LoanOfferSerializer
    queryset = LoanOffer.objects.all()