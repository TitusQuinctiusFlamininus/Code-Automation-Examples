module HangmanVisual where

logo =  "o    o                                             \n"++                                          
        "8    8                                             \n"++
        "o8oooo8 .oPYo. odYo. .oPYo. ooYoYo. .oPYo. odYo.   \n"++
        " 8    8 .oooo8 8' `8 8    8 8' 8  8 .oooo8 8' `8   \n"++
        " 8    8 8    8 8   8 8    8 8  8  8 8    8 8   8   \n"++
        " 8    8 `YooP8 8   8 `YooP8 8  8  8 `YooP8 8   8   \n"++
        ":..:::..:.....:..::..:....8 ..:..:..:.....:..::..  \n"++
        ":::::::::::::::::::::::ooP'.:::::::::::::::::::::  \n"++
        ":::::::::::::::::::::::...:::::::::::::::::::::::  \n"

   


one   = "_______________        "

two   = "____|__________        "

three = "    |    \n"++
        "____|___________       "
        
four  = "    |    \n"++
        "    |    \n"++
        "____|___________       "

five  = "    |    \n"++
        "    |    \n"++
        "    |    \n"++
        "____|___________       "
        
six   = "    |    \n"++
        "    |    \n"++
        "    |    \n"++
        "    |    \n"++
        "____|___________       "

seven = "    |    \n"++
        "    |    \n"++
        "    |    \n"++
        "    |    \n"++
        "    |    \n"++
        "____|___________       "

eight = "    |______     \n"++
        "    |/          \n"++
        "    |           \n"++
        "    |           \n"++
        "    |           \n"++
        "____|___________       "

nine  = "    |______     \n"++
        "    |/     |    \n"++
        "    |           \n"++
        "    |           \n"++
        "    |           \n"++
        "____|___________       "

ten   = "    |______     \n"++
        "    |/     |    \n"++
        "    |      ()   \n"++
        "    |           \n"++
        "    |           \n"++
        "____|___________       "
        
elvn  = "    |________      \n"++
        "    |/      |      \n"++
        "    |      ()      \n"++
        "    |     / \\     \n"++
        "    |              \n"++
        "____|___________       "

twlv  = "    |________     \n"++
        "    |/     |      \n"++
        "    |     ()      \n"++
        "    |    /||\\    \n"++
        "    |     --      \n"++
        "____|___________       "
        
theen = "    |________          Hanged! ha!  \n"++
        "    |/     |           ___|___      \n"++
        "    |      ()        [  o  o   ]    \n"++
        "    |     /||\\       [    i    ]   \n"++
        "    |      --        [ //----\\ ] \n"++
        "    |       \\\\       [_________]   \n"++
        "____|___________      "
        
saved = "   saved! yeeh!      \n"++
        "                     \n"++
        "        \\()/        \n"++
        "         ||          \n"++
        "        _//          \n"++
        "___________________"  
        
gameover = "´´´´´´´´´´´´´´´´´´´´´´\n"++ 
           "  GAME OVER (loser!)  \n"++  
           "``````````````````````" 
        
hangover = [one,two,three, four, five, six, seven, eight, nine, ten, elvn, twlv, theen]


-- | Print to the console the progress on the gallows and the word building
showProgress :: String -> String -> Int -> IO ()
showProgress gallows w c = 
    do putStrLn (gallows ++ "    WORD =>["++w++"]") 
       putStrLn ("Chances Left: "++(show c))
       putStrLn ""
       putStrLn ""
       putStrLn ""

-- | Get answer from the player
gatherInput :: IO String
gatherInput = do putStrLn ""
                 putStrLn ("Guess a Letter : ->")
                 guess <- getLine
                 putStrLn ""
                 putStrLn ""
                 return guess
                 
-- | Herzlich Wilkommen                                                                  
welcome :: IO ()
welcome = do putStrLn "Welcome To Haskell's Hangman"
             putStrLn ""
             putStrLn logo
             putStrLn ""
          