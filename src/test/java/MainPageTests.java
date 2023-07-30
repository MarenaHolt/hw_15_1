import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.MainPage;

public class MainPageTests extends TestBase {

    MainPage mainPage = new MainPage();

    @Test
    @Tag("all")
    @DisplayName("Проверка отображения хедера c названиями разделов на главной странице")
    void checkHeader() {
        mainPage.openMainPage().checkHeaderMainPage();
    }

    @Test
    @Tag("all")
    @DisplayName("Проверка отображения поля поиска с плейсхолдером")
    void checkSearchInput() {
        mainPage.openMainPage().checkInputTypeSearchPlaceholder();
    }

    @Test
    @Tag("all")
    @DisplayName("Проверка отображения подсказок при вводе текста в строку поиска")
    void checkResultList() {
        mainPage.openMainPage().checkSearchResultListIsVisible("Три мушкетера");
    }

    @Test
    @Tag("all")
    @DisplayName("Проверка Перехода на страницу поиска после ввода данных в строку поиска")
    void checkResultListWithPreviousSearchesAndModuleClear() {
        mainPage.openMainPage().checkUrlOfPageWithSearchResult("fff");
    }

    @Test
    @Tag("all")
    @DisplayName("Проверка отображения cookieAcceptPopup")
    void checkSearchOnSite() {
        mainPage.openMainPage()
                .checkСookieAcceptPopup();
    }

}
