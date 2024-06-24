package datarade;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({OWASPShop_ItemPaginationTests.class,
                     OWASPShop_ItemPerPageTests.class,
                     OWASPShop_LanguageTests.class,
                     OWASPShop_LoginAuthenticationTests.class
                    })
public class OWASPShop_TestSuite {
}
