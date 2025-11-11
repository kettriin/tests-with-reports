import io.qameta.allure.Step;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class WebSteps {

    @Step("Ищем по поисковому запросу через строку поиска")
    public void searchWithQuery(String searchQuery) {
        $(".UiSharedInputSearch_input__G0Un1").setValue(searchQuery).pressEnter();
    }

    @Step("Видим страницу с результататми поиска")
    public void searchResultsAreVisible() {
        $(".SearchResultsInformer_title__FVcvT").shouldBe(visible);
    }

    @Step("Видим товар, соответствующий поисковому запросу")
    public void searchedProductIsVisible(String serchedProductName) {
        $(".UiProductTileMain_root__Zk2eh.UiProductTileMain_listing__t356q")
                .shouldHave(text(serchedProductName));
    }
}
