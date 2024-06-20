{-# LANGUAGE OverloadedStrings #-}
{-# LANGUAGE DuplicateRecordFields #-}

module Main (main) where

import Test.HUnit
import Control.Monad.Trans.Reader     (runReaderT)
import Control.Monad.Trans.State.Lazy (evalStateT)

import ThryveUtils
import ThryveTypes
import ThryveUpload
import ThryveConstants


-- Program entry point 
main :: IO ()
main =  runTestTT tests >> return ()

-- We group tests like this and run them all or selectively
tests :: Test
tests = TestList [TestLabel "verifyUserDataMatches" test_Uploaded_Values_Matches_Downloaded_Values_Exactly]


-- Test the Positive Upload Case
test_Uploaded_Values_Matches_Downloaded_Values_Exactly :: Test
test_Uploaded_Values_Matches_Downloaded_Values_Exactly = TestCase (
    do healthData <- evalStateT (runReaderT retrieveThryveCloudData thryveConstants) ([], [])
       putStrLn $ "DOWNLOADED USER HEALTH DATA: \n"++show healthData ++ "\n"
       case healthData of 
        Nothing -> assertFailure "We were meant to get a User Health Record, but either data was missing or malformed!"
        Just d  -> assertBool "The Uploaded Weight/Height Data Value does not seem to match the Downloaded Cloud Weight/Height Data Value \n" 
                    ((checkFor "testWeight" == (value . head . data' . head . dataSources $ d)) &&
                    (checkFor "testHeight" == (value . head . tail . data' . head . dataSources $ d)))
       )
