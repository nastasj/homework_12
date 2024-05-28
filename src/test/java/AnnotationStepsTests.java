import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import steps.AnnotationSteps;

public class AnnotationStepsTests extends TestBase {

    @Test
    @Owner("nastasj")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Check the Issue title in repository using annotation steps")
    public void issueAnnotationStepsTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        AnnotationSteps steps = new AnnotationSteps();
        steps.openMainPage();
        steps.searchForRepository(REPOSITORY);
        steps.clickOnRepositoryLink(REPOSITORY);
        steps.openIssuesTab();
        steps.shouldSeeIssueTitleWithIssueNumber(ISSUE, TITLE);
        steps.shouldSeeIssueTitleOnIssuePage(TITLE);
        steps.takeScreenshot();
    }
}
