package PaylocityAutomation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ReportsPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By timeCardReportLink = By.id("timeCardReportLink");
    private By dateRangeField = By.id("dateRange");
    private By inactiveEmployeeOption = By.id("inactiveEmployeeOption");
    private By pageBreakOption = By.id("pageBreakOption");
    private By includeAuditTrailOption = By.id("includeAuditTrailOption");
    private By exportButton = By.id("exportButton");
    private By fileNameField = By.id("fileName");
    private By pdfFormatOption = By.id("pdfFormat");
    private By runButton = By.id("runButton");

    public ReportsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
    }

    public void generateTimeCardReport(int year) throws InterruptedException {
        driver.get("https://paylocity.com/reports");
        wait.until(ExpectedConditions.presenceOfElementLocated(timeCardReportLink)).click();

        WebElement dateRange = wait.until(ExpectedConditions.presenceOfElementLocated(dateRangeField));
        dateRange.clear();
        dateRange.sendKeys(year + "-01-01 to " + year + "-12-31");

        driver.findElement(inactiveEmployeeOption).click();
        driver.findElement(pageBreakOption).click();
        driver.findElement(includeAuditTrailOption).click();

        driver.findElement(exportButton).click();
        WebElement fileName = wait.until(ExpectedConditions.presenceOfElementLocated(fileNameField));
        fileName.sendKeys("TimeCardReport_" + year);
        driver.findElement(pdfFormatOption).click();
        driver.findElement(runButton).click();

        Thread.sleep(5000);
    }
}
