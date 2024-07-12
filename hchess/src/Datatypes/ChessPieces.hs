module Datatypes.ChessPieces (allKings, allMinorPieces, allMajorPieces, setChessBoard) where

import Datatypes.ChessTypes 
import Datatypes.ChessConstants
import Utilities.ChessUtils

import Data.List     (zipWith5, nub)

-- | Function to create the Kings
allKings :: [Piece ZIEL]
allKings = zipWith (\c y -> Piece { name=KING, color=c, worth=kingWorth, location=(kingFile,y)} ) [BLACK, WHITE] [uBound,lBound]

-- | Function to create all Minor pieces, White and Black (Pawns)       
allMinorPieces :: [Piece MINOR]
allMinorPieces = pawner BLACK bPawnsRank ++ pawner WHITE wPawnsRank

-- | Function to create Pawns of a certain color, placed on a specific row
pawner :: Color -> Rank -> [Piece MINOR]
pawner c r = zipWith3 (\c x y -> Piece { name=PAWN, color=c, worth=pawnWorth, location=(x,y)}) ((<->) uBound c) allFiles ((<->) uBound r)

-- | Function to make all Major pieces, except the King
makeMajors :: Color -> Rank -> [Piece MAJOR]
makeMajors c r = zipWith5 zipper majNames ((<->) uBound c) majWorths nonKingFiles ((<->) uBound r)

-- | Function to create all Major pieces, White and Black (Rooks, Knights, Bishops, Queens, Kings)
allMajorPieces :: [Piece MAJOR]
allMajorPieces = makeMajors BLACK uBound ++ makeMajors WHITE lBound

-- | Function to gather all pieces and place them on the chess board
setChessBoard :: [BoardPiece]
setChessBoard = nub $ concat [[K k, MI mi, MA ma] | k <- allKings, mi <- allMinorPieces, ma <- allMajorPieces]
                
                

