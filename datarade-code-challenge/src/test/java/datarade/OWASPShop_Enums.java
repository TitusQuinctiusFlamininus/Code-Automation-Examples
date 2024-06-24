package datarade;

public class OWASPShop_Enums {

    //Path in the XPath hierarchy HTML DOM to specific elements
    public enum XPath {
        HeaderTextPath("/html/body/app-root/div/mat-sidenav-container/mat-sidenav-content/app-search-result/div/div/div[1]/div[1]"),
        BottomPopupButtonPath("/html/body/div[1]/div/a"),
        WelcomePopupButtonPath("//*[@id=\"mat-dialog-0\"]/app-welcome-banner/div/div[2]/button[2]"),
        LanguageMenuButtonPath("/html/body/app-root/div/mat-sidenav-container/mat-sidenav-content/app-navbar/mat-toolbar/mat-toolbar-row/button[4]"),
        NextPageNavigationButtonPath("/html/body/app-root/div/mat-sidenav-container/mat-sidenav-content/app-search-result/div/div/mat-paginator/div/div/div[2]/button[2]");

        public final String label;
        private XPath(String label) {
            this.label = label;
        }
    }

    //Language Codes Constants
    public enum Language {
        GERMAN("6"),
        FRENCH("10");

        public final String label;
        private Language(String label) {
            this.label = label;
        }
    }

}
