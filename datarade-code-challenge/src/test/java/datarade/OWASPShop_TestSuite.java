package datarade;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/*
This is a list of all the tests that will be executed as part of the TestSuite
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({OWASPShop_ItemPaginationTests.class,
                    OWASPShop_ItemPerPageTests.class,
                    OWASPShop_LanguageTests.class,
                    OWASPShop_LoginAuthenticationTests.class,
                    OWASPShop_SecurityFlawTests.class
                    })
public class OWASPShop_TestSuite {
}
