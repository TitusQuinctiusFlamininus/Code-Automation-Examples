*** Settings ***
Documentation     Simple example using SeleniumLibrary.
Library           SeleniumLibrary

*** Keywords ***

Connect to Loan Amortization Portal
    Open Browser    ${LOGIN URL}    ${BROWSER}

Disconnect all Browser Instance
    Close All Browsers


Fill Input Field With Content
    Sleep   1s
    SeleniumLibrary.Input Text     xpath://*[@id="${LOAN_AMOUNT_FIELD_ID}"]   45400
    Sleep   1s


Select Appropriate Loan Customer
    Sleep   1s
    SeleniumLibrary.Click Element     xpath://*[@id="${CUSTOMER_DROPDOWN}"]
    Sleep   1s
    SeleniumLibrary.Click Element     xpath://*[@id="customerSelect"]/option[2]
    Sleep   1s
    

Fill All Numerical Fields Content
    Sleep   1s
    Fill Input Field With Content
    Sleep   1s
    SeleniumLibrary.Input Text     xpath://*[@id="${LOAN_PERIOD_FIELD_ID}"]   18
    Sleep   1s
    SeleniumLibrary.Input Text     xpath://*[@id="${INTEREST_RATE_FIELD_ID}"]   3.17
    Sleep   1s

Submit And Calculate Loan Payments
    Sleep   1s
    SeleniumLibrary.Click Element  xpath://*[@id="formSubmissionDiv"]/form/button
    Sleep   1s


Verify Alert Present
    Sleep   1s
    Alert Should Be Present  ${EXPECTED_ALERT_MESSAGE} 
    Sleep   1s 


 Verify Text Does Not Appears on Page
     Sleep   1s
     SeleniumLibrary.Page Should Not Contain  ${EXPECTED_MONTHLY_PAYMENT}
     Sleep   1s

 Verify Text Does Appears on Page
     Sleep   1s
     SeleniumLibrary.Page Should Contain  ${EXPECTED_MONTHLY_PAYMENT}
     Sleep   1s