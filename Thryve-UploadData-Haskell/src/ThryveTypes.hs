{-# LANGUAGE DeriveGeneric  #-}
{-# LANGUAGE DeriveAnyClass #-}
{-# LANGUAGE OverloadedStrings #-}

module ThryveTypes where

import GHC.Generics
import Data.Aeson              
import Data.Text               


-- Type Synonyms used in the HTTP REST Flow
type AccessToken          =    [Char]
type UploadTimestamp      =    [Char]
type HealthData           =    [Char]

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

