package tests.ui;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import pages.InputsPage;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class InputsTests extends TestBase {

    @ParameterizedTest(name = "For input: number {0} should display {0} " +
            "for input: text {1} should display {1} " +
            "for input: password {2} should display {2}  " +
            "for input: date {3} should display {3}")
    @Execution(ExecutionMode.SAME_THREAD)
    @Tag("PositiveTests")
    @Tag("Inputs")
    @DisplayName("Display correct texts in inputs")
    @CsvFileSource(resources = "/testData/inputsValues.csv", numLinesToSkip = 1)
    public void shouldDisplayTextsInputsTests(String number, String text, String password, String date) {
        InputsPage inputsPage = new InputsPage();

        step("Go to a inputs page and type different texts in inputs", () -> {

            open("inputs");
            inputsPage.typeNumber(number)
                    .typeText(text)
                    .typePassword(password)
                    .chooseDate(date)
                    .displayInputsButton.click();
        });

        step("Check displaying texts", () -> {

            inputsPage.numberOutput.shouldHave(text(number));
            inputsPage.textOutput.shouldHave(text(text));
            inputsPage.passwordOutput.shouldHave(text(password));
            inputsPage.dateOutput.shouldHave(text(inputsPage.transformDate(date)));
        });

        step("Check clearing texts", () -> {

            inputsPage.clearInputsButton.click();

            inputsPage.numberOutput.shouldNotBe(visible);
            inputsPage.textOutput.shouldNotBe(visible);
            inputsPage.passwordOutput.shouldNotBe(visible);
            inputsPage.dateOutput.shouldNotBe(visible);
        });

    }

    @Test
    @Tag("PositiveTests")
    @Tag("Inputs")
    @DisplayName("Display empty texts in inputs")
    public void shouldDisplayEmptyTextsInputsTests() {
        InputsPage inputsPage = new InputsPage();

        step("Go to a inputs page and type different texts in inputs", () -> {

            open("inputs");
            inputsPage.displayInputsButton.click();
        });

        step("Check displaying texts", () -> {

            inputsPage.numberOutput.shouldBe(empty);
            inputsPage.textOutput.shouldBe(empty);
            inputsPage.passwordOutput.shouldBe(empty);
            inputsPage.dateOutput.shouldBe(empty);
        });

        step("Check clearing texts", () -> {

            inputsPage.clearInputsButton.click();

            inputsPage.numberOutput.shouldNotBe(visible);
            inputsPage.textOutput.shouldNotBe(visible);
            inputsPage.passwordOutput.shouldNotBe(visible);
            inputsPage.dateOutput.shouldNotBe(visible);
        });

    }

    @Test
    @Tag("NegativeTests")
    @Tag("Inputs")
    @DisplayName("Undisplay text value in number output")
    public void shouldUndisplayTextNumberInputsTests() {

        String text = faker.text().text();
        String password = faker.credentials().password();
        String date = faker.timeAndDate().future(60, 30, TimeUnit.DAYS, "dd.MM.yyyy");
        InputsPage inputsPage = new InputsPage();

        step("Go to a inputs page and type different texts in inputs", () -> {

            open("inputs");
            inputsPage.typeNumber(text)
                    .typeText(text)
                    .typePassword(password)
                    .chooseDate(date)
                    .displayInputsButton.click();
        });

        step("Check displaying texts", () -> {

            inputsPage.numberOutput.shouldBe(empty);
            inputsPage.textOutput.shouldHave(text(text));
            inputsPage.passwordOutput.shouldHave(text(password));
            inputsPage.dateOutput.shouldHave(text(inputsPage.transformDate(date)));
        });

        step("Check clearing texts", () -> {

            inputsPage.clearInputsButton.click();

            inputsPage.numberOutput.shouldNotBe(visible);
            inputsPage.textOutput.shouldNotBe(visible);
            inputsPage.passwordOutput.shouldNotBe(visible);
            inputsPage.dateOutput.shouldNotBe(visible);
        });

    }

}
