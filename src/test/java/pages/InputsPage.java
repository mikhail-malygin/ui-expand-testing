package pages;

import com.codeborne.selenide.SelenideElement;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.$;

public class InputsPage {
    public final SelenideElement numberInput = $("[name='input-number']"),
            textInput = $("[name='input-text']"),
            passwordInput = $("[name='input-password']"),
            dateInput = $("[name='input-date']"),
            numberOutput = $("#output-number"),
            textOutput = $("#output-text"),
            passwordOutput = $("#output-password"),
            dateOutput = $("#output-date"),
            displayInputsButton = $(".btn-outline-primary"),
            clearInputsButton = $(".btn-outline-danger");

    public InputsPage typeNumber(String number) {
        numberInput.setValue(number);

        return this;
    }

    public InputsPage typeText(String text) {
        textInput.setValue(text);

        return this;
    }

    public InputsPage typePassword(String password) {
        passwordInput.setValue(password);

        return this;
    }

    public InputsPage chooseDate(String date) {
        dateInput.setValue(date);

        return this;
    }

    public String transformDate(String inputDate) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate date = LocalDate.parse(inputDate, inputFormatter);

        return date.format(outputFormatter);
    }
}
