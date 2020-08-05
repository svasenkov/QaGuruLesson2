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

    @BeforeEach
    void siteOpen() {
        // Открыть сайт Lamoda
        open(URL);
    }

    @Test
    void findShoes() {
        $(byXpath(".//a[@data-genders='men']")).click();

        // Перейти в раздел Обувь
        $(byXpath(".//span[contains(text(),'Обувь')]")).click();

        // Найти на странице текст Мужская обувь
        $("html").shouldHave(text("Мужская обувь"));

        // Перейти на страницу второго элемента
        $$(byXpath(".//div[@class='products-list-item']"))
                .get(1).click();
    }

    @Test
    void registration() {
        // Перейти по ссылке Войти
        $(byXpath(".//span[contains(text(),'Войти')]")).click();

        // Перейти по ссылке Создать аккаунт
        $(byXpath(".//span[contains(text(),'Создать аккаунт')]")).click();

        // Заполнить форму
        $$(byXpath(".//input[@name='email']")).find(visible).setValue(email);
        $$(byXpath(".//input[@name='password']")).find(visible).setValue(password);
        $$(byXpath(".//input[@name='password2']")).find(visible).setValue(password);
        $$(byXpath(".//input[@name='first_name']")).find(visible).setValue(name);

        // Нажать Зарегистрироваться
        $(byXpath(".//button[contains(text(),'Зарегистрироваться')]")).click();
    }

    @Test
    void findShirt() {
        // Ввести в поле поиска "рубашка"
        $(byXpath(".//input[@type='text']")).setValue("рубашка");

        // Нажать на иконку поиска
        $(byXpath(".//div[@class='search__button-logo']")).click();

        // Найти на странице текст товары по запросу «рубашка»
        $("html").shouldHave(text("Товары по запросу «рубашка»"));
    }

    @Test
    void regionChoice() {
        // Нажать на поле региона
        $(byXpath(".//div[@class='v-popover']")).click();

        // Нажать на ссылку Определить автоматически
        $(byXpath(".//a[contains(text(),'Определить автоматически')]")).click();

        // Нажать на кнопку Запомнить выбор
        $(byXpath(".//button[contains(text(),'Запомнить выбор')]")).click();
    }

    @Test
    void onlineSupport() {
        // Нажать кнопку Хорошо
        $(byXpath(".//button[contains(text(),'Хорошо')]")).click();

        // Нажать на кнопку Онлайн-консультант
        $(byXpath(".//div[@id='hde-chat-widget']")).click();

        // Нажать на иконку Закрыть
        $(byXpath(".//div[contains(text(),'✕')]")).click();
    }
}