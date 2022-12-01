package guru.qa.pages.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class YandexFormsMainPage {
    private final SelenideElement
            mainPageCheckerInput = $(".f-index__title"),
            mainPageInput = $(".header2__menu-item"),
            enterInSystemInput = $(".header2__right").$(byText("Войти")).parent(),
            createFormInput = $(".button2_type_create-form"),
            testedFormInput = $(".f-index__form-list");


    public YandexFormsMainPage openMainPage() {
        open("/admin");
        mainPageCheckerInput.shouldHave(text("Создайте форму. Это просто"));

        return this;
    }

    public YandexFormsMainPage goToMainPage() {
        mainPageInput.click();

        return this;
    }

    public YandexFormsMainPage enterInSystem() {
        enterInSystemInput.click();

        return this;
    }

    public YandexFormsMainPage createForm() {
        createFormInput.click();

        return this;
    }

    public YandexFormsMainPage openTestedForm(String value) {
        testedFormInput.$(byText(value)).click();

        return this;
    }
}
