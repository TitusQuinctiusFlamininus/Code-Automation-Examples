from django.contrib import admin

from .models import Customer
from .models import LoanOffer

class CustomerAdmin(admin.ModelAdmin):
    list_display = ('first_name', 'last_name', 'address')

class LoanOfferAdmin(admin.ModelAdmin):
    list_display = ('customer', 'loanAmount', 'loanPeriod', 'interestRate', 'monthlyPayment')

admin.site.register(Customer, CustomerAdmin)
admin.site.register(LoanOffer, LoanOfferAdmin)