package guru.qa.tests;

import com.codeborne.selenide.Configuration;
import guru.qa.pages.YandexFormsFormPage;
import guru.qa.pages.YandexFormsLoginPage;
import guru.qa.pages.components.YandexFormsMainPage;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.*;

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
        yandexFormsFormPage.editFormName(RandomStringUtils.randomAlphanumeric(5));
        x = yandexFormsFormPage.getFormName();
        yandexFormsFormPage.shortAnswerFormSet();
        yandexFormsFormPage.shortAnswerSave();
    }

    @AfterAll
    static void formDelete() {
        yandexFormsMainPage.goToMainPage()
                .openTestedForm(x);
        yandexFormsFormPage.shortAnswerFormDelete();


    }

    @BeforeEach
    void clearTestedFields() {
        yandexFormsMainPage.goToMainPage()
                .openTestedForm(x);
        yandexFormsFormPage.openShortAnswerForm();
    }

    @AfterEach
    void closeForm() {
        yandexFormsFormPage.shortAnswerCancel()
                .openShortAnswerForm()
                .deleteComment()
                .emptyQuestionId()
                .charLimiterCheckbox(false)
                .shortAnswerSave();
    }
}
