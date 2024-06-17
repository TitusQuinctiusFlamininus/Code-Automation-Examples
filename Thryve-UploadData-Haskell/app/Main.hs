{-# LANGUAGE OverloadedStrings #-}
{-# LANGUAGE DuplicateRecordFields #-}

module Main (main) where

import Data.Maybe
import Test.HUnit

import ThryveTypes
import ThryveUpload
import ThryveConstants


-- Program entry point 
main :: IO ()
main =  runTestTT tests >> return ()

-- We group tests like this and run them all or selectively
tests :: Test
tests = TestList [TestLabel "test1" test_Uploaded_Values_Matches_Downloaded_Values_Exactly]


-- Test the Positive Upload Case
test_Uploaded_Values_Matches_Downloaded_Values_Exactly :: Test
test_Uploaded_Values_Matches_Downloaded_Values_Exactly = TestCase (
    do healthData <- retrieveThryveCloudData
       putStrLn $ show healthData ++ "\n"
       assertBool "The Uploaded Weight Data Value does not seem to match the Downloaded Cloud Weight Data Value \n" 
            ((testWeight == (value . head . data' . head . dataSources . fromJust $ healthData)) &&
             (testHeight == (value . head . tail . data' . head . dataSources . fromJust $ healthData)))
       )
