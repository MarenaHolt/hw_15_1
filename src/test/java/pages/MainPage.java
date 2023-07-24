package pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainPage {
    private SelenideElement
            searchBar = $(".twotabsearchtextbox"),
            inputTypeSearch = $("[type='search']"),
            resultSearchHeader = $(".uyjr3HkY"),
            promoCode = $("[test-id='nav_promo']"),
            promoInput = $("[test-id='promo_input']"),
            promoActivate = $("[test-id='promo_activate']"),
            imageLogo = $(".Vjha6F_C");

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
                                "Новинки\n" + "Популярное\n" + "Аудиокниги\n" + "Что почитать?\n" + "Самиздат\n" + "Промокод\n" +"Ещё\n"));
        return this;
    }

    @Step
    public MainPage checkButtonPromoCode() {
        promoCode.shouldHave(Condition.visible).click();
        return this;
    }

    public MainPage checkPromoInputEnabled(String promoCode) {
        promoInput.shouldHave(Condition.visible).setValue(promoCode);
        promoActivate.shouldHave(Condition.enabled);
        return this;

    }

    public MainPage checkPromoInputDisabled() {
        assertThat(promoInput.shouldHave(Condition.visible).getAttribute("placeholder")).isEqualTo("Промокод");
        promoActivate.shouldHave(Condition.disabled);
        return this;
    }

    public MainPage checkSearchButton() {
        searchBar.click();
        return this;
    }

    public MainPage checkInputTypeSearchPlaceholder() {
        assertThat(inputTypeSearch.shouldHave(Condition.visible).getAttribute("placeholder")).isEqualTo("Название фильма, сериала или имя актёра, режиссёра");
        return this;
    }

    public MainPage checkImgLogo() {
        assertTrue(imageLogo.isImage());
        assertThat(imageLogo.getAttribute("src")).isEqualTo("https://static.okko.tv/notifications/tv/1682682482088/1682682502609_512x204.png");
        return this;
    }

}
