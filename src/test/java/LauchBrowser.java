import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class LauchBrowser {

    @Test
    public void browserLaunch(){
        Playwright playwright = Playwright.create();
        Browser browser =playwright.webkit().launch(new BrowserType.LaunchOptions().setChannel("webkit").setHeadless(false));
        Page page = browser.newPage();
        page.navigate("https://eventhub.rahulshettyacademy.com/login");
        System.out.println(page.title());
        assertThat(page).hasTitle("EventHub — Discover & Book Events");
    }
}
