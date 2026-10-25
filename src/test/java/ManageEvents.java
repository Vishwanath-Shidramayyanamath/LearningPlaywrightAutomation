import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.AriaRole;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class ManageEvents {

    Page page;
    Browser browser;
    Playwright playwright;

    @BeforeMethod
    public void setUp() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false));
        page = browser.newPage();
        page.navigate("https://eventhub.rahulshettyacademy.com/");
        page.title();
        System.out.println(page.title());
        assertThat(page).hasTitle("EventHub — Discover & Book Events");
    }

    @Test
    public void CreateEvent() {
        page.getByLabel("Email").fill("vishwanathsmath99@gmail.com");
        page.getByLabel("Password").fill("EventHub@123");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Sign in")).click();
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Browse Events →")).click();
        assertThat(page.getByText("Upcoming Events")).isVisible();
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Add New Event")).click();
        page.getByText("+ New Event");
        page.getByLabel("Title").fill("QA Automation Learning");
        page.getByPlaceholder("Describe the event…").fill("Description about event");
        page.getByLabel("Category").selectOption("Sports");
        page.getByLabel("City").fill("Dharwad");
        page.getByLabel("Venue").fill("Malapur");
        page.getByLabel("Event Date & Time").fill("2026-12-18T07:25");
        page.getByLabel("Price ($)").fill("50");
        page.getByLabel("Total Seats").fill("1000");
//        page.getByLabel("Image URL (optional)").fill("https://www.google.com/imgres?q=Sample%20test%20image%20url&imgurl=https%3A%2F%2Fmedia.licdn.com%2Fdms%2Fimage%2Fv2%2FD4D12AQGgMCavCcCbEg%2Farticle-cover_image-shrink_600_2000%2Farticle-cover_image-shrink_600_2000%2F0%2F1711086616356%3Fe%3D2147483647%26v%3Dbeta%26t%3D1N65UooE_qsxL1jn9bwI0x1CFp7-czZxArntN8jCIps&imgrefurl=https%3A%2F%2Fwww.linkedin.com%2Fpulse%2F100-free-image-url-testing-guest-posting-sites-sv0xf&docid=qzm-HuFOV8WirM&tbnid=XD7FHW_C-7UMVM&vet=12ahUKEwj-0K_y8dWXAxXKRmwGHVpgIJUQnPAOegQIbBAA..i&w=600&h=398&hcb=2&ved=2ahUKEwj-0K_y8dWXAxXKRmwGHVpgIJUQnPAOegQIbBAA");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("+ Add Event")).click();
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Events")).first().click();
        assertThat(page.getByText("QA Automation Learning")).isVisible();
        assertThat(page.getByText("Malapur, Dharwad")).isVisible();
        assertThat(page.getByText("$50")).isVisible();
        page.waitForTimeout(5000);
    }
}