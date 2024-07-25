from django.db import models
from decimal import Decimal
from django.core.validators import MinValueValidator

class Customer(models.Model):
    id = models.IntegerField
    first_name   = models.CharField(max_length=300)
    last_name    = models.CharField(max_length=300)
    address      = models.CharField(max_length=600)

    def _str_(self):
        return self.id + " : " + self.last_name + " " + self.last_name
    
class LoanOffer(models.Model):
    id               = models.IntegerField
    customer         = models.ForeignKey(Customer, on_delete=models.CASCADE)
    loanAmount       = models.DecimalField(max_digits=10, decimal_places=2, validators=[MinValueValidator(Decimal('0.00'))])
    loanPeriod       = models.DecimalField(max_digits=5, decimal_places=0, validators=[MinValueValidator(Decimal('0.0'))]) 
    interestRate     = models.DecimalField(max_digits=4, decimal_places=2, validators=[MinValueValidator(Decimal('0.0'))])
    monthlyPayment   = models.DecimalField(max_digits=10, decimal_places=2, validators=[MinValueValidator(Decimal('0.00'))])                     

    def _str_(self):
        return self.loanAmount
