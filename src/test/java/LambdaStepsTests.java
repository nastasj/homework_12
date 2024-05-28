import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.logevents.SelenideLogger.step;
import static org.openqa.selenium.By.linkText;

public class LambdaStepsTests extends TestBase{

    @Test
    @Owner("nastasj")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Check the Issue title in the repository using lambda steps")
    public void issueLambdaStepsTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        step("Open the main page", () -> {
            open("");
        });
        step("Search a repository " + REPOSITORY, () -> {
            $(".search-input").click();
            $("#query-builder-test").sendKeys(REPOSITORY);
            $("#query-builder-test").submit();
        });
        step("Click on the repository link " + REPOSITORY, () -> {
            $(linkText(REPOSITORY)).click();
        });
        step("Open the Issues tab", () -> {
            $("#issues-tab").click();
        });
        step("Check the issue title " + TITLE + " on the issue number " + ISSUE, () -> {
            $("#issue_" + ISSUE).should(Condition.exist)
                    .shouldHave(text(TITLE));
        });
        step("Check the issue title " + TITLE + " on the issue page", () -> {
            $(linkText(TITLE)).click();
            $("bdi.js-issue-title").shouldHave(text(TITLE));
        });
        takeScreenshot();
    }

}
