import com.microsoft.playwright.*;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.microsoft.playwright.options.AriaRole;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.nio.file.Paths;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class UIValidation {

    Playwright playwright;
    Browser browser;
    BrowserContext context;
    Page page;

    @BeforeMethod
    public void setUp() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        context = browser.newContext();
        page = context.newPage();
        page.navigate("https://rahulshettyacademy.com/loginpagePractise/");
        page.setDefaultTimeout(5000);
        PlaywrightAssertions.setDefaultAssertionTimeout(10000);
    }

    @AfterMethod
    public void tearDown() {
        page.close();
        context.close();
        browser.close();
        playwright.close();
    }

    @Test
    public void login() {
        page.getByLabel("UserName").fill("rahulshettyacademy");
        page.getByLabel("Password:").fill("Learning@830$3mK2");
        Locator radioButton = page.getByRole(AriaRole.RADIO, new Page.GetByRoleOptions().setName("Admin"));
        radioButton.check();
        assertThat(radioButton).isChecked();

        page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("screenshot.png")));

        page.locator("//select[@class='form-control']").selectOption("Consultant");
        Locator checkBox = page.getByRole(AriaRole.CHECKBOX, new Page.GetByRoleOptions().setName("I Agree to the "));
        checkBox.check();
        assertThat(checkBox).isChecked();
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Sign In")).click();
    }
}
