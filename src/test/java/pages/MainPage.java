package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$$;

public class MainPage {
    public final SelenideElement goToLoginPageButton = $$("[href='/login']").last(),
            goToInputsPageButton = $$("[href='/inputs']").last(),
            goToCheckboxesPageButton = $$("[href='/checkboxes").last();

}
