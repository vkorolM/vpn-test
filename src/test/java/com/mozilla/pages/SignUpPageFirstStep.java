package com.mozilla.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.testng.log4testng.Logger;

public class SignUpPageFirstStep extends BasePage {
		public SignUpPageFirstStep(AppiumDriver driver) {
				super(driver);
		}

		@AndroidFindBy(xpath = "//android.view.View.VirtualChild[@content-desc=\"Enter your email address to continue using Mozilla VPN \"]")
		public WebElement pageFlag;

		@AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.view.View[3]")
		WebElement emailField;

		@AndroidFindBy(xpath = "(//android.view.View.VirtualChild[@content-desc=\"Continue \"])[2]")
		WebElement continueButton;

		@Step("Verify sign up page is open")
		public boolean verifyPageIsOpen() {
				return waitForElementPresent(pageFlag, "Sign up page didn't open", 10) != null;
		}

//		@Step
//		public SignUpPageFirstStep fillEmailField() {
//				emailField.clear();
//				emailField.sendKeys("testEmail1@mozilla.com");
//				return this;
//		}

		@Step("Tap on Continue button")
		public SignUpPageFirstStep tapOnContinueButton() {
				continueButton.click();
				return this;
		}
}
