{-# LANGUAGE MultiParamTypeClasses #-}

module Datatypes.ChessTypes where

import Datatypes.ChessConstants

{--
*****************************
*****************************
******HCHESS CONSTANTS*******
*****************************
*****************************
--}

-- | Rook, Knight and Bishop Piece types are a list
rkb     = [ROOK, KNIGHT, BISHOP] :: [PieceType]

-- | What the back row of a setup chessboard looks like, omitting the King (from left to right, or vice-versa)
majNames     = rkb  ++   [QUEEN]  ++ reverse rkb  :: [PieceType]

-- | What the back row worths of a setup chessboard looks like, omitting the King (from left to right, or vice-verse)
majWorths    = rkbWorths ++     [10]   ++ reverse rkbWorths :: [Value]

-- | The King's worth
kingWorth    = 1000      :: Value

-- | The King's file when the game starts
kingFile     = 5         :: File

-- | The worth of any Pawn
pawnWorth    = 1         :: Value

-- | Function to create any piece, given its attributes of name, color, worth and location (formed out of its separate components, 
--   file and rank)
zipper       = \n c w file rank -> Piece { name  = n, color = c, worth = w, location = (file,rank)} 

-- | Function to form a location, given a file and rank
locZipper    = \f r -> (f,r)

-- | All files as a list, with the exception of the King's
nonKingFiles = filter (/=5) allFiles :: [File]

-- | Absolutely all files of all pieces
allFiles     = [1..8] :: [File]

-- | The rank of all black Pawns at the start of the game
bPawnsRank   = 7 :: Rank

-- | The rank of all white Pawns at the start of the game
wPawnsRank   = 2 :: Rank


{--
*****************************
*****************************
***HCHESS TYPE SYNONYMS******
*****************************
*****************************
--}

-- | type that represents who is winning on the board (positive number for White, negative for Black, Zero for 
--   an equal position)
type ChessEval = Float

-- | An integer that describes a part of a location tuple, either rank or file 
type RankOrFile = Int

-- | The numeral representing the row a piece is in
type Rank = Int

-- | The numeral representing the file (column) a piece is in
type File = Int 

-- | A specific square on the board
type Location = (File, Rank)

-- | The value of the chess piece
type Value = Int

{--
*****************************
*****************************
******HCHESS DATA TYPES******
*****************************
*****************************
--}

-- | Type of pieces the pawns are (it is used as a phantom, for promotion rules)
data MINOR = MINOR

-- | Any piece that is not a pawn, except the King, is of this type (it is used as a phantom, for promotion rules)
data MAJOR = MAJOR

-- | The type describing the piece (King), which is the goal of chess
data ZIEL  = ZIEL

-- | Colors of the pieces on the chess board
data Color = BLACK | WHITE deriving (Show, Eq)

-- | Fundamental kinds of chess pieces in the game
data PieceType =  KING  | QUEEN  | ROOK  | BISHOP | KNIGHT | PAWN  deriving (Show, Eq)

-- | A typical chess piece
data Piece a = Piece {   name       :: PieceType,
                         color      :: Color, 
                         worth      :: Value, 
                         location   :: Location
                     }   deriving (Show, Eq)

-- | The type that we use to gather all chess types together
data BoardPiece = K (Piece ZIEL) | MI (Piece MINOR) | MA (Piece MAJOR) deriving (Eq)

-- | Type representing the current state of the game and the color of the side that made the last move
newtype ChessBoard a = ChessBoard { eval :: (Color, [BoardPiece]) }

-- | Type representing a move to make on the board, from one location to another
data Move = Move   {    from  :: Location, 
                        to    :: Location,
                        farbe :: Color
                    } deriving (Show)

-- | Type representing a tree of moves to consider making for an advantage
data ChessTree a = Draw | StaleMate | CheckMate | ChessMove a (ChessTree a) deriving (Show)

{--
*****************************
*****************************
****HCHESS TYPE CLASSES******
*****************************
*****************************
--}

-- | Minor pieces behaviour
class Minor a where

-- | Major pieces behaviour
class Major a where

-- | Ability of a piece to migrate from one square to another, either to capture or simply to move.
--   For a simple movement :  a piece to move and its intended location
--   For a piece capture:  a killer, a victim, a list of all victims captured so far
class Movable p where
    move    :: p -> Location -> p
    capture :: p -> p -> [p] -> (p, [p])

-- | Promotion of Minor pieces (i.e pawns). Major pieces are any pieces that are NOT pawns (and not the King)
class Promotable p t where
    promote :: p -> t -> t

{--
*****************************
*****************************
**HCHESS TYPECLASS INSTANCES*
*****************************
*****************************
--}

-- | Making only Minor pieces a member 
instance Minor MINOR where

-- | Making Major pieces a member 
instance Major MAJOR where 

-- | Making Kings a member 
instance Major ZIEL where

-- | Display the board, we are only interested in the pieces
instance Show BoardPiece where
    show (K  p)   = show p
    show (MI p)   = show p
    show (MA p)   = show p

-- | Making all our pieces movable and the ability to capture other pieces
instance Movable (Piece a) where
    move p l        = p  { location = l } 
    capture k v l   = (k {location = location v}, (v:l))


-- | Promoting a Minor piece to Major piece
--   This is the only way we can promote in chess. 
instance (Minor a, Major b) => Promotable (Piece a) (Piece b) where
    promote p r = Piece { name     =  name r, 
                          color    =  color p, 
                          worth    =  worth r, 
                          location =  (fst $ location p, if (color p == BLACK) then uBound else lBound)
                         }
    