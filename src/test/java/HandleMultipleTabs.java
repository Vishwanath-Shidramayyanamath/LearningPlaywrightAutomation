import com.microsoft.playwright.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

public class HandleMultipleTabs {

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
        page.navigate("https://freelance-learn-automation.vercel.app/login");

        context.tracing().start(new Tracing.StartOptions()
                        .setScreenshots(true).setSnapshots(true)
                .setSources(true));
        page = context.newPage();

    }

    @AfterMethod
    public void tearDown() {
        context.tracing().stop(new Tracing.StopOptions().setPath(Paths.get("Trace.zip")));
        page.close();
        context.close();
        browser.close();
        playwright.close();
    }

    @Test
    public void handleTabs() {
        Page newPage = context.waitForPage(()->{
           page.locator("//a[@href='https://www.linkedin.com/in/mukesh-otwani-93631b99/']").last().click();
        });
        page.bringToFront();
        page.getByPlaceholder("Enter Email").fill("vishu.smath@gmail.com");
    }
}