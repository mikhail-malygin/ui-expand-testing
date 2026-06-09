package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$$;

public class MainPage {
    public final SelenideElement goToLoginPageButton = $$("[href='/login']").first();
}
