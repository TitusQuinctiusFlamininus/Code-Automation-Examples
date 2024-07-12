module Datatypes.ChessConstants where

-- | The Rook, Knight and Bishop worths in a list
rkbWorths    = [5, 3, 3] :: [Int]


-- | The highest rank or file any piece can reach
uBound = 8 :: Int

-- | The lowest rank or file any piece can reach
lBound = 1 :: Int

-- | A list describing a full spectrum of location indexes (from 1 through 8, in either direction)
boardSpan = [lBound..uBound]