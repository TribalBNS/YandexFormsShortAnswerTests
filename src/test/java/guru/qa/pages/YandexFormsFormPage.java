package guru.qa.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

import static guru.qa.tests.TestData.*;

public class YandexFormsFormPage {
    private final SelenideElement
            formNameEditorInput = $(".f-edit-form__label-edit"),
            formNameEditInput = $(".input__control"),
            formNameGetterInput = $(".f-edit-form__label-text"),
            shortAnswerFormSetInput = $(".f-question-type_type_text"),
            shortAnswerFormInput = $(".f-question__label"),
            addCommentInput = $(".f-edit-question-form__add-comment"),
            deleteCommentInput = $(".link_role_remove-comment"),
            commentInput = $(".f-edit-question-form__comment-content").$(".input__control"),
            questionIdInput = $(".f-edit-question-form__slug").$(".input__control"),
            emptyQuestionIdInput = $(".input_role_slug").$(".input__clear"),
            charLimiterCheckboxInput = $(".checkbox_role_limits"),
            charLimiterInput = $(byText("Ограничить количество символов в ответе")),
            charLimiterFromInput = $(".input_role_limits-min").$(".input__control"),
            charLimiterToInput = $(".input_role_limits-max").$(".input__control"),
            shortAnswerSaveInput = $(".f-edit-question-form__buttons").$(byText("Сохранить")).parent(),
            shortAnswerCancelInput = $(".button2_type_cancel"),
            shortAnswerFormModalInput = $(".modal__content"),
            shortAnswerFormModalNotificationInput = $(".m-notification__content"),
            shortAnswerFormMoreInput = $(".f-edit-form__more"),
            shortAnswerFormDeleteInput = $(byText("Удалить форму")),
            shortAnswerFormConfirmDeleteInput = $(".button2_role_remove");

    public YandexFormsFormPage editFormName(String value) {
        formNameEditorInput.click();
        formNameEditInput.append(value).pressEnter();

        return this;
    }

    public YandexFormsFormPage getFormName() {
        formName = formNameGetterInput.getOwnText();

        return this;
    }

    public YandexFormsFormPage shortAnswerFormSet() {
        shortAnswerFormSetInput.click();

        return this;
    }

    public YandexFormsFormPage openShortAnswerForm() {
        shortAnswerFormInput.click();

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

    public YandexFormsFormPage emptyQuestionId() {
        if (emptyQuestionIdInput.isDisplayed())
            emptyQuestionIdInput.click();

        return this;
    }

    public YandexFormsFormPage setCharLimiterFrom(String from) {
        charLimiterFromInput.setValue(from);

        return this;
    }

    public YandexFormsFormPage setCharLimiterTo(String to) {
        charLimiterToInput.setValue(to);

        return this;
    }

    public YandexFormsFormPage checkCharLimiterFrom(String from) {
        charLimiterFromInput.shouldHave(value(from));

        return this;
    }

    public YandexFormsFormPage checkCharLimiterTo(String to) {
        charLimiterToInput.shouldHave(value(to));

        return this;
    }

    public YandexFormsFormPage shortAnswerSave() {
        shortAnswerSaveInput.click();

        return this;
    }

    public YandexFormsFormPage checkAddComment(Boolean hidden) {
        if (hidden)
            addCommentInput.shouldBe(Condition.hidden);

        return this;
    }

    public YandexFormsFormPage deleteComment() {
        if (deleteCommentInput.isDisplayed())
            deleteCommentInput.click();

        return this;
    }

    public YandexFormsFormPage checkComment(Boolean visible) {
        if (visible)
            commentInput.shouldBe(Condition.visible);
        else
            commentInput.shouldBe(Condition.hidden);

        return this;
    }

    public YandexFormsFormPage checkQuestionId() {
        questionIdInput.shouldNotBe(empty);

        return this;
    }

    public YandexFormsFormPage checkCharLimiterCheckbox(Boolean on) {
        charLimiterInput.scrollIntoView(true);
        if (on)
            charLimiterCheckboxInput.shouldHave(cssClass(".checkbox_checked_yes"));
        else
            charLimiterCheckboxInput.shouldNotHave(cssClass(".checkbox_checked_yes"));

        return this;
    }

    public YandexFormsFormPage shortAnswerCancel() {
        sleep(1000);
        if (shortAnswerFormModalInput.isDisplayed())
            shortAnswerCancelInput.click();

        return this;
    }

    public YandexFormsFormPage shortAnswerFormModal(Boolean visible) {
        if (visible)
            shortAnswerFormModalInput.shouldBe(Condition.visible);
        else
            shortAnswerFormModalInput.shouldNotBe(Condition.visible);

        return this;
    }

    public YandexFormsFormPage shortAnswerFormModalNotification() {
        shortAnswerFormModalNotificationInput.should(appear);

        return this;
    }

    public YandexFormsFormPage charLimiterCheckbox(boolean on) {
        charLimiterInput.scrollIntoView(true);
        if (on) {
            if ($(".checkbox_role_limits:not(.checkbox_checked_yes)").isDisplayed())
                charLimiterInput.click();
        }
        if (!on) {
            if ($(".checkbox_role_limits.checkbox_checked_yes").isDisplayed())
                charLimiterInput.click();
        }

        return this;
    }

    public YandexFormsFormPage charLimiterFromEnabled(boolean value) {
        if (value)
            charLimiterFromInput.shouldBe(enabled);
        else
            charLimiterFromInput.shouldNotBe(enabled);

        return this;
    }

    public YandexFormsFormPage charLimiterToEnabled(boolean value) {
        if (value)
            charLimiterToInput.shouldBe(enabled);
        else
            charLimiterToInput.shouldNotBe(enabled);

        return this;
    }

    public YandexFormsFormPage shortAnswerFormDelete() {
        shortAnswerFormMoreInput.click();
        shortAnswerFormDeleteInput.click();
        shortAnswerFormConfirmDeleteInput.click();

        return this;
    }
}
