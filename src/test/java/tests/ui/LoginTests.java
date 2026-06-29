package tests.ui;

import io.qameta.allure.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.LoginPage;
import pages.SecureLoginAreaPage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class LoginTests extends TestBase {

    @Test
    @Tag("PositiveTests")
    @Tag("Login")
    @DisplayName("Successful login")
    @Description("Test successful login with the correct credentials")
    public void shouldSuccessfulLoginTest() {

        step("Go to a login page and login with the correct credentials", () -> {
            LoginPage loginPage = new LoginPage();

            open("login");
            loginPage.enterLoginUsername(config.username())
                    .enterLoginPassword(config.password())
                    .submitLogin();
        });

        step("Check successful login", () -> {
            SecureLoginAreaPage securePage = new SecureLoginAreaPage();

            securePage.successfulLoginMessage.shouldHave(text("You logged into a secure area!"));
            securePage.logoutRedButton.shouldBe(visible);
        });

        step("Logout", () -> {
            SecureLoginAreaPage securePage = new SecureLoginAreaPage();

            securePage.logoutRedButton.click();
        });
    }

    @Test
    @Tag("NegativeTests")
    @Tag("Login")
    @DisplayName("Unsuccessful login using an invalid username")
    @Description("Test unsuccessful login with the incorrect credentials: username")
    public void shouldUnsuccessfulInvalidUsernameLoginTest() {

        step("Go to a login page and try to login with the wrong username", () -> {
            LoginPage loginPage = new LoginPage();

            open("login");
            loginPage.enterLoginUsername(faker.credentials().username())
                    .enterLoginPassword(config.password())
                    .submitLogin();
        });

        step("Check unsuccessful login", () -> {
            LoginPage loginPage = new LoginPage();

            loginPage.redAlertMessage.shouldHave(text("Your username is invalid!"));
        });
    }

    @Test
    @Tag("NegativeTests")
    @Tag("Login")
    @DisplayName("Unsuccessful login using an invalid password")
    @Description("Test unsuccessful login with the incorrect credentials: password")
    public void shouldUnsuccessfulInvalidPasswordLoginTest() {

        step("Go to a login page and try to login with the wrong password", () -> {
            LoginPage loginPage = new LoginPage();

            open("login");
            loginPage.enterLoginUsername(config.username())
                    .enterLoginPassword(faker.credentials().weakPassword())
                    .submitLogin();
        });

        step("Check unsuccessful login", () -> {
            LoginPage loginPage = new LoginPage();

            loginPage.redAlertMessage.shouldBe(visible);
            loginPage.redAlertMessage.shouldHave(text("Your password is invalid!"));
        });
    }

    @Test
    @Tag("NegativeTests")
    @Tag("Login")
    @DisplayName("Unsuccessful login with empty credentials")
    @Description("Test unsuccessful login with the empty credentials")
    public void shouldUnsuccessfulEmptyCredentialsLoginTest() {

        step("Go to a login page and try to login with the empty credentials", () -> {
            LoginPage loginPage = new LoginPage();

            open("login");
            loginPage.submitLogin();
        });

        step("Check unsuccessful login", () -> {
            LoginPage loginPage = new LoginPage();

            loginPage.redAlertMessage.shouldHave(text("Your username is invalid!"));
        });
    }
}
