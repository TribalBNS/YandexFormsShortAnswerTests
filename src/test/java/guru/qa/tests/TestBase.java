package guru.qa.tests;

import com.codeborne.selenide.Configuration;
import guru.qa.pages.YandexFormsFormPage;
import guru.qa.pages.YandexFormsLoginPage;
import guru.qa.pages.components.YandexFormsMainPage;
import org.junit.jupiter.api.*;

import static guru.qa.tests.TestData.*;

public class TestBase {
    static YandexFormsLoginPage yandexFormsLoginPage = new YandexFormsLoginPage();
    static YandexFormsFormPage yandexFormsFormPage = new YandexFormsFormPage();
    static YandexFormsMainPage yandexFormsMainPage = new YandexFormsMainPage();

    @BeforeAll
    static void setUp() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://forms.yandex.ru";
        Configuration.holdBrowserOpen = false;
        yandexFormsMainPage.openMainPage()
                .enterInSystem();
        yandexFormsLoginPage.loginCheck()
                .loginName(LOGIN)
                .enter()
                .password(PASSWORD)
                .enter();
        yandexFormsMainPage.createForm();
        yandexFormsFormPage.editFormName(formNameAppender);
        yandexFormsFormPage.getFormName();
        yandexFormsFormPage.shortAnswerFormSet();
        yandexFormsFormPage.shortAnswerSave();
    }

    @AfterAll
    static void formDelete() {
        yandexFormsMainPage.goToMainPage()
                .openTestedForm(formName);
        yandexFormsFormPage.shortAnswerFormDelete();


    }

    @BeforeEach
    void clearTestedFields() {
        yandexFormsMainPage.goToMainPage()
                .openTestedForm(formName);
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
