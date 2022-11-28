package guru.qa.tests;

import com.codeborne.selenide.Configuration;
import guru.qa.pages.ShortAnswerFormPage;
import guru.qa.pages.YandexFormLoginPage;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {
    static YandexFormLoginPage yandexFormLoginPage = new YandexFormLoginPage();
    static ShortAnswerFormPage shortAnswerFormPage = new ShortAnswerFormPage();

    @BeforeAll
    static void BeforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://forms.yandex.ru";
        Configuration.holdBrowserOpen = false;
        yandexFormLoginPage.openPage()
                .enterInSystem()
                .loginCheck()
                .loginName("TribalBNS")
                .enter()
                .password("TribalForTest1")
                .enter();
        shortAnswerFormPage.checkPreCreatedForm("TestForm");
    }
}
