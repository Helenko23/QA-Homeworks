package com.telerikacademy.testframework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class CustomWebDriverManager {

    public enum CustomWebDriverManagerEnum {

        INSTANCE;
        private WebDriver driver = setupBrowser();

        public void quitDriver() {
            if (driver != null) {
                driver.quit();
                driver = null;
            }
        }

        public WebDriver getDriver() {
            if (driver == null) {
                setupBrowser();
            }
            return driver;
        }

        private WebDriver setupBrowser() {
            WebDriver firefoxDriver = new FirefoxDriver();
            firefoxDriver.manage().window().maximize();
            driver = firefoxDriver;
            return firefoxDriver;
        }
    }
}
