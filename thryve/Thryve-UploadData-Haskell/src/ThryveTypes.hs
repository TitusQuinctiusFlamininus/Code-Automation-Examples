{-# LANGUAGE DeriveGeneric  #-}
{-# LANGUAGE DeriveAnyClass #-}
{-# LANGUAGE OverloadedStrings #-}

module ThryveTypes where

import GHC.Generics
import Data.Aeson              
import Data.Text    
import Control.Monad.Trans.Reader
import Control.Monad.Trans.State.Lazy 
import qualified Data.Map                as M    
import qualified Data.ByteString.Char8   as C
      

-- HTTP RELATED TYPES
type ContentType           =    C.ByteString
type ProtocolMethod        =    C.ByteString
type HeaderContent         =    C.ByteString
type ThryveRequestBody     =    C.ByteString

-- Type Synonyms used in the HTTP REST Flow
type AccessToken           =    [Char]
type UploadTimestamp       =    [Char]
type HealthData            =    [Char]

-- Our General Generation MT Type
type ThryveRest a  s  r    =    ReaderT r (StateT s IO) a 

-- Our State MT synonym of user data as computations proceed
type ThryveSession         =    (AccessToken, UploadTimestamp)

-- Our representation of values that do not change
type ThryveConstants       =    M.Map [Char] [Char]


-- Defining a JSON format for a generic health record
data ThryveHealthData     =    ThryveHealthData {
                                    authenticationToken        :: String, 
                                    dataSources                :: [DataSource]
                                }   deriving (Show)

data DataSource           =     DataSource      {
                                     dataSource                 :: Int,
                                     data'                      :: [Data]
                                }   deriving (Show)

data Data                 =     Data             {
                                     startTimestampUnix         :: Integer,
                                     createdAtUnix              :: Integer,
                                     dynamicValueType           :: Int,
                                     value                      :: String,
                                     valueType                  :: String
                                }   deriving (Show)


-- Making all our custom types into Instances so we can absorb JSON
instance FromJSON ThryveHealthData where
  parseJSON = withObject "ThryveHealthData" $ \obj -> do
    authenticationToken <- obj .: "authenticationToken"
    dataSources         <- obj .: "dataSources"
    return (ThryveHealthData { authenticationToken = authenticationToken, dataSources = dataSources })

instance FromJSON DataSource where
  parseJSON = withObject "DataSource" $ \obj -> do
    dataSource         <- obj .: "dataSource"
    data'              <- obj .: "data"
    return (DataSource { dataSource = dataSource, data' = data' })

instance FromJSON Data where
  parseJSON = withObject "Data" $ \obj -> do
    startTimestampUnix  <- obj .: "startTimestampUnix"
    createdAtUnix       <- obj .: "createdAtUnix"
    dynamicValueType    <- obj .: "dynamicValueType"
    value               <- obj .: "value"
    valueType           <- obj .: "valueType"
    return (Data { startTimestampUnix = startTimestampUnix,createdAtUnix = createdAtUnix,
                   dynamicValueType = dynamicValueType, value = value, valueType = valueType})

