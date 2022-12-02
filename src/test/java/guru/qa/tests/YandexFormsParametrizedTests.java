package guru.qa.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

public class YandexFormsParametrizedTests extends TestBase {


    @Test
    @DisplayName("Поле ввода коментария появляется после нажатия на кнопку 'Добавить комментарий'")
    public void commentInputSectionAppearsAfterClickingAddCommentTest() {
        yandexFormsFormPage.addComment()
                .checkComment(true);
    }

    @Test
    @DisplayName("Кнопка 'Добавить комментарий' исчезает после нажатия на неё")
    public void commentAddButtonHidesAfterClickingAddCommentTest() {
        yandexFormsFormPage.addComment()
                .checkAddComment(true);
    }


    @ParameterizedTest(name = "Проверка возможности заполнения поля 'Комментарий' различными типами данных")
    @ValueSource(strings = {"abc", "абс", "АБС", "123", "你好", "$^@%@!^.,", "sdf sdf", " asfasdas", "asdasd "})
    public void commentFormValidationWithDifferentDataTest(String comment) {
        yandexFormsFormPage.addComment()
                .comment(comment)
                .shortAnswerSave()
                .shortAnswerFormModal(false);
    }

    @Test
    @DisplayName("Вопрос сохраняется при пустом поле 'Комментарий'")
    public void questionSavesWithEmptyCommentFieldTest() {
        yandexFormsFormPage.addComment()
                .comment("")
                .shortAnswerSave()
                .shortAnswerFormModal(false);
    }

    @MethodSource("guru.qa.tests.TestData#commentFormValidationWithDifferentTextLengthTest")
    @ParameterizedTest(name = "Проверка возможности заполнения поля 'Комментарий' текстом длиной {0} символов")
    public void commentFormValidationWithDifferentTextLengthTest(String comment) {
        yandexFormsFormPage.addComment()
                .comment(comment)
                .shortAnswerSave()
                .shortAnswerFormModal(false);
    }

    @Test
    @DisplayName("Поле 'Идентификатор вопроса' имеет значение по умолчанию")
    public void questionIdHasDefaultValueTest() {
        yandexFormsFormPage.checkQuestionId();
    }

    @Test
    @DisplayName("При удалении идентификатора вопроса ему присвается значение по умолчанию")
    public void questionIdDeletingPlacesDefaultValueTest() {
        yandexFormsFormPage.questionId("")
                .shortAnswerSave()
                .openShortAnswerForm()
                .checkQuestionId();
    }

    @MethodSource("guru.qa.tests.TestData#questionIdValidationWithDifferentValidSymbolsLengthTest")
    @ParameterizedTest(name = "Проверка возможности заполнения поля 'Идентификатор вопроса' текстом длиной {0} символов")
    public void questionIdValidationWithDifferentValidSymbolsLengthTest(String id) {
        yandexFormsFormPage.questionId(id)
                .shortAnswerSave()
                .shortAnswerFormModal(false);
    }

    @ParameterizedTest(name = "Проверка возможности заполнения поля 'Идентификатор вопроса' валидными символами {0}")
    @ValueSource(strings = {"123", "sdg", "LNMF", "-_"})
    public void questionIdValidationWithDifferentValidSymbolsTest(String id) {
        yandexFormsFormPage.questionId(id)
                .shortAnswerSave()
                .shortAnswerFormModal(false);
    }

    @MethodSource("guru.qa.tests.TestData#questionIdValidationWithDifferentInvalidSymbolsLengthTest")
    @ParameterizedTest(name = "Проверка невозможности заполнения поля 'Идентификатор вопроса' текстом длиной {0} символов")
    public void questionIdValidationWithDifferentInvalidSymbolsLengthTest(String id) {
        yandexFormsFormPage.questionId(id)
                .shortAnswerSave()
                .shortAnswerFormModal(true)
                .shortAnswerFormModalNotification()
                .shortAnswerFormModal(true);
    }

    @ParameterizedTest(name = "Проверка невозможности заполнения поля 'Идентификатор вопроса' невалидными символами {0}")
    @ValueSource(strings = {"абв", "();%;№)", "你好", "1.2", "1,2", "12 12"})
    public void questionIdValidationWithDifferentInvalidSymbolsTest(String id) {
        yandexFormsFormPage.questionId(id)
                .shortAnswerSave()
                .shortAnswerFormModalNotification()
                .shortAnswerFormModal(true);
    }

    @Test
    @DisplayName("Поля 'От' и 'До' неактивны при выключенном чекбоксе 'Ограничить количество символов в ответе'")
    public void fieldsFromAndToInactiveIfCharLimiterCheckboxIsOffTest() {
        yandexFormsFormPage.charLimiterCheckbox(false)
                .charLimiterFromEnabled(false)
                .charLimiterToEnabled(false);
    }

    @Test
    @DisplayName("Чекбокс 'Ограничить количество символов в ответе' выключается, если поля 'От' и 'До' не заполнены")
    public void charLimiterCheckboxIsOffIfFieldsFromAndToEmptyTest() {
        yandexFormsFormPage.charLimiterCheckbox(true)
                .shortAnswerSave()
                .openShortAnswerForm()
                .checkCharLimiterCheckbox(false);
    }

    @Test
    @DisplayName("Вопрос сохраняется, если поля 'От' и 'До' заполнены")
    public void questionSavesWithCharLimiterFieldsFromAndToSetTest() {
        yandexFormsFormPage.charLimiterCheckbox(true)
                .charLimiterFromEnabled(true)
                .setCharLimiterFrom("5")
                .charLimiterToEnabled(true)
                .setCharLimiterTo("10")
                .shortAnswerSave()
                .shortAnswerFormModal(false);
    }

    @ParameterizedTest(name = "Вопрос сохраняется, если поля 'От' и 'До' заполнены равными значениями {0} и {1}")
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
    @DisplayName("Вопрос сохраняется, если поле 'От' заполнено, а поле 'До' нет")
    public void questionSavesWithCharLimiterFieldFromSetToEmptyTest() {
        yandexFormsFormPage.charLimiterCheckbox(true)
                .charLimiterFromEnabled(true)
                .setCharLimiterFrom("4")
                .shortAnswerSave()
                .shortAnswerFormModal(false);
    }

    @Test
    @DisplayName("Вопрос сохраняется, если поле 'До' заполнено, а поле 'От' нет")
    public void questionSavesWithCharLimiterFieldFromEmptyToSetTest() {
        yandexFormsFormPage.charLimiterCheckbox(true)
                .charLimiterToEnabled(true)
                .setCharLimiterTo("6")
                .shortAnswerSave()
                .shortAnswerFormModal(false);
    }

    @Test
    @DisplayName("Вопрос yt сохраняется, если значение поля 'От' больше значения поля 'До'")
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

    @ParameterizedTest(name = "Проверка возможности заполнения поля 'От' различными валидными значениями")
    @CsvFileSource(resources = "/charLimiterFromToValidAmounts.csv")
    public void charLimiterFromValidationWithValidAmountsTest(String from) {
        yandexFormsFormPage.charLimiterCheckbox(true)
                .charLimiterFromEnabled(true)
                .setCharLimiterFrom(from)
                .shortAnswerSave()
                .shortAnswerFormModal(false);
    }

    @ParameterizedTest(name = "Проверка возможности заполнения поля 'До' различными валидными значениями")
    @CsvFileSource(resources = "/charLimiterFromToValidAmounts.csv")
    public void charLimiterToValidationWithValidAmountsTest(String to) {
        yandexFormsFormPage.charLimiterCheckbox(true)
                .charLimiterToEnabled(true)
                .setCharLimiterTo(to)
                .shortAnswerSave()
                .shortAnswerFormModal(false);
    }

    @ParameterizedTest(name = "Проверка невозможности заполнения поля 'От' различными невалидными значениями")
    @ValueSource(strings = {"0", "-1", "2.5", "3,5", "e", "абв", "abc", " 1", "$2", "你好"})
    public void charLimiterFromValidationWithInvalidAmountsTest(String from) {
        yandexFormsFormPage.charLimiterCheckbox(true)
                .charLimiterFromEnabled(true)
                .setCharLimiterFrom(from)
                .shortAnswerSave()
                .shortAnswerFormModalNotification()
                .shortAnswerFormModal(true);
    }

    @ParameterizedTest(name = "Проверка невозможности заполнения поля 'До' различными невалидными значениями")
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
