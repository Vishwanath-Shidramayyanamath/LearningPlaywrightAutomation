import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.security.PublicKey;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class HandleDialog {


    Playwright playwright ;
    Browser browser;
    BrowserContext context;
    BrowserContext context02;
    Page page;
    Page page1;
    Page page02;

    @BeforeMethod
    public  void setUp(){
        playwright = Playwright.create();
        browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
        context02 = browser.newContext();
        context = browser.newContext();
        page = context.newPage();
        page1 = browser.newPage();
        page02 = context02.newPage();
        page.navigate("https://rahulshettyacademy.com/loginpagePractise/");
        String title = page.title();
        System.out.println(title);
        assertThat(page).hasTitle("LoginPage Practise | Rahul Shetty Academy");
    }

    @AfterMethod
    public void tearDown(){
        context.close();
        page.close();
        context02.close();
        page02.close();
        playwright.close();
    }

    @Test
    public void dialogActions(){
        page.getByRole(AriaRole.RADIO, new Page.GetByRoleOptions().setName("User")).check();
        page.onDialog(dialog -> dialog.accept());

        page02.navigate("https://rahulshettyacademy.com/loginpagePractise/");
        page02.getByRole(AriaRole.RADIO, new Page.GetByRoleOptions().setName("User")).click();
        page02.onDialog(dialog -> dialog.dismiss());
    }
}