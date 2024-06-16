{-# LANGUAGE OverloadedStrings #-}

module ThryveUpload( endToEndTrial ) where

-- Our Libraries
import ThryveTypes
import ThryveConstants
import ThryveUtils

--External Helper Libraries
import           Data.Aeson
import           Network.HTTP.Simple
import           Data.Char             (chr)
import qualified Data.ByteString       as B


-- Entry function to the end-to-end test
endToEndTrial :: IO ()
endToEndTrial = do
        accToken <- generateThryveUser
        putStrLn $ "Thryve User Created : Access Token for Session : -> "++accToken


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
    response <- httpBS request
    return . map (chr . fromEnum) . B.unpack $ getResponseBody response



