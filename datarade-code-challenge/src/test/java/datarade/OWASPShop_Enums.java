package datarade;

public class OWASPShop_Enums {

    //Authentication Related Constants
    public enum Authentication {
        LoginMenuButtonName("navbarLoginButton"),
        AccountMenuButtonPath("/html/body/app-root/div/mat-sidenav-container/mat-sidenav-content/app-navbar/mat-toolbar/mat-toolbar-row/button[3]"),
        EmailTextFieldPath("/html/body/app-root/div/mat-sidenav-container/mat-sidenav-content/app-login/div/mat-card/div/mat-form-field[1]/div/div[1]/div[3]/input"),
        PasswordTextFieldPath("/html/body/app-root/div/mat-sidenav-container/mat-sidenav-content/app-login/div/mat-card/div/mat-form-field[2]/div/div[1]/div[3]/input"),
        LoginPageButtonPath("/html/body/app-root/div/mat-sidenav-container/mat-sidenav-content/app-login/div/mat-card/div/button[1]"),
        AuthAttemptMessagePath("/html/body/app-root/div/mat-sidenav-container/mat-sidenav-content/app-login/div/mat-card/div[1]");

        public final String label;
        private Authentication(String label) {
            this.label = label;
        }
    }

    //General Paths in the XPath hierarchy HTML DOM to specific elements
    public enum Navigation {
        HeaderTextPath("/html/body/app-root/div/mat-sidenav-container/mat-sidenav-content/app-search-result/div/div/div[1]/div[1]"),
        BottomPopupButtonPath("/html/body/div[1]/div/a"),
        WelcomePopupButtonPath("//*[@id=\"mat-dialog-0\"]/app-welcome-banner/div/div[2]/button[2]"),
        LanguageMenuButtonPath("/html/body/app-root/div/mat-sidenav-container/mat-sidenav-content/app-navbar/mat-toolbar/mat-toolbar-row/button[4]"),
        NextPageNavigationButtonPath("/html/body/app-root/div/mat-sidenav-container/mat-sidenav-content/app-search-result/div/div/mat-paginator/div/div/div[2]/button[2]");

        public final String label;
        private Navigation(String label) {
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
