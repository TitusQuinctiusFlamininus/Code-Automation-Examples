
namespace Tests;
using Src;


public class SourceDestinationUnitTests

{
   [TestFixture]
    public class SourceDestinationTests
    {

         [Test]
         [TestCase("", "", CONSTANTS.SOURCE)]
         [TestCase("good_source", "", CONSTANTS.DESTINATION)]
        public void Verify_Source_Location_Inputs(string src, string dest, string konst)
        {
            try{
                new LocationArgumentGate(src, dest).VerifyLocationArguments();
                Assert.Fail("An Error was meant to be generated!");
            }
            catch(SyncException se){
                Assert.That(se.Message, Does.Contain(ErrorCodes.EmptyLocationStringMsg(konst)));
            }
        }
 

       [Test]
       [TestCase("bad", "good_dest", CONSTANTS.SOURCE)]

        public void Verify_Source_Location_Does_NotExist_Error(string src, string dest, string konst)
        {
            try{
                LocationArgumentGate testSubject = new LocationArgumentGate(src, dest)
                {
                    
                         DoesDirectoryExist = (x) =>  false
                
                };
                testSubject.VerifyLocationArguments();
                Assert.Fail("An Error was meant to be generated!");
            }
            catch(SyncException se){
                Assert.That(se.Message, Does.Contain(ErrorCodes.NonExistentLocationStringMsg(konst)));
            }
        }

      [Test]
        public void Verify_Dot_InputLocation_Is_OK() //Dot just means the current directory
        {
             LocationArgumentGate testSubject = new LocationArgumentGate(".", "somedestination");
             testSubject.VerifyLocationArguments();
             Assert.That(testSubject.Source == ".");  
        }
      
    }
}