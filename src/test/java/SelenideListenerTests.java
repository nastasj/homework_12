import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.By.linkText;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;

public class SelenideListenerTests extends TestBase {

    @Test
    @Owner("nastasj")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Check the Issue title in repository using Selenide (with Listener)")
    public void issueSelenideListenerTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        open(URL);
        $(".search-input").click();
        $("#query-builder-test").sendKeys(REPOSITORY);
        $("#query-builder-test").submit();
        $(linkText(REPOSITORY)).click();
        $("#issues-tab").click();
        $("#issue_" + ISSUE).should(Condition.exist)
                .shouldHave(text(TITLE));
        $(linkText(TITLE)).click();
        $("bdi.js-issue-title").shouldHave(text(TITLE));
        takeScreenshot();
    }
}
