package com.mozilla.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePage {
		public HomePage(AppiumDriver driver) {
				super(driver);
		}

		@AndroidFindBy(xpath = "(//android.view.View.VirtualChild[@content-desc=\"Sign up \"])[2]")
		public WebElement signUpButton;

		@Step("Verify home page is open")
		public Boolean verifySignUpButtonPresent() {
				return waitForElementPresent(signUpButton, "Element not found", 10) != null;
		}

}
