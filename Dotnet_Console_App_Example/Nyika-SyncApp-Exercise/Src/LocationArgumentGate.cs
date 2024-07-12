using System.Reflection.Metadata.Ecma335;

namespace Src;


public class LocationArgumentGate
{

    private string source;
    private string dest;
       public LocationArgumentGate(string source, string dest)
       {
        this.source = source;
        this.dest = dest;
         }

    public string Source { get => source; set => source = value; }
    public string Dest { get => dest; set => dest = value; }


    public void VerifyLocationArguments() 
    {
         if(Source == ""){
             throw new SyncException(ErrorCodes.EmptyLocationStringMsg(CONSTANTS.SOURCE));
        }
         if(Dest == ""){
             throw new SyncException(ErrorCodes.EmptyLocationStringMsg(CONSTANTS.DESTINATION));
        }
         if(!DoesDirectoryExist(Source)){
             throw new SyncException(ErrorCodes.NonExistentLocationStringMsg(CONSTANTS.SOURCE));
         }

    }

    public Func<string, bool> DoesDirectoryExist = (x) =>  true;

 
}
