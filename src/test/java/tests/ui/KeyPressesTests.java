package tests.ui;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import pages.KeyPressesPage;

import java.util.Arrays;
import java.util.List;

import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.actions;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class KeyPressesTests extends TestBase {

    @Test
    @Tag("PositiveTests")
    @Tag("KeyPresses")
    @DisplayName("Click a keyboard button and check display action")
    public void shouldDisplayTypedSymbolTests() {
        List<String> typedSymbols = Arrays.asList("A", "b", "7");
        List<String> typedButtons = Arrays.asList("ESCAPE", "CONTROL", "BACK_SPACE", "TAB", "SHIFT", "ALT", "ENTER");
        KeyPressesPage keyPressesPage = new KeyPressesPage();

        step("Type symbol and check its display", () -> {

            open("key-presses");

            keyPressesPage.inputTextArea.shouldBe(visible);
            keyPressesPage.resultInputTextArea.shouldBe(hidden);

            for (String symbol : typedSymbols) {
                keyPressesPage.inputTextArea.sendKeys(symbol);
                assertThat(keyPressesPage.resultInputTextArea.getText()).isEqualTo("You entered: " + symbol.toUpperCase());
            }

            for (String button : typedButtons) {
                Keys key = Keys.valueOf(button);
                actions().sendKeys(key).perform();
                assertThat(keyPressesPage.resultInputTextArea.getText()).isEqualTo("You entered: " + button);
            }
        });
    }
}
