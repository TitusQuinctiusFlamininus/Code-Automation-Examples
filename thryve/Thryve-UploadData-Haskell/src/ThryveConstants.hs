module ThryveConstants where

import ThryveTypes

import Data.Text    
import Data.Maybe
import qualified Data.Map as M


-- A Map of seemingly constant values
thryveConstants :: ThryveConstants
thryveConstants   =    M.fromList [("userCreationUrl","someUserCreationUrl"),
                                          ("uploadDataUrl","someDataUploadUrl"),
                                          ("downloadDataUrl","someDataDownloadUrl"),
                                          ("username","someUsername"),
                                          ("password","somePassword"),
                                          ("authID","someAuthID"),
                                          ("authSecret","someAuthSecrezt"),
                                          ("testHeight","167"),
                                          ("testWeight","75.6")
                                  ]

-- THRYVE USER HEALTH DATA JSON STRING
hData             =      "{\"height\": "++(fromJust $ M.lookup "testHeight" thryveConstants)++",\"weight\": "++(fromJust $ M.lookup "testWeight" thryveConstants)++",\"birthdate\": \"1974-09-14\",\"gender\": \"female\"}"
