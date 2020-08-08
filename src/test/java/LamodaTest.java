import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

class LamodaTest {
    static final String URL = "https://www.lamoda.ru/";
    static String email = "test@test.ru";
    static String password = "1234567";
    static String name = "Test";
    static String searchWord = "рубашка";

    @BeforeEach
    void siteOpen() {
        // Открыть сайт Lamoda
        open(URL);
    }

    @Test
    void checkButton() {
        $("[data-genders='men']").click();

        // Перейти в раздел Обувь
        $x("//*[@class='link' and contains(text(),'Обувь')]").click();

        // Перейти на страницу первого элемента
        $(".products-list-item").click();

        // Найти на странице кнопку с текстром "Добавить в корзину"
        $(".popover-target button").shouldHave(text("Добавить в корзину"));
    }

    @Test
    void failedRegistration() {
        // Перейти по ссылке Войти
        $(".js-auth-button").click();

        // Перейти по ссылке Создать аккаунт
        $(".login-form__register").click();

        // Заполнить форму
        $(".register-form__inner [name=email]").setValue(email);
        $(".register-form__inner [name=password]").setValue(password);
        $(".register-form__inner [name=password2]").setValue(password);
        $(".register-form__inner [name=first_name]").setValue(name);

        // Нажать Зарегистрироваться
        $(".js-registration-button").click();

        $("#registration_recaptcha ~ .login-form__error")
                .shouldHave(text("Другая учетная запись зарегистрирована на указанный адрес электронной почты."));

    }

    @Test
    void findShirt() {
        // Ввести в поле поиска "рубашка"
        $x("//input[@type='text']").setValue(searchWord).pressEnter();

        // Нажать на иконку поиска
        //$("a[role='button']").click();

        // Найти на странице текст товары по запросу «рубашка»
        $(".title h2").shouldHave(text("Товары по запросу «" + searchWord + "»"));
    }

    @Test
    void regionChoice() {
        // Нажать на поле региона
        $(".popover-target").click();

        // Нажать на ссылку Определить автоматически
        $x("//a[contains(text(),'Москва')]").click();

        // Нажать на кнопку Запомнить выбор
        $(".x-button_accented").click();

        // Проверить значение поля региона
        $(".popover-target").shouldHave(text("Москва"));

    }

    @Test
    void onlineSupport() {
        // Нажать кнопку Хорошо
        $(".x-button").click();

        // Нажать на кнопку Онлайн-консультант
        $("#hde-chat-widget").click();

        // Переключиться на фрейм окно
        Selenide.switchTo().frame($("#hde-iframe"));

        // Проверить наличие и текст кнопки "Отправить"
        $(".el-button").shouldHave(text("Отправить"));

        // Переключиться обратно
        switchTo().defaultContent();

        // Нажать на иконку Закрыть
        $x("//div[contains(text(),'✕')]").click();
    }
}