module Main where

import Datatypes.ChessPieces
import Datatypes.ChessTypes
import Utilities.ChessUtils


main :: IO ()
main = do mapM_ (putStrLn . show) setChessBoard
          putStrLn "PROMOTING A PAWN TO A ROOK"    
          putStrLn $ show $ promote (head allMinorPieces) (head allMajorPieces)
          --putStrLn $ show $ promote (head allMajorPieces) (head allMajorPieces)
          --putStrLn $ show $ promote (head allMajorPieces) (head allMinorPieces)


--Determine the actual moves possible for any piece, from any current position.
--First Parameter  ::  The piece that wants to make a move
--Second Parameter ::  The board as it stands currently
anyPieceMoves :: Piece a -> [BoardPiece] -> [Location]
anyPieceMoves p b = (.<->.) . (>!<) c . (<-?->) l $ t
                     where c = territory b $ color p
                           l = location p
                           t = name p
                           