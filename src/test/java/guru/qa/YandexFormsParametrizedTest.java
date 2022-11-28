package guru.qa;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class YandexFormsParametrizedTest {

    @BeforeAll
    static void BeforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://forms.yandex.ru";
        Configuration.holdBrowserOpen = false;
        open("/admin");
        $(".header2__right").$(byText("Войти")).parent().click();
        $(".AuthLoginInputToggle-wrapper").$(byText("Почта")).parent().click();
        $("#passp-field-login").setValue("TribalBNS");
        $(".passp-sign-in-button").$(byText("Войти")).parent().click();
        $("#passp-field-passwd").setValue("TribalForTest1");
        $(".passp-sign-in-button").$(byText("Войти")).parent().click();
        $(".f-index__form-list-item_id_63849b3de010dbf2a79a2686").click();
        $(".f-edit-form__label").shouldHave(Condition.text("TestForm"));
    }


    static Stream<Arguments> formQuestionCommentaryIDMinMaxSymbolsEditingInShortAnswerTest() {
        return Stream.of(
                Arguments.of("TestQuestion", "TestComment", "RandomID123321", "1", "10"),
                Arguments.of(RandomStringUtils.randomAlphanumeric(1), RandomStringUtils.randomAlphanumeric(1), RandomStringUtils.randomAlphanumeric(1), "1", "2"),
                Arguments.of(RandomStringUtils.randomAlphanumeric(2), RandomStringUtils.randomAlphanumeric(2), RandomStringUtils.randomAlphanumeric(2), "2", "2"),
                Arguments.of(RandomStringUtils.randomAlphanumeric(25), RandomStringUtils.randomAlphanumeric(50), RandomStringUtils.randomAlphanumeric(25), "5", "100"),
                Arguments.of(RandomStringUtils.randomAlphanumeric(50), RandomStringUtils.randomAlphanumeric(500),
                        RandomStringUtils.randomAlphanumeric(50), "100", "1000")
        );
    }

    @MethodSource
    @ParameterizedTest(name = "Checking the possibility of editing question({0}), comment({1}), " +
            "minimum({2}) and maximum({3}) symbols in 'Short Answer' section of Yandex Forms")
    public void formQuestionCommentaryIDMinMaxSymbolsEditingInShortAnswerTest(String question, String comment, String id, String from, String to) {
        $(".f-question__label").click();
        $(".f-edit-question-form__question-content").$(".input__control").setValue(question);
        if ($(".f-edit-question-form__add-comment").isDisplayed())
            $(".f-edit-question-form__add-comment").click();
        $(".f-edit-question-form__comment-content").$(".input__control").setValue(comment);
        $(".f-edit-question-form__slug").$(".input__control").setValue(id);
        if ($(".checkbox_role_limits:not(.checkbox_checked_yes)").isDisplayed())
            $(byText("Ограничить количество символов в ответе")).scrollIntoView(true).click();
        $(".input_role_limits-min").$(".input__control").setValue(from);
        $(".input_role_limits-max").$(".input__control").setValue(to);
        $(".f-edit-question-form__buttons").$(byText("Сохранить")).parent().click();

        $(byText(question)).parent().click();
        $(".f-edit-question-form__question-content").$(".input__control").shouldHave(Condition.text(question));
        $(".f-edit-question-form__add-comment").shouldBe(Condition.hidden);
        $(".f-edit-question-form__comment-content").$(".input__control").shouldHave(Condition.text(comment));
        $(".f-edit-question-form__slug").$(".input__control").shouldHave(Condition.value(id));
        $(".checkbox_role_limits:not(.checkbox_checked_yes)").shouldBe(Condition.hidden);
        $(".input_role_limits-min").$(".input__control").shouldHave(Condition.value(from));
        $(".input_role_limits-max").$(".input__control").shouldHave(Condition.value(to));
        $(".f-edit-question-form__buttons").$(byText("Сохранить")).parent().click();
    }
}
