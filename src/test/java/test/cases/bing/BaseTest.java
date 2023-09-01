package test.cases.bing;

import com.telerikacademy.testframework.UserActions;
import org.junit.AfterClass;
import org.junit.BeforeClass;

public class BaseTest {

    UserActions actions = new UserActions();

    @BeforeClass
    public static void setUp() {
        UserActions.loadBrowser("bing.homePage");
    }

    @AfterClass
    public static void tearDown() {
        UserActions.quitDriver();
    }
}
