
namespace Tests;
using Src;


public class SyncArgumentUnitTests

{
   [TestFixture]
    public class CommandLineArgumentTests
    {

        private SyncArgumentGate  testSubject;

        [SetUp]
        public void SetUp()
        {
            testSubject = new SyncArgumentGate(30);
        }

        [Test]
        public void Verify_SyncInterval_Provided_In_CMDLine_Arguments_OK()
        {
            testSubject.VerifySyncPeriodArgument("123");
            Assert.That(testSubject.SyncArg == 123);
        }

        [Test]
        public void Verify_SyncInterval_As_Empty_String_Not_OK()
        {
            try{
            testSubject.VerifySyncPeriodArgument("");
            Assert.Fail("An Error was meant to be generated!");

            }
            catch(SyncException se){
                Assert.That(se.Message, Is.EqualTo(ErrorCodes.SYNC_EMPTY_STRING_MSG));
            }
        }

        [Test]
        public void Verify_SyncInterval_Provided_Not_A_Numeral_Not_OK()
        {
             try{
                testSubject.VerifySyncPeriodArgument("abc");
                Assert.Fail("An Error was meant to be generated!");
            }
            catch(SyncException se){
                Assert.That(se.Message, Is.EqualTo(ErrorCodes.SYNC_NON_NUMERAL_MSG));
            }
        }

           
        [Test]
        public void Verify_SyncInterval_With_Partial_NonNmerals_Not_OK()
        {
            try{
                testSubject.VerifySyncPeriodArgument("x1z");
                Assert.Fail("An Error was meant to be generated!");
            }
            catch(SyncException se){
                Assert.That(se.Message, Is.EqualTo(ErrorCodes.SYNC_NON_NUMERAL_MSG));
            }
        }

        [Test]
        public void Verify_SyncInterval_Lower_Than_MinimumTime_Not_OK()
        {
            try{
                testSubject.VerifySyncPeriodArgument("29");
                Assert.Fail("An Error was meant to be generated!");
            }
            catch(SyncException se){
                Assert.That(se.Message, Is.EqualTo(ErrorCodes.SYNC_MINIMUM_SYNC_WAIT_MSG));
            }
        }

        [Test]
        public void Verify_SyncInterval_As_Zero_Still_Lower_Than_MinimumTime_Not_OK()
        {
            try{
                testSubject.VerifySyncPeriodArgument("0");
                Assert.Fail("An Error was meant to be generated!");
            }
            catch(SyncException se){
                Assert.That(se.Message, Is.EqualTo(ErrorCodes.SYNC_MINIMUM_SYNC_WAIT_MSG));
            }
        }

         [Test]
        public void Verify_SyncInterval_As_Negative_IsNot_A_Numeral_Not_OK()
        {
            try{
                testSubject.VerifySyncPeriodArgument("-7");
                Assert.Fail("An Error was meant to be generated!");
            }
            catch(SyncException se){
                Assert.That(se.Message, Is.EqualTo(ErrorCodes.SYNC_NON_NUMERAL_MSG));
            }
        }

        [Test]
        public void Verify_SyncInterval_As_Exactly_DefaultSyncTime_OK()
        {
            //Let's use another minimal default sync time
            testSubject = new SyncArgumentGate(5); 
            testSubject.VerifySyncPeriodArgument("5");  
            Assert.That(testSubject.DefaultSyncTime == testSubject.SyncArg);        
        }

    }
}