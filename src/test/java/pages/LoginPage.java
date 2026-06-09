package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {

    public final SelenideElement userNameInput = $("#username"),
            passwordInput = $("#password"),
            submitLoginButton = $("#submit-login");
}
