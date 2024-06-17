module ThryveConstants where

-- ENDPOINT URIs
userCreationUrl   =      "https://qa.und-gesund.de/restjson/v5/accessToken"
uploadDataUrl     =      "https://qa.und-gesund.de/restjson/v5/userInformation"
downloadDataUrl   =      "https://qa.und-gesund.de/restjson/v5/dynamicEpochValues"

--USERNAME PASSWORD CREDENTIALS
username          =      "applicationtest-api"
password          =      "fyNbjcr5g9GUS5tE"

-- SECURE AUTHORIZATION CREDENTIALS
authID            =      "mmixU2uX6Q31mrIg"
authSecret        =      "5y81Oeht3YrO2ooQkCJVuqiXcVtbLmIVzXyBh21HSePqBCxL8riuYAp8pD3jQ1DW"

-- THRYVE USER HEALTH DATA HEIGHT AND WEIGHT PARAMETERS
testHeight        =      "167"
testWeight        =      "75.6"

-- THRYVE USER HEALTH DATA JSON STRING
hData             =      "{\"height\": "++testHeight++",\"weight\": "++testWeight++",\"birthdate\": \"1974-09-14\",\"gender\": \"female\"}"