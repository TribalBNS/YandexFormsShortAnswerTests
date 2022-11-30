package guru.qa.tests;

import com.codeborne.selenide.Configuration;
import guru.qa.pages.YandexFormsFormPage;
import guru.qa.pages.YandexFormsLoginPage;
import guru.qa.pages.components.YandexFormsMainPage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;

import static com.codeborne.selenide.Selenide.$;

public class TestBase {
    static YandexFormsLoginPage yandexFormsLoginPage = new YandexFormsLoginPage();
    static YandexFormsFormPage yandexFormsFormPage = new YandexFormsFormPage();
    static YandexFormsMainPage yandexFormsMainPage = new YandexFormsMainPage();
    static String x;

    @BeforeAll
    static void setUp() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://forms.yandex.ru";
        Configuration.holdBrowserOpen = true;
        yandexFormsMainPage.openMainPage()
                        .enterInSystem();
        yandexFormsLoginPage.loginCheck()
                .loginName("TribalBNS")
                .enter()
                .password("TribalForTest1")
                .enter();
        yandexFormsMainPage.createForm();
        yandexFormsFormPage.editFormName("321");
        x = yandexFormsFormPage.getFormName();
        yandexFormsFormPage.shortAnswerFormSet();
        yandexFormsFormPage.shortAnswerSave();
    }
    @BeforeEach
    void goToMain() {
        $(".header2__menu-item").click();
    }
}
