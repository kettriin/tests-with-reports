import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.attachment;
import static io.qameta.allure.Allure.step;


public class LambdaStepTest {
    @BeforeEach
    void preconditions() {
        open("https://www.vprok.ru/");
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 3000;

        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterEach
    void attachTheSource() {
        attachment("Source", webdriver().driver().source());
    }

    @CsvSource({
            "хлеб , Хлеб",
            "молоко 3 процента , Молоко ",
            "ВОДА , Вода"
    })
    @Feature("Поиск и рекомендации")
    @Story("Поиск в каталоге")
    @Owner("e.shchetinina")
    @Severity(SeverityLevel.BLOCKER)
    @ParameterizedTest(name = "В поисковом запросе {0} отображается карточка товара {1}")
    @Tag("WEB")
    void searchQueryContainsProductCard(String searchQuery, String serchedProductName) {

        step("Ищем по поисковому запросу через строку поиска", () -> {
            $(".UiSharedInputSearch_input__G0Un1").setValue(searchQuery).pressEnter();
        });

        step("Видим страницу с результататми поиска", () -> {
            $(".SearchResultsInformer_title__FVcvT").shouldBe(visible);
        });

        step("Видим товар, соответствующий поисковому запросу", () -> {
            $(".UiProductTileMain_root__Zk2eh.UiProductTileMain_listing__t356q")
                    .shouldHave(text(serchedProductName));
        });
    }
}

