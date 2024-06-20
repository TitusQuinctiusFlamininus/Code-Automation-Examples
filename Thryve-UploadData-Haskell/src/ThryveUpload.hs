{-# LANGUAGE OverloadedStrings #-}

module ThryveUpload( retrieveThryveCloudData ) where

-- Our Libraries
import ThryveTypes
import ThryveUtils
import ThryveConstants

--External Helper Libraries 
import            Network.HTTP.Simple
import            Control.Monad.IO.Class
import            Control.Monad.Trans.Class
import            Control.Monad.Trans.State.Lazy
import            Data.Aeson                         as A
import            Data.Char                          (chr)
import qualified  Data.ByteString                    as B




-- Entry function to the end-to-end test
retrieveThryveCloudData :: ThryveRest (Maybe ThryveHealthData) ThryveSession ThryveConstants
retrieveThryveCloudData = do
        generateThryveUser    >> lift get >>= (\k ->  liftIO . putStrLn $ "\n Thryve User Created : Access Token for Session : -> "++fst k++"\n ")     
        uploadThryveUserData  >> lift get >>= (\k' -> liftIO . putStrLn $ "Thryve User Health Data Uploaded at TimeStamp: -> "++snd k'++"\n ")
        downloadThryveUserData 



-- Function that accesses the Thryve Server and requests for a Thryve User
-- to be generated. An AccessToken type is returned
generateThryveUser :: ThryveRest (Maybe ThryveHealthData) ThryveSession ThryveConstants
generateThryveUser = do
    request' <- parseRequest ("POST "++ checkFor "userCreationUrl")
    let request = formRequest request' "POST" "application/x-www-form-urlencoded" Nothing Nothing
    resp <- liftIO $ httpBS request
    (lift $ put ((map (chr . fromEnum) . B.unpack . getResponseBody $ resp), [])) >> return Nothing
    

-- Function that Uploads the Thryve User's Health Data, represented as JSON
uploadThryveUserData :: ThryveRest (Maybe ThryveHealthData) ThryveSession ThryveConstants
uploadThryveUserData = do
    (accToken, _) <- lift get 
    liftIO findCurrentTime >>= (\t -> lift $ put (accToken, t)) 
    request'  <- parseRequest ("PUT "++checkFor "uploadDataUrl")
    let request = formRequest request' "PUT" "application/json; charset=utf-8" (Just . createByteStream $ hData) (Just . createByteStream $ accToken)
    httpBS request >>= (\c -> liftIO $ putStrLn ("Response Code After Upload: -> "++(show . getResponseStatusCode $ c))) >> return Nothing
                            
    
-- Function that Uploads the Thryve User's Health Data, represented as JSON
downloadThryveUserData ::  ThryveRest (Maybe ThryveHealthData) ThryveSession ThryveConstants
downloadThryveUserData= do
    (accToken, tstamp) <- lift get 
    request' <- parseRequest ("POST "++checkFor "downloadDataUrl")
    let request = formRequest request' "POST" "application/x-www-form-urlencoded"
                         (Just $ createByteStream ("authenticationToken="++accToken++"&createdAfterUnix="++tstamp)) (Just . createByteStream $ accToken)
    httpBS request >>= (return . A.decode . flick . map (chr . fromEnum) . B.unpack . getResponseBody)
    