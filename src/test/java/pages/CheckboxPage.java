package pages;

import com.codeborne.selenide.ElementsCollection;

import static com.codeborne.selenide.Selenide.$$;

public class CheckboxPage {
    public final ElementsCollection listCheckboxes = $$("[type='checkbox']");
}
