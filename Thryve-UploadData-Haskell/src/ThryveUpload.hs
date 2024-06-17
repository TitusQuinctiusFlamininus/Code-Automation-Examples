{-# LANGUAGE OverloadedStrings #-}

module ThryveUpload( endToEndTrial ) where

-- Our Libraries
import ThryveTypes
import ThryveConstants
import ThryveUtils

--External Helper Libraries 
import            Data.Maybe
import            Data.Aeson                    as A
import            Network.HTTP.Simple
import            Network.HTTP.Client.Conduit 
import            Data.Char                     (chr)
import qualified  Data.Text.Internal            as T
import qualified  Data.ByteString               as B


-- Entry function to the end-to-end test
endToEndTrial :: IO (Maybe ThryveHealthData)
endToEndTrial = do
        accToken <- generateThryveUser
        putStrLn $ "\n Thryve User Created : Access Token for Session : -> "++accToken++"\n "
        uTime <- uploadThryveUserData accToken 
        putStrLn $ "Thryve User Health Data Uploaded at TimeStamp: -> "++uTime++"\n "
        downloadThryveUserData accToken uTime >>= return . A.decode . flick


        
        

-- Function that accesses the Thryve Server and requests for a Thryve User
-- to be generated. An AccessToken type is returned
generateThryveUser :: IO AccessToken
generateThryveUser = do
    request' <- parseRequest ("POST "++userCreationUrl)
    let request
            = setRequestMethod "POST"
            $ setRequestHeader "Content-Type"     ["application/x-www-form-urlencoded"]
            $ setRequestHeader "Authorization"    [authorizationHeader                ]
            $ setRequestHeader "AppAuthorization" [appAuthorizationHeader             ]
            $ setRequestSecure True
            $ setRequestPort 443
            $ request'
    httpBS request >>= (return . map (chr . fromEnum) . B.unpack . getResponseBody)


-- Function that Uploads the Thryve User's Health Data, represented as JSON
uploadThryveUserData :: AccessToken -> IO UploadTimestamp
uploadThryveUserData accToken = do
    stampTime <- findCurrentTime
    request'  <- parseRequest ("PUT "++uploadDataUrl)
    let request
            = setRequestMethod "PUT"
            $ setRequestHeader "Content-Type"         ["application/json; charset=utf-8"  ]
            $ setRequestHeader "Authorization"        [authorizationHeader                ]
            $ setRequestHeader "AppAuthorization"     [appAuthorizationHeader             ]
            $ setRequestHeader "authenticationToken"  [createByteStream accToken          ]
            $ setRequestBody (RequestBodyBS . createByteStream $ hData)
            $ setRequestSecure True
            $ setRequestPort 443
            $ request'
    (httpBS request >>= (\c -> putStrLn ("Response Code After Upload: -> "++(show . getResponseStatusCode $ c)))) >> return stampTime 
    
-- Function that Uploads the Thryve User's Health Data, represented as JSON
downloadThryveUserData :: AccessToken -> UploadTimestamp -> IO HealthData
downloadThryveUserData accToken tstamp = do
    request' <- parseRequest ("POST "++downloadDataUrl)
    let request
            = setRequestMethod "POST"
            $ setRequestHeader "Content-Type"         ["application/x-www-form-urlencoded"  ]
            $ setRequestHeader "Authorization"        [authorizationHeader                  ]
            $ setRequestHeader "AppAuthorization"     [appAuthorizationHeader               ]
            $ setRequestHeader "authenticationToken"  [createByteStream accToken            ]
            $ setRequestBody (RequestBodyBS $ createByteStream ("authenticationToken="++accToken++"&createdAfterUnix="++tstamp))
            $ setRequestSecure True
            $ setRequestPort 443
            $ request'
    httpBS request >>= (return . map (chr . fromEnum) . B.unpack . getResponseBody)
    