package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class AutoCompletePage {
    public final SelenideElement countryInput = $("#country"),
            resultText = $("#result"),
            submitButton = $(".btn-primary");
    public final ElementsCollection countryOptions = $$("#countryautocomplete-list div");
}
