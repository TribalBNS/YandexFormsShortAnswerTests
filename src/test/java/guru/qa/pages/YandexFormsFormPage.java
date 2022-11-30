package guru.qa.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class YandexFormsFormPage {
    private final SelenideElement
            formNameEditorInput = $(".f-edit-form__label-edit"),
            formNameEditInput = $(".input__control"),
            formNameGetterInput = $(".f-edit-form__label-text"),
            shortAnswerFormSetInput = $(".f-question-type_type_text"),
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

public YandexFormsFormPage editFormName(String value) {
    formNameEditorInput.click();
    formNameEditInput.append(value).pressEnter();

    return this;
}

public String getFormName() {
    String x = formNameGetterInput.getOwnText();

    return x;
}
public YandexFormsFormPage shortAnswerFormSet() {
    shortAnswerFormSetInput.click();

    return this;
}
    public YandexFormsFormPage openShortAnswerForm() {
        shortAnswerFormInput.click();

        return this;
    }

    public YandexFormsFormPage question(String question) {
        questionInput.setValue(question);

        return this;
    }

    public YandexFormsFormPage addComment() {
        if (addCommentInput.isDisplayed())
            addCommentInput.click();

        return this;
    }

    public YandexFormsFormPage comment(String comment) {
        commentInput.setValue(comment);

        return this;
    }

    public YandexFormsFormPage questionId(String questionId) {
        questionIdInput.setValue(questionId);

        return this;
    }

    public YandexFormsFormPage answerCharLimiterCheckbox() {
        answerCharLimiterInput.scrollIntoView(true);
        if (answerCharLimiterCheckboxInput.isDisplayed())
            answerCharLimiterInput.click();

        return this;
    }

    public YandexFormsFormPage answerCharLimiterFrom(String from) {
        answerCharLimiterFromInput.setValue(from);

        return this;
    }

    public YandexFormsFormPage answerCharLimiterTo(String to) {
        answerCharLimiterToInput.setValue(to);

        return this;
    }

    public YandexFormsFormPage shortAnswerSave() {
        shortAnswerSaveInput.click();

        return this;
    }

    public YandexFormsFormPage checkQuestion(String question) {
        questionInput.shouldHave(Condition.text(question));

        return this;
    }

    public YandexFormsFormPage checkAddComment(Boolean hidden) {
        if (hidden)
            addCommentInput.shouldBe(Condition.hidden);

        return this;
    }

    public YandexFormsFormPage checkComment(String comment) {
        commentInput.shouldHave(Condition.text(comment));

        return this;
    }

    public YandexFormsFormPage checkQuestionId(String questionId) {
        questionIdInput.shouldHave(Condition.value(questionId));

        return this;
    }

    public YandexFormsFormPage checkAnswerCharLimiterCheckbox(Boolean hidden) {
        answerCharLimiterInput.scrollIntoView(true);
        if (hidden)
            answerCharLimiterCheckboxInput.shouldBe(Condition.hidden);

        return this;
    }

    public YandexFormsFormPage checkAnswerCharLimiterFrom(String from) {
        answerCharLimiterFromInput.shouldHave(Condition.value(from));

        return this;
    }

    public YandexFormsFormPage checkAnswerCharLimiterTo(String to) {
        answerCharLimiterToInput.shouldHave(Condition.value(to));

        return this;
    }
}
