package PaylocityAutomation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class PayrollPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By employeeSearchBar = By.id("employeeSearch");
    private By payTab = By.id("payTab");
    private By checksSubTab = By.id("checksSubTab");
    private By showPrivateData = By.id("showPrivateData");
    private By payStubCheckbox = By.className("payStubCheckbox");
    private By downloadPayStubsButton = By.id("downloadPayStubs");
    private By nextPageButton = By.id("nextPage");

    public PayrollPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
    }

    public void processPayCheques(String employeeName) throws InterruptedException {
        driver.get("https://paylocity.com/hrPayroll");
        WebElement searchBar = wait.until(ExpectedConditions.presenceOfElementLocated(employeeSearchBar));
        searchBar.clear();
        searchBar.sendKeys(employeeName);
        searchBar.submit();

        wait.until(ExpectedConditions.presenceOfElementLocated(payTab)).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(checksSubTab)).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(showPrivateData)).click();

        boolean hasMoreRecords = true;
        while (hasMoreRecords) {
            List<WebElement> payStubCheckboxes = driver.findElements(payStubCheckbox);
            for (int i = 0; i < Math.min(6, payStubCheckboxes.size()); i++) {
                payStubCheckboxes.get(i).click();
            }

            wait.until(ExpectedConditions.presenceOfElementLocated(downloadPayStubsButton)).click();
            WebElement nextButton = driver.findElement(nextPageButton);
            if (nextButton.getAttribute("class").contains("disabled")) {
                hasMoreRecords = false;
            } else {
                nextButton.click();
            }
            Thread.sleep(3000);
        }
    }
}
