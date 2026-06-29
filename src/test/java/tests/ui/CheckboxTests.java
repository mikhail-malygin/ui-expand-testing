package tests.ui;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.CheckboxPage;

import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class CheckboxTests extends TestBase {

    @Test
    @Tag("PositiveTests")
    @Tag("Checkboxes")
    @DisplayName("Activates checkboxes")
    public void shouldActivatesCheckboxesTests() {
        step("Activate checkbox", () -> {
            CheckboxPage checkboxPage = new CheckboxPage();
            open("checkboxes");

            for (SelenideElement element : checkboxPage.listCheckboxes) {
                if (element.isSelected()) {
                    element.click();
                }
            }

            for (SelenideElement element : checkboxPage.listCheckboxes) {
                if (!element.isSelected()) {
                    element.click();
                }
            }

        });

        step("Check checkboxes activation", () -> {
            CheckboxPage checkboxPage = new CheckboxPage();
            for (SelenideElement element : checkboxPage.listCheckboxes) {
                element.shouldBe(Condition.checked);
            }
        });
    }

    @Test
    @Tag("PositiveTests")
    @Tag("Checkboxes")
    @DisplayName("Deactivates checkboxes")
    public void shouldDeactivatesCheckboxesTests() {
        step("Activate checkbox", () -> {
            CheckboxPage checkboxPage = new CheckboxPage();

            open("checkboxes");

            for (SelenideElement element : checkboxPage.listCheckboxes) {
                if (element.isSelected()) {
                    element.click();
                }
            }
        });

        step("Check checkboxes deactivation", () -> {
            CheckboxPage checkboxPage = new CheckboxPage();
            for (SelenideElement element : checkboxPage.listCheckboxes) {
                element.shouldNotBe(Condition.checked);
            }
        });
    }
}

