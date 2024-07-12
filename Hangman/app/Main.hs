
{-# LANGUAGE ViewPatterns #-}


module Main where

import Hangman
import CHangman
import HangmanVisual

import Control.Comonad
import Data.Char                     (toUpper)
import Data.List                     (intersperse)
import qualified Data.HashMap as H   (empty)

-- improvements: -- see if we can use a comonad transformer with IO

solutionword = "mississippi"

-- | Comonadic version
main :: IO ()
main = do welcome
          let mask      = hideWords solutionword
              initHang  = HangStuff {g='#', c = Chances {ch = length hangover, uh = mask, sol = mapify solutionword }, idx = 0} in 
               do putStrLn ("You start with "++(show $ length hangover)++" Chances! ")
                  putStrLn ("Word Layout : ["++intersperse ' ' mask++"]")
                  runHangmanC initHang

-- | COMONADIC VERSION :: Function into the wonderful world of hangman
runHangmanC :: HangStuff Chances -> IO ()
runHangmanC ((any (== '_') .  uh . extract) -> False)  = putStrLn (saved++"    WORD =>["++solutionword++"]")
runHangmanC h                                          = 
             do guess <- gatherInput
                let h' = guessLetter' <<=  h { g = (<?) guess } in 
                         do showProgress (getgallows hangover (idx h')) (modProgress $ uh (extract h')) (ch (extract h')) 
                            case ch (extract h')of 
                                 0 -> putStrLn gameover>>putStrLn ("ANSWER => "++(toUpper <$> solutionword)) >>return ()
                                 _ -> if ch (extract h') == (ch $ extract h) then runHangmanC h' else runHangmanC $ up h'
                                               
            

-- | NON-MONADIC :: Function into the wonderful world of hangman 
runHangman :: HangWord -> HangStart -> Solution -> IO ()
runHangman ((any (== '_') .  uhang) -> False) _ _    = putStrLn (saved++"    WORD =>["++solutionword++"]")
runHangman h                                  s sol  = 
             do guess <- gatherInput
                let theguess = if guess == [] then '$' else (head guess)
                    (h',sol') = guessLetter (theguess, sol) h in 
                                 do  case chances h' == 0 of 
                                       True   -> do  putStrLn ((getgallows hangover s) ++ "    WORD WAS => "++
                                                              (toUpper <$> solutionword)++"")    
                                                     putStrLn gameover >> return ()
                                       False  -> case chances h' == chances h  of
                                                      True  -> do showProgress (getgallows hangover (s-1)) 
                                                                     (modProgress $ uhang h') (chances h')
                                                                  runHangman h' s     sol'
                                                      False -> do showProgress (getgallows hangover s) 
                                                                     (modProgress $ uhang h') (chances h')
                                                                  runHangman h' (s+1) sol'


                
{-
main :: IO ()
main = do welcome
          let mask      = hideWords solutionword
              initHang  = HangWord {uhang = mask, chances = length hangover} in 
               do putStrLn ("You start with "++(show $ length hangover)++" Chances! ")
                  putStrLn ("Word Layout : ["++intersperse ' ' mask++"]")
                  runHangman initHang 0 (mapify solutionword)
-}



