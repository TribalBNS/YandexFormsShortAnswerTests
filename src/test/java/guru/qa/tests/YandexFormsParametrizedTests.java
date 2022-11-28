package guru.qa.tests;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class YandexFormsParametrizedTests extends TestBase {


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
        shortAnswerFormPage.openShortAnswerForm()
                .question(question)
                .addComment()
                .comment(comment)
                .questionId(id)
                .answerCharLimiterCheckbox()
                .answerCharLimiterFrom(from)
                .answerCharLimiterTo(to)
                .shortAnswerSave();

        //Checking that all the values were actually saved
        shortAnswerFormPage.openShortAnswerForm()
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
