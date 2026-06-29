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
        LoginPage loginPage = new LoginPage();
        SecureLoginAreaPage securePage = new SecureLoginAreaPage();

        step("Go to a login page and login with the correct credentials", () -> {

            open("login");
            loginPage.enterLoginUsername(config.username())
                    .enterLoginPassword(config.password())
                    .submitLogin();
        });

        step("Check successful login", () -> {

            securePage.successfulLoginMessage.shouldHave(text("You logged into a secure area!"));
            securePage.logoutRedButton.shouldBe(visible);
        });

        step("Logout", () -> securePage.logoutRedButton.click());
    }

    @Test
    @Tag("NegativeTests")
    @Tag("Login")
    @DisplayName("Unsuccessful login using an invalid username")
    @Description("Test unsuccessful login with the incorrect credentials: username")
    public void shouldUnsuccessfulInvalidUsernameLoginTest() {
        LoginPage loginPage = new LoginPage();

        step("Go to a login page and try to login with the wrong username", () -> {

            open("login");
            loginPage.enterLoginUsername(faker.credentials().username())
                    .enterLoginPassword(config.password())
                    .submitLogin();
        });

        step("Check unsuccessful login", () -> {

            loginPage.redAlertMessage.shouldHave(text("Your username is invalid!"));
        });
    }

    @Test
    @Tag("NegativeTests")
    @Tag("Login")
    @DisplayName("Unsuccessful login using an invalid password")
    @Description("Test unsuccessful login with the incorrect credentials: password")
    public void shouldUnsuccessfulInvalidPasswordLoginTest() {
        LoginPage loginPage = new LoginPage();

        step("Go to a login page and try to login with the wrong password", () -> {

            open("login");
            loginPage.enterLoginUsername(config.username())
                    .enterLoginPassword(faker.credentials().weakPassword())
                    .submitLogin();
        });

        step("Check unsuccessful login", () -> {

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
        LoginPage loginPage = new LoginPage();

        step("Go to a login page and try to login with the empty credentials", () -> {

            open("login");
            loginPage.submitLogin();
        });

        step("Check unsuccessful login", () -> {

            loginPage.redAlertMessage.shouldHave(text("Your username is invalid!"));
        });
    }
}
