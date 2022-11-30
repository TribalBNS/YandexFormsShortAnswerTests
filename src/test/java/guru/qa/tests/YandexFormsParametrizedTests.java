package guru.qa.tests;

import com.codeborne.selenide.Condition;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Selenide.$;

public class YandexFormsParametrizedTests extends TestBase {


    static Stream<Arguments> commentFormValidationWithDifferentTextLengthTest() {
        return Stream.of(
                Arguments.of(RandomStringUtils.randomAlphanumeric(1)),
                Arguments.of(RandomStringUtils.randomAlphanumeric(2)),
                Arguments.of(RandomStringUtils.randomAlphanumeric(50)),
                Arguments.of(RandomStringUtils.randomAlphanumeric(500))
        );
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

    @Test
    public void commentInputSectionAppearsAfterClickingAddCommentTest() {
        yandexFormsMainPage.openTestedForm(x);
        yandexFormsFormPage.openShortAnswerForm()
                .addComment();
        $(".f-edit-question-form__comment-content").$(".input__control").shouldBe(Condition.visible);
        yandexFormsFormPage.shortAnswerSave();
    }

    @Test
    public void commentAddButtonHidesAfterClickingAddCommentTest() {
        yandexFormsMainPage.openTestedForm(x);
        yandexFormsFormPage.openShortAnswerForm()
                .addComment();
        $(".f-edit-question-form__add-comment").shouldBe(hidden);
        yandexFormsFormPage.shortAnswerSave();
    }


    @ParameterizedTest(name = "Validation of the 'Comment' field: saving the field with different types of data")
    @ValueSource(strings = {"abc", "абс", "АБС", "123"})
    public void commentFormValidationWithDifferentDataTest(String comment) {
        yandexFormsMainPage.openTestedForm(x);
        yandexFormsFormPage.openShortAnswerForm()
                .addComment()
                .comment(comment)
                .shortAnswerSave();
        $(".modal__content").shouldBe(hidden);
    }

    @Test
    public void questionSavesWithEmptyCommentFieldTest() {
        yandexFormsMainPage.openTestedForm(x);
        yandexFormsFormPage.openShortAnswerForm()
                .addComment()
                .comment("")
                .shortAnswerSave();
        $(".modal__content").shouldBe(hidden);
    }

    @MethodSource
    @ParameterizedTest
    public void commentFormValidationWithDifferentTextLengthTest(String comment) {
        yandexFormsMainPage.openTestedForm(x);
        yandexFormsFormPage.openShortAnswerForm()
                .addComment()
                .comment(comment)
                .shortAnswerSave();
        $(".modal__content").shouldBe(hidden);
    }


    @MethodSource
    @ParameterizedTest(name = "Checking the possibility of editing question({0}), comment({1}), " +
            "minimum({2}) and maximum({3}) symbols in 'Short Answer' section of Yandex Forms")
    public void formQuestionCommentaryIDMinMaxSymbolsEditingInShortAnswerTest(String question, String comment, String id, String from, String to) {
        yandexFormsMainPage.openTestedForm(x);
        yandexFormsFormPage.openShortAnswerForm()
                .question(question)
                .addComment()
                .comment(comment)
                .questionId(id)
                .answerCharLimiterCheckbox()
                .answerCharLimiterFrom(from)
                .answerCharLimiterTo(to)
                .shortAnswerSave();

        //Checking that all the values were actually saved
        yandexFormsFormPage.openShortAnswerForm()
                .checkQuestion(question)
                .checkAddComment(true)
                .checkComment(comment)
                .checkQuestionId(id)
                .checkAnswerCharLimiterCheckbox(true)
                .checkAnswerCharLimiterFrom(from)
                .checkAnswerCharLimiterTo(to)
                .shortAnswerSave();
    }
}
