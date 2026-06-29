package tests.ui;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.CheckboxPage;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.refresh;
import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CheckboxTests extends TestBase {

    @Test
    @Tag("PositiveTests")
    @Tag("Checkboxes")
    @DisplayName("Activates checkboxes")
    public void shouldActivatesCheckboxesTests() {

        step("Activates checkboxes", () -> {
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

        step("Checks checkboxes activation", () -> {
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
        step("Deactivates checkboxes", () -> {

            CheckboxPage checkboxPage = new CheckboxPage();

            open("checkboxes");

            for (SelenideElement element : checkboxPage.listCheckboxes) {
                if (element.isSelected()) {
                    element.click();
                }
            }
        });

        step("Checks checkboxes deactivation", () -> {
            CheckboxPage checkboxPage = new CheckboxPage();
            for (SelenideElement element : checkboxPage.listCheckboxes) {
                element.shouldNotBe(Condition.checked);
            }
        });
    }

    @Test
    @Tag("PositiveTests")
    @Tag("Checkboxes")
    @DisplayName("Reload doesn't change checkboxes")
    public void shouldNothingChangesCheckboxesAfterReloadPageTests() {
        CheckboxPage checkboxPage = new CheckboxPage();
        List<Boolean> checkboxes = new ArrayList<>();

        step("Reload the checkboxes page", () -> {

            open("checkboxes");
            for (SelenideElement element : checkboxPage.listCheckboxes) {
                if (element.isSelected()) {
                    checkboxes.add(true);
                } else {
                    checkboxes.add(false);
                }
            }

            refresh();
        });

        step("Checks nothing changes in checkboxes after reload the page", () -> {
            for (int i = 0; i < checkboxes.size(); i++) {
                assertThat(checkboxPage.listCheckboxes.get(i).isSelected()).isEqualTo(checkboxes.get(i));
            }
        });
    }
}

