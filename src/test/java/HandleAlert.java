import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.nio.file.Paths;

public class HandleAlert {

    Playwright playwright;
    Browser browser;
    BrowserContext context;
    Page page;

    @BeforeMethod
    public void setUp() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        context = browser.newContext();
        context.tracing().start(new Tracing.StartOptions().setScreenshots(true).setSnapshots(true).setSources(true));
        page = context.newPage();
        page.navigate("https://the-internet.herokuapp.com/javascript_alerts");
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
    public void handleAlert() {

        page.onDialog(dialog -> {
            String msg = dialog.message();
            System.out.println("alertmessage "+msg);
            Assert.assertTrue(msg.contains("I am a JS prompt"));
            dialog.accept("accepting the alert");
        });

        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Click for JS Prompt")).click();
    }
}