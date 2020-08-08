import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;


class LamodaTests {
    static final String URL = "https://www.lamoda.ru/";
    static String email = "test@test.ru";
    static String password = "1234567";
    static String name = "Test";
    static String searchWord = "рубашка";

    @Test
    void cartButtonShouldExistTest() {
        open(URL);
       
        $(by("data-genders", "men").click();

        // Перейти в раздел Обувь
        $(".menu__categories").$(byText("Обувь")).click();  

        // Перейти на страницу первого элемента
        $(".products-list-item").click();

        // Найти на странице кнопку с текстром "Добавить в корзину"          
        $(byText("Добавить в корзину")).shouldBe(visible);
    }

    @Test
    void failedRegistrationTest() {
        open(URL);        
        
        // Перейти по ссылке Войти
        $(".js-auth-button").click();

        // Перейти по ссылке Создать аккаунт
        $(".login-form__register").click();

        // Заполнить форму
        $(".register-form__inner [name=email]").setValue(email);
        $(".register-form__inner [name=password]").setValue(password);
        $(".register-form__inner [name=password2]").setValue(password);
        $(".register-form__inner [name=first_name]").setValue(name).pressEnter();

        $("#registration_recaptcha ~ .login-form__error")
                .shouldHave(text("Другая учетная запись зарегистрирована на указанный адрес электронной почты."));
    }

    @Test
    void findShirtTest() {
        open(URL);
       
        $("input[type='text']").setValue(searchWord).pressEnter(); // Ввести в поле поиска "рубашка"

        // Нажать на иконку поиска
        //$("a[role='button']").click();

        // Найти на странице текст товары по запросу «рубашка»
        $("products-catalog__head .title").shouldHave(text("Товары по запросу «" + searchWord + "»"));
    }

    @Test
    void regionChoiceTest() {
        open(URL);
              
        $(".popover-target").click(); // Нажать на поле региона
        $(byText("Москва").click(); // Нажать на ссылку Определить автоматически
        $(".x-button_accented").click(); // Нажать на кнопку Запомнить выбор

        // Проверить значение поля региона
        $(".popover-target").shouldHave(text("Москва"));

    }

    @Test
    void onlineSupportTest() {
        open(URL);
     
        $(".x-button").click(); // Нажать кнопку Хорошо
        $("#hde-chat-widget").click(); // Нажать на кнопку Онлайн-консультант
        switchTo().frame($("#hde-iframe")); // Переключиться на фрейм окно
        $(".el-button").shouldHave(text("Отправить")); // Проверить наличие и текст кнопки "Отправить"
        switchTo().defaultContent(); // Переключиться обратно
        $(byText("✕").click(); // Нажать на иконку Закрыть
          
        // assert ?
    }
}
