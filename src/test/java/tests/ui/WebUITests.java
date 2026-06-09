package tests.ui;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static io.qameta.allure.Allure.step;

public class WebUITests extends TestBase {

    @Test
    public void shouldSuccessfulLoginTest() {

        step("Go to a login page and login with the correct credentials", () -> {
            mainPage.goToLoginPageButton.click();
            loginPage.userNameInput.setValue(config.username());
            loginPage.passwordInput.setValue(config.password());
            loginPage.submitLoginButton.click();
        });

        step("Check successful login", () -> {
            securePage.successfulLoginMessage.shouldHave(text("You logged into a secure area!"));
            securePage.logoutRedButton.shouldBe(visible);
        });


    }
}
