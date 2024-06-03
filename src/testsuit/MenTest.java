package testsuit;

import org.junit.After;
import org.junit.Before;
import utilities.Utility;

public class MenTest extends Utility {
    String baseUrl = "https://magento.softwaretestingboard.com/";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    @After
    public void tearDown() {
        closeBrowser();
    }
}
