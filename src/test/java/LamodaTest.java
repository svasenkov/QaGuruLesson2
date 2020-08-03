import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

class LamodaTest {
    static final String URL = "https://www.lamoda.ru/";
    static String email = "test@test.ru";
    static String password = "1234567";
    static String name = "Test";

    @BeforeEach
    void siteOpen() {
        // Открыть сайт Lamoda
        open(URL);
    }

    @Test
    void findShoes() {
        // Перейти в раздел Мужчинам
        $(byXpath("/html/body/div[1]/div[2]/div[2]/a[2]")).click();

        // Перейти в раздел Обувь
        $(byXpath("//*[@id=\"menu-wrapper\"]/div/div[2]/a[3]/span")).click();

        // Найти на странице текст Мужская обувь
        $("html").shouldHave(text("Мужская обувь"));
    }

    @Test
    void registration() {
        // Перейти по ссылке Войти
        $(byXpath("/html/body/div[1]/div[2]/div[1]/span[1]")).click();

        // Перейти по ссылке Создать аккаунт
        $(byCssSelector("body > div.popup.auth-popup.popup_visible > div > div > div.popup__content-wrapper > form.form.login-form > div.popup__header.auth-social > div.auth-social__description > span.link_blue.login-form__register.link"))
                .click();

        // Заполнить форму
        $(byXpath("/html/body/div[2]/div/div/div[2]/form[2]/div[2]/div[1]/div/input"))
                .setValue(email);
        $(byXpath("/html/body/div[2]/div/div/div[2]/form[2]/div[2]/div[2]/div/input"))
                .setValue(password);
        $(byXpath("/html/body/div[2]/div/div/div[2]/form[2]/div[2]/div[3]/div/input"))
                .setValue(password);
        $(byXpath("/html/body/div[2]/div/div/div[2]/form[2]/div[2]/div[4]/div/input"))
                .setValue(name);

        // Нажать Зарегистрироваться
        $(byXpath("/html/body/div[2]/div/div/div[2]/form[2]/div[2]/div[7]/button")).click();
    }

    @Test
    void findShirt() {
        // Ввести в поле поиска "рубашка"
        $(byXpath("//*[@id=\"menu-wrapper\"]/div/div[1]/input")).setValue("рубашка");

        // Нажать на иконку поиска
        $(byXpath("//*[@id=\"menu-wrapper\"]/div/div[1]/div[2]/div")).click();

        // Найти на странице текст товары по запросу «рубашка»
        $("html").shouldHave(text("Товары по запросу «рубашка»"));
    }

    @Test
    void regionChoice() {
        // Нажать на поле региона
        $(byXpath("/html/body/div[1]/div[1]/div/div/div[1]/div/div[2]")).click();

        // Нажать на ссылку Определить автоматически
        $(byXpath("/html/body/div[7]/div[3]/div/div/div[3]/div/div[2]/a")).click();

        // Нажать на кнопку Запомнить выбор
        $(byXpath("/html/body/div[7]/div[3]/div/div/div[4]/div/button[2]")).click();
    }

    @Test
    void onlineSupport() {
        // Нажать кнопку Хорошо
        $(byXpath("/html/body/div[8]/div[1]/div[1]/div/button")).click();

        // Нажать на кнопку Онлайн-консультант
        $(byXpath("//*[@id=\"hde-chat-widget\"]")).click();

        // Нажать на иконку Закрыть
        $(byXpath("//*[@id=\"hde-chat-container\"]/div[1]")).click();
    }
}