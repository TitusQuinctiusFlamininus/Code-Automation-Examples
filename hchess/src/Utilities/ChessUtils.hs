module Utilities.ChessUtils where

import Datatypes.ChessTypes
import Datatypes.ChessConstants

-- | Replicate some type a number of times
(<->) :: Int -> a -> [a]
(<->) = replicate 

--Filter out all locations (or cells) that are completely outside the 8x8 board
notOnBoard :: (Location -> RankOrFile) -> [Location] -> [Location]
notOnBoard f = filter (\k -> f k >= lBound) . filter (\k -> f k <= uBound)

-- | Check if any elements from one list appear in the second list; and return only those elements that
--   do not appear in the second list
--   First parameter = the list of all colored location
--   Second parameter = the list of all locations that a piece can move to
(>!<) :: [Location] -> [Location] -> [Location]
(>!<)   []   _    = []
(>!<)   _    []   = []
(>!<)   c (x:xs)
 | (x `elem` c) == True =      (>!<) c xs
 | otherwise            =  x : (>!<) c xs

-- | function to obtain the locations of all pieces of the same color, that are on the board (not including captured pieces) --   territory :: [BoardPiece] -> Color -> [Location]
territory [] _  = []
territory (x:xs) c
 | paintB x == c  =  locationB x : territory xs c
 | otherwise      =  territory xs c

-- | function to obtain the color from any boardpiece
paintB :: BoardPiece -> Color 
paintB (K p)  = color p 
paintB (MI p) = color p 
paintB (MA p) = color p 

-- | function to obtain the location from any boardpiece
locationB :: BoardPiece -> Location
locationB  (K p)  = location p 
locationB  (MI p) = location p 
locationB  (MA p) = location p 

-- | Zip two lists where:
--   First list is a list of functions (each representing a binary addition operation with one value already bound )
--   Second list is a list of spans
(|+|) :: (RankOrFile -> RankOrFile) -> [RankOrFile]
(|+|) h = zipWith ($) ((<->) uBound h) boardSpan

-- | Zip two lists where:
--   First list is a list of values (representing either files or ranks)
--   Second list is a list of spans
(|-|) :: RankOrFile -> [RankOrFile]
(|-|) g = zipWith (-) ((<->) uBound g) boardSpan 

-- | Raw possibilities across all cells, in an 8x8 space, regardless of whether the cells are on the board or not.
--   It determines unmodified possible moves of a piece, based on its current position. Some positions resulting may 
--   not even be on the board
(<-?->) :: Location -> PieceType -> [Location]
(<-?->) (f,r) t
 | t == KNIGHT = poss knFiles knRanks
 | t == KING   = poss kFiles kRanks
 | t == QUEEN  = bMoves ++ rMoves
 | t == BISHOP = bMoves
 | t == ROOK   = rMoves
 | otherwise   = [] 
                 where poss    = zipWith locZipper
                       knFiles = (zipWith ($) ((<->) 2 (+2) ++ (<->) 2 (+1)) $ (<->) 4 $ f) ++ (<->) 2 (f-2) ++ (<->) 2 (f-1)
                       knRanks = concat . (<->) 2 $ [(r+1), (r-1), (r+2), (r-2)]
                       bFiles  = concat . (<->) 2 $ ((|+|) (+f)) ++ ((|-|) f)
                       bRanks  = ((|+|) (+r)) ++ ((|-|) r) ++ ((|-|) r) ++ ((|+|) (+r))
                       rFiles  = ((|+|) (+f)) ++ ((|-|) f) ++ ((concat . (<->) 2) $ ((<->) uBound f))
                       rRanks  = ((concat . (<->) 2) $ ((<->) uBound r)) ++ ((|+|) (+r)) ++ ((|-|) r)
                       kFiles  = [f, f+1, f+1, f+1, f, f-1, f-1, f-1]
                       kRanks  = [r+1, r+1, r, r-1, r-1, r-1, r, r+1]
                       bMoves  = poss bFiles bRanks
                       rMoves  = poss rFiles rRanks
                                                           

-- | Filter out all locations outside the board, given as locations in the list 
(.<->.) :: [Location] -> [Location]
(.<->.) l = notOnBoard fst . notOnBoard snd $ l 

