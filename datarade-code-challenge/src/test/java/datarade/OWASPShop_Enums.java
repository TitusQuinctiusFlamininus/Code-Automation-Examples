package datarade;

public class OWASPShop_Enums {

    //Authentication Related Constants
    public enum Authentication {
        LoginMenuButtonName("navbarLoginButton"),
        AccountMenuButtonPath("/html/body/app-root/div/mat-sidenav-container/mat-sidenav-content/app-navbar/mat-toolbar/mat-toolbar-row/button[3]"),
        EmailTextFieldPath("/html/body/app-root/div/mat-sidenav-container/mat-sidenav-content/app-login/div/mat-card/div/mat-form-field[1]/div/div[1]/div[3]/input"),
        PasswordTextFieldPath("/html/body/app-root/div/mat-sidenav-container/mat-sidenav-content/app-login/div/mat-card/div/mat-form-field[2]/div/div[1]/div[3]/input"),
        LoginPageButtonPath("/html/body/app-root/div/mat-sidenav-container/mat-sidenav-content/app-login/div/mat-card/div/button[1]"),
        AuthAttemptMessagePath("/html/body/app-root/div/mat-sidenav-container/mat-sidenav-content/app-login/div/mat-card/div[1]"),
        ValidUsername("admin@juice-sh.op"),
        ValidPassword("admin123"),
        LogoutButtonPath("/html/body/div[4]/div[2]/div/div/div/button[4]"),
        SuccessLoginMessage_1_Path("/html/body/app-root/div/mat-sidenav-container/mat-sidenav-content/app-challenge-solved-notification/div/mat-card[1]/div[1]"),
        SuccessLoginMessage_2_Path("/html/body/app-root/div/mat-sidenav-container/mat-sidenav-content/app-challenge-solved-notification/div/mat-card[2]/div[1]"),
        SuccessLoginMessage_1("You successfully solved a challenge: Password Strength (Log in with the administrator's user credentials without previously changing them or applying SQL Injection.)\n" +
                "X"),
        SuccessLoginMessage_2("You successfully solved a challenge: Login Admin (Log in with the administrator's user account.)\n" +
                "X");
        ;

        public final String label;
        private Authentication(String label) {
            this.label = label;
        }
    }

    //Enums to test any Security Flaws the system may have
    public enum SecurityFlaw {
        NotCustomerYetLinkPath("/html/body/app-root/div/mat-sidenav-container/mat-sidenav-content/app-login/div/mat-card/div/div[2]/a"),
        RegisterEmailTextFieldPath("/html/body/app-root/div/mat-sidenav-container/mat-sidenav-content/app-register/div/mat-card/div[2]/mat-form-field[1]/div/div[1]/div[3]/input"),
        RegisterPasswordTextFieldPath("/html/body/app-root/div/mat-sidenav-container/mat-sidenav-content/app-register/div/mat-card/div[2]/mat-form-field[2]/div/div[1]/div[3]/input"),
        RepeatPasswordTextFieldPath("/html/body/app-root/div/mat-sidenav-container/mat-sidenav-content/app-register/div/mat-card/div[2]/mat-form-field[3]/div/div[1]/div[3]/input"),
        SecurityQuestionSelectPath("/html/body/app-root/div/mat-sidenav-container/mat-sidenav-content/app-register/div/mat-card/div[2]/div[1]/mat-form-field[1]/div/div[1]/div[3]/mat-select"),
        SecurityAnswerTextFieldPath("/html/body/app-root/div/mat-sidenav-container/mat-sidenav-content/app-register/div/mat-card/div[2]/div[1]/mat-form-field[2]/div/div[1]/div[3]/input"),
        RegistrationButtonPath("/html/body/app-root/div/mat-sidenav-container/mat-sidenav-content/app-register/div/mat-card/div[2]/button");

        public final String label;

        private SecurityFlaw(String label) {
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
