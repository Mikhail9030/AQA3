package ru.netology;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class DebitCardApplicationTest {

    @BeforeEach
    void Setup() {
        open("http://localhost:9999");
    }

    @Test
    void shouldSuccessfulSendValidForm(){
        $("[data-test-id=name] input").setValue("Иванов-Петров Иван");
        $("[data-test-id=phone] input").setValue("+79260000000");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $(".Success_successBlock__2L3Cw").shouldHave(text(" Ваша заявка успешно отправлена!" +
                " Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldGetErrorMessageIfNameEmpty() {
        $("[data-test-id=name] input").setValue("");
        $("[data-test-id=phone] input").setValue("+79260000000");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $(".input_type_text .input__sub").shouldHave(text("Поле обязательно для заполнения"));
    }

    @Test
    void shouldGetErrorMessageIfNameInvalid() {
        $("[data-test-id=name] input").setValue("Ivanov Ivan");
        $("[data-test-id=phone] input").setValue("+79260000000");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $(".input_type_text .input__sub").shouldHave(text("Имя и Фамилия указаные неверно." +
                " Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldGetErrorMessageIfPhoneInvalid() {
        $("[data-test-id=name] input").setValue("Иванов-Петров Иван");
        $("[data-test-id=phone] input").setValue("+792600000000");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $(".input_type_tel .input__sub").shouldHave(text("Телефон указан неверно." +
                " Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldGetErrorMessageIfPhoneEmpty() {
        $("[data-test-id=name] input").setValue("Иванов-Петров Иван");
        $("[data-test-id=phone] input").setValue("");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $(".input_type_tel .input__sub").shouldHave(text("Поле обязательно для заполнения"));
    }

    @Test
    void shouldInvalidCheckbox() {
        $("[data-test-id=name] input").setValue("Иванов-Петров Иван");
        $("[data-test-id=phone] input").setValue("+79260000000");
        $(".button").click();
        $("[data-test-id='agreement'].input_invalid .checkbox__text")
                .shouldHave(exactText("Я соглашаюсь с условиями обработки и использования моих персональных данных" +
                        " и разрешаю сделать запрос в бюро кредитных историй"));
    }

}
