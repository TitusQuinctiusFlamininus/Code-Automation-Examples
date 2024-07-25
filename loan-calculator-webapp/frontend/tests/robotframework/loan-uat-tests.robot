*** Settings ***
Documentation     Testing of the Loan Amortization Web Application.
...
...               Keywords are imported from the resource file
Resource          keywords.resource
Default Tags      positive

*** Variables ***
${LOGIN URL}      http://localhost:3000/
${BROWSER}        Firefox

${LOAN_AMOUNT_FIELD_ID}                     loanAmount
${LOAN_PERIOD_FIELD_ID}                     loanPeriod
${INTEREST_RATE_FIELD_ID}                   interestRate
${CUSTOMER_DROPDOWN}                        customerSelect
${EXPECTED_MONTHLY_PAYMENT}                 "monthlyPayment": "2586.07"
${EXPECTED_ALERT_MESSAGE}                   A Customer must be Associated with Loan Offer!



*** Test Cases ***
Verify Loan Calculation Without Any Loan Details Cannot Be Performed
    Connect to Loan Amortization Portal
    Submit And Calculate Loan Payments
    Verify Text Does Not Appears on Page
    Disconnect all Browser Instance

Verify Loan Calculation With Only Loan Principal Cannot Be Performed
    Connect to Loan Amortization Portal
    Fill Input Field With Content
    Submit And Calculate Loan Payments
    Verify Text Does Not Appears on Page
    Disconnect all Browser Instance

Verify Loan Calculation With All Loan Details Except Associated Customer Cannot Be Performed
    Connect to Loan Amortization Portal
    Fill All Numerical Fields Content
    Submit And Calculate Loan Payments
    Verify Alert Present
    Disconnect all Browser Instance

Verify Loan Calculation With All Loan Details And Associated Customer Can Be Performed
    Connect to Loan Amortization Portal
    Fill All Numerical Fields Content
    Select Appropriate Loan Customer
    Submit And Calculate Loan Payments
    Verify Text Does Appears on Page
    Disconnect all Browser Instance