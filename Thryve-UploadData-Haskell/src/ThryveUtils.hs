{-# LANGUAGE OverloadedStrings #-}

module ThryveUtils where 

import ThryveConstants

import Data.Char                       (chr)
import Data.ByteString.Base64          (encode)
import qualified Data.ByteString       as B
import qualified Data.ByteString.Char8 as C



--Function that encodes any String into a readable Base64 String
-- This is needed for authentication credentials when being transmitted inside HTTP headers
encodeAsBase64 :: [Char] -> [Char]
encodeAsBase64         = map (chr . fromEnum) . B.unpack . encode . C.pack


--Value of the AUTHORIZATION HEADER
authorizationHeader :: C.ByteString
authorizationHeader     = C.pack ("Basic "++encodeAsBase64 (username++":"++password))

--Value of the APP AUTHORIZATION HEADER
appAuthorizationHeader :: C.ByteString
appAuthorizationHeader  = C.pack ("Basic "++encodeAsBase64 (authID++":"++authSecret))