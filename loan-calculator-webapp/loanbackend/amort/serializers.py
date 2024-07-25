from rest_framework import serializers
from .models import Customer
from .models import LoanOffer

class CustomerSerializer(serializers.ModelSerializer):
    class Meta:
        model = Customer
        fields = ('id', 'first_name', 'last_name', 'address')

class LoanOfferSerializer(serializers.ModelSerializer):
    class Meta:
        model = LoanOffer
        fields = ('id', 'customer', 'loanAmount', 'loanPeriod', 'interestRate', 'monthlyPayment')