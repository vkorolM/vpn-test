package com.mozilla.tests;

import com.mozilla.core.BaseTest;
import com.mozilla.pages.HomePage;
import com.mozilla.pages.SignUpPageFirstStep;
import org.testng.Assert;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class POCTests extends BaseTest {

		@Test(description = "Random test to show how allure report work")
		public void firstTest() {
				HomePage homePage = new HomePage(driver);
				SignUpPageFirstStep signUpPageFirstStep = new SignUpPageFirstStep(driver);
				if(homePage.verifySignUpButtonPresent()) {
						homePage.signUpButton.click();
				}
				assertTrue(!signUpPageFirstStep.verifyPageIsOpen(), "Page is showing when it shouldn't");
		}
}