package pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainPage {
    private SelenideElement
            inputTypeSearch = $("[data-test-id='header__search-input--desktop']"),
            searchResultList = $("[data-test-id='header__search-result-list']"),
            cookieAcceptPopup = $("[data-test-id='cookieAcceptPopup']");

    private static ElementsCollection resultElementsHeaders = $$(("[data-test-id='lower-menu--desktop']"));

    @Step("Открываем главную страницу")
    public MainPage openMainPage() {
        open("");
        return this;
    }

    @Step("Получаем и проверяем элементы в хедере")
    public MainPage checkHeaderMainPage() {
        resultElementsHeaders.shouldHave(
                CollectionCondition.texts("Каталог\n" + "Подписка за 0 ₽\n" +
                        "Новинки\n" + "Популярное\n" + "Аудиокниги\n" + "Что почитать?\n" + "Самиздат\n" + "Промокод\n" + "Ещё\n"));
        return this;
    }

    @Step("Отображение подсказок в поиске")
    public MainPage checkSearchResultListIsVisible(String text) {
        inputTypeSearch.click();
        inputTypeSearch.setValue(text);
        searchResultList.shouldHave(Condition.visible);
        return this;
    }

    @Step("В поле подсказок в поиске отображаются кнопки Ранее вы искали и очистить историю")
    public MainPage checkUrlOfPageWithSearchResult(String text) {
        inputTypeSearch.click();
        inputTypeSearch.setValue(text).submit();
        webdriver().shouldHave(url("https://www.litres.ru/search/?q=" + text));
        return this;
    }

    public MainPage checkInputTypeSearchPlaceholder() {
        inputTypeSearch.shouldHave(Condition.visible);
        assertThat(inputTypeSearch.shouldHave(Condition.visible).getAttribute("placeholder"))
                .isEqualTo("Книга, серия, автор, жанр, издательство");
        return this;
    }

    public MainPage checkСookieAcceptPopup() {
        assertThat(cookieAcceptPopup.shouldHave(Condition.visible).getText()).isEqualTo("Продолжая использовать этот сайт, вы даете согласие ООО \"ЛитРес\", город Москва, на обработку файлов cookie и соответствующих пользовательских данных... далее\n" +
                "ПРИНЯТЬ");
        return this;
    }

}
