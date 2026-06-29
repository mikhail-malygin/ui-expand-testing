package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class KeyPressesPage {

    public final SelenideElement inputTextArea = $("#target"),
            resultInputTextArea = $("#result");
}
