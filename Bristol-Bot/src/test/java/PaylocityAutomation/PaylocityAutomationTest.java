package PaylocityAutomation;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
//import org.testng.annotations.Tese;
import org.testng.annotations.Test;

public class PaylocityAutomationTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private PayrollPage payrollPage;
    private ReportsPage reportsPage;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "path_to_chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);

        // Initialize page objects
        loginPage = new LoginPage(driver);
        payrollPage = new PayrollPage(driver);
        reportsPage = new ReportsPage(driver);
    }

    @Test(priority = 1)
    public void testLogin() {
        loginPage.login("your_username", "your_password");
        System.out.println("Login successful.");
    }

    @Test(priority = 2)
    public void testPayrollProcessing() throws InterruptedException {
        String[] employees = {"John Doe", "Jane Smith"}; // Example employee names
        for (String employee : employees) {
            payrollPage.processPayCheques(employee);
            System.out.println("Payroll processing completed for: " + employee);
        }
    }

    @Test(priority = 3)
    public void testGenerateReports() throws InterruptedException {
        int[] years = {2021, 2022}; // Example years
        for (int year : years) {
            reportsPage.generateTimeCardReport(year);
            System.out.println("Report generation completed for year: " + year);
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
