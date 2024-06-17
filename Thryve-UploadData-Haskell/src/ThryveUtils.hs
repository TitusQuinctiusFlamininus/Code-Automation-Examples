{-# LANGUAGE OverloadedStrings #-}

module ThryveUtils where 

-- Our Stuff
import ThryveTypes
import ThryveConstants

--External imports
import qualified Data.ByteString         as B
import qualified Data.ByteString.Char8   as C
import Data.Char                         (chr)
import Data.ByteString.Base64            (encode)
import Data.Time.Clock.POSIX             (getPOSIXTime)



--Function that encodes any String into a readable Base64 String
-- This is needed for authentication credentials when being transmitted inside HTTP headers
encodeAsBase64         :: [Char] -> [Char]
encodeAsBase64          =  map (chr . fromEnum) . B.unpack . encode . C.pack

-- Function to take a list of characters and return a bytestream
createByteStream       :: [Char] -> C.ByteString
createByteStream        =  C.pack

--Value of the AUTHORIZATION HEADER
authorizationHeader    :: C.ByteString
authorizationHeader     =  createByteStream ("Basic "++encodeAsBase64 (username++":"++password))

--Value of the APP AUTHORIZATION HEADER
appAuthorizationHeader :: C.ByteString
appAuthorizationHeader  =  createByteStream ("Basic "++encodeAsBase64 (authID++":"++authSecret))

-- Function to obtain the Current Unix POSIX Time
findCurrentTime        :: IO UploadTimestamp
findCurrentTime         = ((round . (* 1000)) <$> getPOSIXTime) >>= (return . show)
                  