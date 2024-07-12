module Main where

import Nurikabe

import Control.Monad.Trans.State.Lazy       (StateT, put, get, execStateT)
import Control.Monad.Trans.Reader           (ReaderT, runReaderT, ask)

main :: IO ()
main = do
 putStrLn "***********************************************************************************************************"
 putStrLn "===================================WELCOME TO NURIKABE (from Brainbashers)==============================================="
 putStrLn "***********************************************************************************************************"
 putStrLn ""
 putStrLn "Enter values already solved on the board in the format: 123,456 etc...."
 putStrLn " For example: 123,456 would imply: 2nd cell in 1st column is an island of length 3, 5th cell in the 4th column is an island of length 6, etc "
 putStrLn "(Note: Traverse the board from lower left cell, moving left to right for the bottom row, then the next row, etc....)"
 inputValues        <- getLine
 let readyboard     = setDefaultIslands [] (inputToDefault inputValues) $ createNuriBoard []
     trueislandlist = prepNuri (createBaseIslandList readyboard) readyboard
     strategy       = constructIslandStrategy trueislandlist in
     do
      (finalstrat,finalNurikabeSolution) <- execStateT (runReaderT checkNuri trueislandlist) (strategy,readyboard)
      if finalNurikabeSolution == [] then putStrLn "There was No Nurikabe Solution Found! (Recheck your islands...)"
      else do
         putStrLn "!§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§"
         putStrLn "!! WE FOUND A NURIKABE SOLUTION!!! "
         putStrLn "!§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§"
         putStrLn $ show (finalNurikabeSolution)++(show $ length finalNurikabeSolution)

