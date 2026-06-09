package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {

    public final SelenideElement userNameInput = $("#username"),
            passwordInput = $("#password"),
            submitLoginButton = $("#submit-login"),
            redAlertMessage = $("#flash");

    public LoginPage enterLoginUsername(String username) {
        userNameInput.setValue(username);

        return this;
    }

    public LoginPage enterLoginPassword(String password) {
        passwordInput.setValue(password);

        return this;
    }

    public void submitLogin() {
        submitLoginButton.click();
    }
}
