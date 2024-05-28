import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Attachment;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class TestBase {

    public static final String REPOSITORY = "qa-guru/qa_guru_14_10";
    public static final int ISSUE = 2;
    public static final String TITLE = "Issue for Autotest";

    @BeforeAll
    static void beforeAll() {
        Configuration.pageLoadStrategy = "eager";
        Configuration.browserSize="1920x1080";
        Configuration.baseUrl="https://github.com";
    }

    @AfterEach
    void afterEach() {
        Selenide.closeWebDriver();
    }

    @Attachment(value = "Screenshot", type = "image/png", fileExtension = "png")
    public byte[] takeScreenshot() {
        return ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }
}
