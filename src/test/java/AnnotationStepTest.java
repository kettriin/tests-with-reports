import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static com.codeborne.selenide.Selenide.open;
import utils.AttachmentUtils;


public class AnnotationStepTest {
    @BeforeEach
    void preconditions() {
        open("https://www.vprok.ru/");
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 3000;

        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterEach
    void attachTheSource() {
        AttachmentUtils attach = new AttachmentUtils();
        attach.takeScreenshot();;
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
        WebSteps steps = new WebSteps();

        steps.searchWithQuery(searchQuery);
        steps.searchResultsAreVisible();
        steps.searchedProductIsVisible(serchedProductName);
    }
}
