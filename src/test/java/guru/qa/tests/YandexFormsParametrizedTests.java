package guru.qa.tests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

public class YandexFormsParametrizedTests extends TestBase {


    @Test
    public void commentInputSectionAppearsAfterClickingAddCommentTest() {
        yandexFormsFormPage.addComment()
                .checkComment(true);
    }

    @Test
    public void commentAddButtonHidesAfterClickingAddCommentTest() {
        yandexFormsFormPage.addComment()
                .checkAddComment(true);
    }


    @ParameterizedTest(name = "Validation of the 'Comment' field: saving the field with different types of data")
    @ValueSource(strings = {"abc", "абс", "АБС", "123", "你好", "$^@%@!^", "sdf sdf", " asfasdas", "asdasd "})
    public void commentFormValidationWithDifferentDataTest(String comment) {
        yandexFormsFormPage.addComment()
                .comment(comment)
                .shortAnswerSave()
                .shortAnswerFormModal(false);
    }

    @Test
    public void questionSavesWithEmptyCommentFieldTest() {
        yandexFormsFormPage.addComment()
                .comment("")
                .shortAnswerSave()
                .shortAnswerFormModal(false);
    }

    @MethodSource("guru.qa.tests.TestData#commentFormValidationWithDifferentTextLengthTest")
    @ParameterizedTest
    public void commentFormValidationWithDifferentTextLengthTest(String comment) {
        yandexFormsFormPage.addComment()
                .comment(comment)
                .shortAnswerSave()
                .shortAnswerFormModal(false);
    }

    @Test
    public void questionIdHasDefaultValueTest() {
        yandexFormsFormPage.checkQuestionId();
    }

    @Test
    public void questionIdDeletingPlacesDefaultValueTest() {
        yandexFormsFormPage.questionId("")
                .shortAnswerSave()
                .openShortAnswerForm()
                .checkQuestionId();
    }

    @MethodSource("guru.qa.tests.TestData#questionIdValidationWithDifferentValidSymbolsLengthTest")
    @ParameterizedTest
    public void questionIdValidationWithDifferentValidSymbolsLengthTest(String id) {
        yandexFormsFormPage.questionId(id)
                .shortAnswerSave()
                .shortAnswerFormModal(false);
    }

    @ParameterizedTest
    @ValueSource(strings = {"123", "sdg", "LNMF", "-_"})
    public void questionIdValidationWithDifferentValidSymbolsTest(String id) {
        yandexFormsFormPage.questionId(id)
                .shortAnswerSave()
                .shortAnswerFormModal(false);
    }

    @MethodSource("guru.qa.tests.TestData#questionIdValidationWithDifferentInvalidSymbolsLengthTest")
    @ParameterizedTest
    public void questionIdValidationWithDifferentInvalidSymbolsLengthTest(String id) {
        yandexFormsFormPage.questionId(id)
                .shortAnswerSave()
                .shortAnswerFormModal(true)
                .shortAnswerFormModalNotification()
                .shortAnswerFormModal(true);
    }

    @ParameterizedTest
    @ValueSource(strings = {"абв", "();%;№)", "你好", "1.2", "1,2", "12 12"})
    public void questionIdValidationWithDifferentInvalidSymbolsTest(String id) {
        yandexFormsFormPage.questionId(id)
                .shortAnswerSave()
                .shortAnswerFormModalNotification()
                .shortAnswerFormModal(true);
    }

    @Test
    public void fieldsFromAndToInactiveIfCharLimiterCheckboxIsOffTest() {
        yandexFormsFormPage.charLimiterCheckbox(false)
                .charLimiterFromEnabled(false)
                .charLimiterToEnabled(false);
    }

    @Test
    public void charLimiterCheckboxIsOffIfFieldsFromAndToEmptyTest() {
        yandexFormsFormPage.charLimiterCheckbox(true)
                .shortAnswerSave()
                .openShortAnswerForm()
                .checkCharLimiterCheckbox(false);
    }

    @Test
    public void questionSavesWithCharLimiterFieldsFromAndToSetTest() {
        yandexFormsFormPage.charLimiterCheckbox(true)
                .charLimiterFromEnabled(true)
                .setCharLimiterFrom("5")
                .charLimiterToEnabled(true)
                .setCharLimiterTo("10")
                .shortAnswerSave()
                .shortAnswerFormModal(false);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "10, 10",
            "1, 1",
            "2, 2",
            "1000, 1000"
    })
    public void questionSavesWithCharLimiterFieldsFromAndToSetEqualTest(String from, String to) {
        yandexFormsFormPage.charLimiterCheckbox(true)
                .charLimiterFromEnabled(true)
                .setCharLimiterFrom(from)
                .charLimiterToEnabled(true)
                .setCharLimiterTo(to)
                .shortAnswerSave()
                .shortAnswerFormModal(false);
    }

    @Test
    public void questionSavesWithCharLimiterFieldFromSetToEmptyTest() {
        yandexFormsFormPage.charLimiterCheckbox(true)
                .charLimiterFromEnabled(true)
                .setCharLimiterFrom("4")
                .shortAnswerSave()
                .shortAnswerFormModal(false);
    }

    @Test
    public void questionSavesWithCharLimiterFieldFromEmptyToSetTest() {
        yandexFormsFormPage.charLimiterCheckbox(true)
                .charLimiterToEnabled(true)
                .setCharLimiterTo("6")
                .shortAnswerSave()
                .shortAnswerFormModal(false);
    }

    @Test
    public void questionDoesNotSaveWithCharLimiterFieldsFromBiggerThanToTest() {
        yandexFormsFormPage.charLimiterCheckbox(true)
                .charLimiterFromEnabled(true)
                .setCharLimiterFrom("12")
                .charLimiterToEnabled(true)
                .setCharLimiterTo("7")
                .shortAnswerSave()
                .shortAnswerFormModalNotification()
                .shortAnswerFormModal(true);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/charLimiterFromToValidAmounts.csv")
    public void charLimiterFromValidationWithValidAmountsTest(String from) {
        yandexFormsFormPage.charLimiterCheckbox(true)
                .charLimiterFromEnabled(true)
                .setCharLimiterFrom(from)
                .shortAnswerSave()
                .shortAnswerFormModal(false);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/charLimiterFromToValidAmounts.csv")
    public void charLimiterToValidationWithValidAmountsTest(String to) {
        yandexFormsFormPage.charLimiterCheckbox(true)
                .charLimiterToEnabled(true)
                .setCharLimiterTo(to)
                .shortAnswerSave()
                .shortAnswerFormModal(false);
    }

    @ParameterizedTest
    @ValueSource(strings = {"0", "-1", "2.5", "3,5", "e", "абв", "abc", " 1", "$2", "你好"})
    public void charLimiterFromValidationWithInvalidAmountsTest(String from) {
        yandexFormsFormPage.charLimiterCheckbox(true)
                .charLimiterFromEnabled(true)
                .setCharLimiterFrom(from)
                .shortAnswerSave()
                .shortAnswerFormModalNotification()
                .shortAnswerFormModal(true);
    }

    @ParameterizedTest
    @ValueSource(strings = {"0", "-1", "2.5", "3,5", "e", "абв", "abc", " 1", "$2", "你好"})
    public void charLimiterToValidationWithInvalidAmountsTest(String to) {
        yandexFormsFormPage.charLimiterCheckbox(true)
                .charLimiterToEnabled(true)
                .setCharLimiterTo(to)
                .shortAnswerSave()
                .shortAnswerFormModalNotification()
                .shortAnswerFormModal(true);
    }
}
