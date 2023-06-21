package com.mozilla.core;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.qameta.allure.Step;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.net.URL;

public class BaseTest {

		protected AppiumDriver driver;
		private static String appiumUrl = "http://127.0.0.1:4723";

		@BeforeClass
		@Step("Setup driver")
		protected void setUp() throws Exception {
				DesiredCapabilities capabilities = new DesiredCapabilities();
				capabilities.setCapability("platformName", "Android");
				capabilities.setCapability("deviceName", "appium");
				capabilities.setCapability("platformVersion", "13");
				capabilities.setCapability("automationName", "uiautomator2");
				capabilities.setCapability("printPageSourceOnFindFailure", "true");
				capabilities.setCapability("app", "/Users/vladyslavkorol/Developement/Mozilla/vpn-automation/mozillavpn-x86-release.apk");

				UiAutomator2Options uiAutomator2Options = new UiAutomator2Options()
						.setAutomationName("UiAutomator2")
						.setDeviceName("Pixel3")
						.setPlatformVersion("11")
						.setPlatformName("Android")
						.setPrintPageSourceOnFindFailure(true)
						.setApp("/Users/vladyslavkorol/Developement/Mozilla/vpn-automation/vpn/mozillavpn-x86-release.apk");

				driver = new AndroidDriver(new URL(appiumUrl), uiAutomator2Options);
		}

		@AfterClass
		protected void tearDown() throws Exception {
				driver.quit();
		}

		public AppiumDriver getDriver() {
				return driver;
		}

		public void setDriver(AppiumDriver driver) {
				this.driver = driver;
		}
}