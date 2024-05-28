package steps;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;

public class AnnotationSteps {

    @Step("Open the main page")
    public void openMainPage(String url) {
        open(url);
    }

    @Step("Search a repository {repo}")
    public void searchForRepository(String repo) {
        $(".search-input").click();
        $("#query-builder-test").sendKeys(repo);
        $("#query-builder-test").submit();
    }

    @Step("Click on the repository link {repo}")
    public void clickOnRepositoryLink(String repo) {
        $(linkText(repo)).click();
    }

    @Step("Open the Issues tab")
    public void openIssuesTab() {
        $("#issues-tab").click();
    }

    @Step("Check the issue title {issue} with the issue number {issue}")
    public void shouldSeeIssueTitleWithIssueNumber(int issue, String title) {
        $("#issue_" + issue).should(Condition.exist)
                .shouldHave(text(title));
    }

    @Step("Check the issue title {issue} on the issue page")
    public void shouldSeeIssueTitleOnIssuePage(String title) {
        $(linkText(title)).click();
        $("bdi.js-issue-title").shouldHave(text(title));
    }

    @Attachment(value = "Screenshot", type = "image/png", fileExtension = "png")
    public byte[] takeScreenshot() {
        return ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }
}
