{-# LANGUAGE OverloadedStrings #-}

module ThryveUtils where 


-- Our Stuff
import ThryveTypes
import ThryveConstants

--External imports
import Data.Maybe
import Network.HTTP.Simple              
import Network.HTTP.Client.Conduit     
import qualified Data.Map as M
import qualified Data.ByteString         as B
import qualified Data.ByteString.Char8   as C
import qualified Data.ByteString.Lazy    as L
import Data.Char                         (chr)
import Data.ByteString.Base64            (encode)
import Data.Time.Clock.POSIX             (getPOSIXTime)


-- Simplifying type signature dealing with forming a request
type SimpleThryveRequest  =    (Request -> ProtocolMethod -> ContentType -> Maybe ThryveRequestBody -> Maybe HeaderContent -> Request)

--Function that encodes any String into a readable Base64 String
-- This is needed for authentication credentials when being transmitted inside HTTP headers
encodeAsBase64         :: [Char] -> [Char]
encodeAsBase64          =  map (chr . fromEnum) . B.unpack . encode . createByteStream

-- Function to take a list of characters and return a bytestream
createByteStream       :: [Char] -> C.ByteString
createByteStream        =  C.pack

-- Funtional that will, Given some key, lookup the corresponding thryve constant value for it
checkFor :: [Char] -> [Char]
checkFor                = fromJust . (flip M.lookup) thryveConstants


--Value of the AUTHORIZATION HEADER
authorizationHeader    :: C.ByteString
authorizationHeader     =  createByteStream ("Basic "++encodeAsBase64 (checkFor "username"++":"++checkFor "password"))

--Value of the APP AUTHORIZATION HEADER
appAuthorizationHeader :: C.ByteString
appAuthorizationHeader  =  createByteStream ("Basic "++encodeAsBase64 (checkFor "authID"++":"++checkFor "authSecret"))

-- Function to obtain the Current Unix POSIX Time
findCurrentTime        :: IO UploadTimestamp
findCurrentTime         = ((round . (* 1000)) <$> getPOSIXTime) >>= (return . show)
                  
-- Function to Remove unwanted first and last square bracket character in the given list of chracters
flick :: [Char] -> L.ByteString
flick []                =  ""
flick xs                =  L.fromStrict . createByteStream . tail . init $ xs

--Function that forms the remainder of an HTTP Request
formRequest :: SimpleThryveRequest
formRequest r p c _ Nothing          =  let request   = setRequestMethod  p
                                             $ setRequestHeader "Content-Type"         [ c ]
                                             $ setRequestHeader "Authorization"        [authorizationHeader    ]
                                             $ setRequestHeader "AppAuthorization"     [appAuthorizationHeader ]
                                             $ setRequestSecure True
                                             $ setRequestPort 443
                                             $ r in request 
formRequest r p c (Just b) (Just h)  =  let request  = setRequestMethod  p
                                             $ setRequestHeader "Content-Type"         [ c ]
                                             $ setRequestHeader "Authorization"        [authorizationHeader    ]
                                             $ setRequestHeader "AppAuthorization"     [appAuthorizationHeader ]
                                             $ setRequestHeader "authenticationToken"  [ h ]
                                             $ setRequestBody (RequestBodyBS b)
                                             $ setRequestSecure True
                                             $ setRequestPort 443
                                             $ r in request
formRequest r _ _ Nothing (Just _)   = r
