module ThryveConstants where

import ThryveTypes

import Data.Text    
import Data.Maybe
import qualified Data.Map as M


-- A Map of seemingly constant values
thryveConstants :: ThryveConstants
thryveConstants       =    M.fromList [("userCreationUrl","https://qa.und-gesund.de/restjson/v5/accessToken"),
                                          ("uploadDataUrl","https://qa.und-gesund.de/restjson/v5/userInformation"),
                                          ("downloadDataUrl","https://qa.und-gesund.de/restjson/v5/dynamicEpochValues"),
                                          ("username","applicationtest-api"),
                                          ("password","fyNbjcr5g9GUS5tE"),
                                          ("authID","mmixU2uX6Q31mrIg"),
                                          ("authSecret","5y81Oeht3YrO2ooQkCJVuqiXcVtbLmIVzXyBh21HSePqBCxL8riuYAp8pD3jQ1DW"),
                                          ("testHeight","167"),
                                          ("testWeight","75.6")
                                    ]

-- THRYVE USER HEALTH DATA JSON STRING
hData             =      "{\"height\": "++(fromJust $ M.lookup "testHeight" thryveConstants)++",\"weight\": "++(fromJust $ M.lookup "testWeight" thryveConstants)++",\"birthdate\": \"1974-09-14\",\"gender\": \"female\"}"