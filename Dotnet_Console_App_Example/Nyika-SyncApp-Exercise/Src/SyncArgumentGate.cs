namespace Src;

using System.Text.RegularExpressions;


public class SyncArgumentGate
{
    public SyncArgumentGate(int defaultSyncTime){
        this.DefaultSyncTime = defaultSyncTime;
    }

   
    private int syncArg;
    private int defaultSyncTime;

    public int DefaultSyncTime { get => defaultSyncTime; set => defaultSyncTime = value; }

    public int SyncArg { get => syncArg; set => syncArg = value; }

    public void VerifySyncPeriodArgument(string syncArgInput ) 
    {

       if(syncArgInput == ""){
        throw new SyncException(ErrorCodes.SYNC_EMPTY_STRING_MSG);
        }

        //Let's assume we can only deal in whole numbers (seconds)
       if (!Regex.IsMatch(syncArgInput, @"^\d+$"))
        {
        throw new SyncException(ErrorCodes.SYNC_NON_NUMERAL_MSG);
       }

       int parsedSync = 0;
       if(int.TryParse(syncArgInput, out parsedSync))
       {      
        
            if(parsedSync < DefaultSyncTime)
                {
                    throw new SyncException(ErrorCodes.SYNC_MINIMUM_SYNC_WAIT_MSG);
                }
            else
                {
                    SyncArg = parsedSync;
                }
        }
        else
        {
                   SyncArg = DefaultSyncTime;
        }
    }
}
