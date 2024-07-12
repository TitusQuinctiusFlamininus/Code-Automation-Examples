{-# LANGUAGE RecordWildCards #-}

module CHangman where

import Hangman
import Control.Comonad
import qualified Data.HashMap as H   (Map, toList, fromList)

-- | type to represent hang progress
data Chances        = Chances   { ch   ::  Int,
                                  uh   ::  [Char],
                                  sol  ::  H.Map [Int] Char
                                } 
-- | comonad computation context
data HangStuff a    = HangStuff { g    ::  Char,
                                  c    ::  a,
                                  idx  :: HangStart
                                } 
-- up :: a -> a
class Advance t where
    up   :: t -> t

-- fmap :: (a -> b) -> f a -> f b
instance Functor HangStuff where
    fmap f h@HangStuff {c = y}  = HangStuff {c = f y, idx = idx h, g = g h}
    
--  extract :: w a -> a
--  extend  :: w a -> (w a -> b) -> w b
instance Comonad HangStuff where
    extract     HangStuff {c = y}  = y
    extend            z k          = HangStuff {c = z k, idx = idx k, g = g k}

-- up :: HangStuff a -> HangStuff a    
instance Advance (HangStuff a) where
    up h   = HangStuff {idx = (idx h)+1, g = g h, c = c h}
    
-- COMONADIC VERSION:
-- | Formal function to process the guessed letter. UGuess represents the player's guess, 
--   the Solution represents a map of the dictionary word with character indices. Not making a guess (by 
--   pressing enter-key), should yield a ' ' as the guess, in which case we will just reject further processing
guessLetter' :: HangStuff Chances -> Chances
guessLetter' h  =
    case jury (g h) $ sol zoz of 
          Nothing       ->  Chances {ch   = (ch zoz) - 1, 
                                     uh   = uh zoz, 
                                     sol  = sol zoz
                                    } 
          Just (n, s')  ->  Chances { ch  = ch zoz,
                                      uh  = l ++ ((g h) : (drop 1 r)), 
                                      sol = s'
                                    }
                            where (l,r) = splitAt n $ uh zoz
    where zoz                           = extract h            