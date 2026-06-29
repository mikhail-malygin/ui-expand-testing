package tests.ui;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import pages.AutoCompletePage;


import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class AutocompleteTests extends TestBase {

    @ParameterizedTest
    @ValueSource(strings = {"Canada", "United States of America", "France"})
    @Tag("PositiveTests")
    @Tag("AutoComplete")
    @DisplayName("Checks autocomplete in selectors by countries")
    public void shouldAutoCompleteCountryTests(String country) {
        AutoCompletePage autoCompletePage = new AutoCompletePage();

        step("Type country name using autocompleting", () -> {

            open("autocomplete");
            autoCompletePage.countryInput.shouldBe(visible);
            autoCompletePage.submitButton.shouldBe(visible);
            autoCompletePage.resultText.shouldBe(hidden);

            autoCompletePage.countryInput.sendKeys(country);
            autoCompletePage.countryOptions.get(0).click();
            autoCompletePage.submitButton.click();
        });

        step("Checks autocomplete function", () -> {
            assertThat(autoCompletePage.resultText.getText()).isEqualTo("You selected: " + country);
        });
    }
}
