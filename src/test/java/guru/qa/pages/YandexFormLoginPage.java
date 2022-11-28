package guru.qa.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class YandexFormLoginPage {
    private final SelenideElement
            mainPageCheckerInput = $(".f-index__title"),
            enterInSystemInput = $(".header2__right").$(byText("Войти")).parent(),
            loginCheckInput = $(".AuthLoginInputToggle-wrapper").$(byText("Почта")).parent(),
            loginNameInput = $("#passp-field-login"),
            enterInput = $(".passp-sign-in-button").$(byText("Войти")).parent(),
            passwordInput = $("#passp-field-passwd");


    public YandexFormLoginPage openPage() {
        open("/admin");
        mainPageCheckerInput.shouldHave(text("Создайте форму. Это просто"));

        return this;
    }

    public YandexFormLoginPage enterInSystem() {
        enterInSystemInput.click();

        return this;
    }

    public YandexFormLoginPage loginCheck() {
        loginCheckInput.click();

        return this;

    }

    public YandexFormLoginPage loginName(String value) {
        loginNameInput.setValue(value);

        return this;

    }

    public YandexFormLoginPage enter() {
        enterInput.click();

        return this;

    }

    public YandexFormLoginPage password(String value) {
        passwordInput.setValue(value);

        return this;

    }
}
