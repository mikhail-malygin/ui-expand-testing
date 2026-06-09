package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class SecureLoginAreaPage {
    public final SelenideElement successfulLoginMessage = $("#flash-message #flash"),
            logoutRedButton = $("[href='/logout']");
}
