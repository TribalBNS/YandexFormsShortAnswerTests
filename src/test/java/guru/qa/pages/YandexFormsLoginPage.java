package guru.qa.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class YandexFormsLoginPage {
    private final SelenideElement
            loginCheckInput = $(".AuthLoginInputToggle-wrapper").$(byText("Почта")).parent(),
            loginNameInput = $("#passp-field-login"),
            enterInput = $(".passp-sign-in-button").$(byText("Войти")).parent(),
            passwordInput = $("#passp-field-passwd");



    public YandexFormsLoginPage loginCheck() {
        loginCheckInput.click();

        return this;

    }

    public YandexFormsLoginPage loginName(String value) {
        loginNameInput.setValue(value);

        return this;

    }

    public YandexFormsLoginPage enter() {
        enterInput.click();

        return this;

    }

    public YandexFormsLoginPage password(String value) {
        passwordInput.setValue(value);

        return this;

    }
}
