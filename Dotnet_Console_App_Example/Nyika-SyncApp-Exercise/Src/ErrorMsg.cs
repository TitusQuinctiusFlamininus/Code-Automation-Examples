public readonly struct ErrorCodes
{
     public const string SYNC_EMPTY_STRING_MSG = "The Synchronization Argument Provided was an Empty String!";
     public const string SYNC_NON_NUMERAL_MSG = "The Synchronization Argument Provided was Not a Numeral!";
     public const string SYNC_MINIMUM_SYNC_WAIT_MSG = "The Synchronization Argument Provided is Less than the Minimum Required!";

    public static string EmptyLocationStringMsg(string locType){
         return String.Format( "The {0} Location Argument Provided was an Empty String!", locType);
    }

    public static string NonExistentLocationStringMsg(string locType){
         return String.Format( "The {0} Location Directory Provided Cannot Be Found! Please Check the PATH again.", locType);
    }
}
