package guru.qa.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

public class YandexFormsShortAnswerModalTests extends TestBase {


    @Test
    @DisplayName("T-1: 'Commentary' field appears after clicking 'Add comment' button")
    public void commentInputSectionAppearsAfterClickingAddCommentTest() {
        yandexFormsFormPage.addComment()
                .checkComment(true);
    }

    @Test
    @DisplayName("T-2: 'Add comment' button disappears after clicking on it")
    public void commentAddButtonHidesAfterClickingAddCommentTest() {
        yandexFormsFormPage.addComment()
                .checkAddComment(true);
    }


    @ParameterizedTest(name = "T-3: Form saves with 'Commentary' field filled with valid data {0}")
    @ValueSource(strings = {"abc", "абс", "АБС", "123", "你好", "$^@%@!^.,", "sdf sdf", " asfasdas", "asdasd "})
    public void commentFormValidationWithDifferentDataTest(String comment) {
        yandexFormsFormPage.addComment()
                .comment(comment)
                .shortAnswerSave()
                .shortAnswerFormModal(false);
    }

    @Test
    @DisplayName("T-4: Form saves with empty 'Commentary' field")
    public void questionSavesWithEmptyCommentFieldTest() {
        yandexFormsFormPage.addComment()
                .comment("")
                .shortAnswerSave()
                .shortAnswerFormModal(false);
    }

    @MethodSource("guru.qa.tests.TestData#commentFormValidationWithDifferentTextLengthTest")
    @ParameterizedTest(name = "T-5: Form saves with 'Commentary' field filled with valid length data {0}")
    public void commentFormValidationWithDifferentTextLengthTest(String comment) {
        yandexFormsFormPage.addComment()
                .comment(comment)
                .shortAnswerSave()
                .shortAnswerFormModal(false);
    }

    @Test
    @DisplayName("T-6: 'Question ID' has a default parameter")
    public void questionIdHasDefaultValueTest() {
        yandexFormsFormPage.checkQuestionId();
    }

    @Test
    @DisplayName("T-7: Deleting 'Question ID' returns it a default parameter")
    public void questionIdDeletingPlacesDefaultValueTest() {
        yandexFormsFormPage.questionId("")
                .shortAnswerSave()
                .openShortAnswerForm()
                .checkQuestionId();
    }

    @MethodSource("guru.qa.tests.TestData#questionIdValidationWithDifferentValidSymbolsLengthTest")
    @ParameterizedTest(name = "T-8: Form saves with 'Question ID' field filled with valid length data {0}")
    public void questionIdValidationWithDifferentValidSymbolsLengthTest(String id) {
        yandexFormsFormPage.questionId(id)
                .shortAnswerSave()
                .shortAnswerFormModal(false);
    }

    @ParameterizedTest(name = "T-9: Form saves with 'Question ID' field filled with valid data {0}")
    @ValueSource(strings = {"123", "sdg", "LNMF", "-_"})
    public void questionIdValidationWithDifferentValidSymbolsTest(String id) {
        yandexFormsFormPage.questionId(id)
                .shortAnswerSave()
                .shortAnswerFormModal(false);
    }

    @MethodSource("guru.qa.tests.TestData#questionIdValidationWithDifferentInvalidSymbolsLengthTest")
    @ParameterizedTest(name = "T-10: Form doesn't save with 'Question ID' field filled with invalid length data {0}")
    public void questionIdValidationWithDifferentInvalidSymbolsLengthTest(String id) {
        yandexFormsFormPage.questionId(id)
                .shortAnswerSave()
                .shortAnswerFormModal(true)
                .shortAnswerFormModalNotification()
                .shortAnswerFormModal(true);
    }

    @ParameterizedTest(name = "T-11: Form doesn't save with 'Question ID' field filled with invalid data {0}")
    @ValueSource(strings = {"абв", "();%;№)", "你好", "1.2", "1,2", "12 12"})
    public void questionIdValidationWithDifferentInvalidSymbolsTest(String id) {
        yandexFormsFormPage.questionId(id)
                .shortAnswerSave()
                .shortAnswerFormModalNotification()
                .shortAnswerFormModal(true);
    }

    @Test
    @DisplayName("T-12: Fields 'From' and 'To' are inactive if checkbox 'Limit number of characters in response' is off")
    public void fieldsFromAndToInactiveIfCharLimiterCheckboxIsOffTest() {
        yandexFormsFormPage.charLimiterCheckbox(false)
                .charLimiterFromEnabled(false)
                .charLimiterToEnabled(false);
    }

    @Test
    @DisplayName("T-13: Checkbox 'Limit number of characters in response' is off if fields 'From' and 'To' are not filled")
    public void charLimiterCheckboxIsOffIfFieldsFromAndToEmptyTest() {
        yandexFormsFormPage.charLimiterCheckbox(true)
                .shortAnswerSave()
                .openShortAnswerForm()
                .checkCharLimiterCheckbox(false);
    }

    @Test
    @DisplayName("T-14: Form saves if fields 'From' and 'To' are filled with valid data")
    public void questionSavesWithCharLimiterFieldsFromAndToSetTest() {
        yandexFormsFormPage.charLimiterCheckbox(true)
                .charLimiterFromEnabled(true)
                .setCharLimiterFrom("5")
                .charLimiterToEnabled(true)
                .setCharLimiterTo("10")
                .shortAnswerSave()
                .shortAnswerFormModal(false);
    }

    @ParameterizedTest(name = "T-15: Form saves if fields 'From' and 'To' are filled with equal valid data {0} and {1}")
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
    @DisplayName("T-16: Form saves if 'From' field is set and 'To' field is empty")
    public void questionSavesWithCharLimiterFieldFromSetToEmptyTest() {
        yandexFormsFormPage.charLimiterCheckbox(true)
                .charLimiterFromEnabled(true)
                .setCharLimiterFrom("4")
                .shortAnswerSave()
                .shortAnswerFormModal(false);
    }

    @Test
    @DisplayName("T-17: Form saves if 'To' field is set and 'From' field is empty")
    public void questionSavesWithCharLimiterFieldFromEmptyToSetTest() {
        yandexFormsFormPage.charLimiterCheckbox(true)
                .charLimiterToEnabled(true)
                .setCharLimiterTo("6")
                .shortAnswerSave()
                .shortAnswerFormModal(false);
    }

    @Test
    @DisplayName("T-18: Form doesn't save if value 'From' is bigger than 'To'")
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

    @ParameterizedTest(name = "T-19: Form saves if 'From' field is filled with valid data {0}")
    @CsvFileSource(resources = "/charLimiterFromToValidAmounts.csv")
    public void charLimiterFromValidationWithValidAmountsTest(String from) {
        yandexFormsFormPage.charLimiterCheckbox(true)
                .charLimiterFromEnabled(true)
                .setCharLimiterFrom(from)
                .shortAnswerSave()
                .shortAnswerFormModal(false);
    }

    @ParameterizedTest(name = "T-20: Form saves if 'To' field is filled with valid data {0}")
    @CsvFileSource(resources = "/charLimiterFromToValidAmounts.csv")
    public void charLimiterToValidationWithValidAmountsTest(String to) {
        yandexFormsFormPage.charLimiterCheckbox(true)
                .charLimiterToEnabled(true)
                .setCharLimiterTo(to)
                .shortAnswerSave()
                .shortAnswerFormModal(false);
    }

    @ParameterizedTest(name = "T-21: Form doesn't save if 'From' field is filled with invalid data {0}")
    @ValueSource(strings = {"0", "-1", "2.5", "3,5", "e", "абв", "abc", " 1", "$2", "你好"})
    public void charLimiterFromValidationWithInvalidAmountsTest(String from) {
        yandexFormsFormPage.charLimiterCheckbox(true)
                .charLimiterFromEnabled(true)
                .setCharLimiterFrom(from)
                .shortAnswerSave()
                .shortAnswerFormModalNotification()
                .shortAnswerFormModal(true);
    }

    @DisplayName("T-22: Form doesn't save if 'To' field is filled with invalid data")
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
