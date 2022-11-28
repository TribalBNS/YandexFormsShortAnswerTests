package guru.qa.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class ShortAnswerFormPage {
    private final SelenideElement
            preCreatedFormInput = $(".f-index__form-list-item_id_63849b3de010dbf2a79a2686"),
            preCreatedFormNameInput = $(".f-edit-form__label"),
            shortAnswerFormInput = $(".f-question__label"),
            questionInput = $(".f-edit-question-form__question-content").$(".input__control"),
            addCommentInput = $(".f-edit-question-form__add-comment"),
            commentInput = $(".f-edit-question-form__comment-content").$(".input__control"),
            questionIdInput = $(".f-edit-question-form__slug").$(".input__control"),
            answerCharLimiterCheckboxInput = $(".checkbox_role_limits:not(.checkbox_checked_yes)"),
            answerCharLimiterInput = $(byText("Ограничить количество символов в ответе")),
            answerCharLimiterFromInput = $(".input_role_limits-min").$(".input__control"),
            answerCharLimiterToInput = $(".input_role_limits-max").$(".input__control"),
            shortAnswerSaveInput = $(".f-edit-question-form__buttons").$(byText("Сохранить")).parent();

    public ShortAnswerFormPage checkPreCreatedForm(String value) {
        preCreatedFormInput.click();
        preCreatedFormNameInput.shouldHave(text(value));

        return this;
    }

    public ShortAnswerFormPage openShortAnswerForm() {
        shortAnswerFormInput.click();

        return this;
    }

    public ShortAnswerFormPage question(String question) {
        questionInput.setValue(question);

        return this;
    }

    public ShortAnswerFormPage addComment() {
        if (addCommentInput.isDisplayed())
            addCommentInput.click();

        return this;
    }

    public ShortAnswerFormPage comment(String comment) {
        commentInput.setValue(comment);

        return this;
    }

    public ShortAnswerFormPage questionId(String questionId) {
        questionIdInput.setValue(questionId);

        return this;
    }

    public ShortAnswerFormPage answerCharLimiterCheckbox() {
        answerCharLimiterInput.scrollIntoView(true);
        if (answerCharLimiterCheckboxInput.isDisplayed())
            answerCharLimiterInput.click();

        return this;
    }

    public ShortAnswerFormPage answerCharLimiterFrom(String from) {
        answerCharLimiterFromInput.setValue(from);

        return this;
    }

    public ShortAnswerFormPage answerCharLimiterTo(String to) {
        answerCharLimiterToInput.setValue(to);

        return this;
    }

    public ShortAnswerFormPage shortAnswerSave() {
        shortAnswerSaveInput.click();

        return this;
    }

    public ShortAnswerFormPage checkQuestion(String question) {
        questionInput.shouldHave(Condition.text(question));

        return this;
    }

    public ShortAnswerFormPage checkAddComment(Boolean hidden) {
        if (hidden)
            addCommentInput.shouldBe(Condition.hidden);

        return this;
    }

    public ShortAnswerFormPage checkComment(String comment) {
        commentInput.shouldHave(Condition.text(comment));

        return this;
    }

    public ShortAnswerFormPage checkQuestionId(String questionId) {
        questionIdInput.shouldHave(Condition.value(questionId));

        return this;
    }

    public ShortAnswerFormPage checkAnswerCharLimiterCheckbox(Boolean hidden) {
        answerCharLimiterInput.scrollIntoView(true);
        if (hidden)
            answerCharLimiterCheckboxInput.shouldBe(Condition.hidden);

        return this;
    }

    public ShortAnswerFormPage checkAnswerCharLimiterFrom(String from) {
        answerCharLimiterFromInput.shouldHave(Condition.value(from));

        return this;
    }

    public ShortAnswerFormPage checkAnswerCharLimiterTo(String to) {
        answerCharLimiterToInput.shouldHave(Condition.value(to));

        return this;
    }
}
