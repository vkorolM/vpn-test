package com.mozilla.pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BasePage {

		protected AppiumDriver driver;
		protected WebDriverWait wait;

		public BasePage(AppiumDriver driver) {
				this.driver = driver;
		}

		public WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds) {
				wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
				wait.withMessage(error_message + "\n");
				return wait.until(
						ExpectedConditions.presenceOfElementLocated(by));
		}

		public WebElement waitForElementPresent(By by, String error_message) {
				return waitForElementPresent(by, error_message, 5);
		}

		public WebElement waitForElementAndClick(By by, String error_message, long timeoutInSeconds) {
				WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
				element.click();
				return element;
		}

		public WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutInSeconds) {
				WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
				element.sendKeys(value);
				return element;
		}

		public boolean waitForElementNotPresent(By by, String error_message, long timeoutInSeconds) {
				wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
				wait.withMessage(error_message + "\n");
				return wait.until(
						ExpectedConditions.invisibilityOfElementLocated(by));
		}

		public WebElement waitForElementAndClear(By by, String error_message, long timeoutInSeconds) {
				WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
				element.clear();
				return element;
		}

		public void swipeUp(int timeOfSwipeInMillis) {
				Dimension size = driver.manage().window().getSize();
				int x = size.width / 2;
				int start_y = size.height / 2;
				int end_y = (int) (size.height * 0.2);

				PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
				Sequence scroll = new Sequence(finger, 0);

				scroll.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), x, start_y));
				scroll.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
				scroll.addAction(finger.createPointerMove(Duration.ofMillis(timeOfSwipeInMillis), PointerInput.Origin.viewport(), x, end_y));
				scroll.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

				driver.perform(List.of(scroll));
		}

		public void swipeUpQuick() {
				swipeUp(200);
		}

		public void swipeUpToFindElement(By by, String error_message, int max_swipes) {
				int already_swiped = 0;
				while (driver.findElements(by).size() == 0) {
						if (already_swiped > max_swipes) {
								waitForElementPresent(by, "Can't find element by swiping up. \n" + error_message, 0);
								return;
						}
						swipeUpQuick();
						already_swiped++;
				}
		}

		public int getAmountOfElements(By by) {
				List elements = driver.findElements(by);
				return elements.size();
		}

		public void assertElementNotPresent(By by, String error_message) {
				int amount_of_elements = getAmountOfElements(by);
				if (amount_of_elements > 0) {
						String default_message = "An element '" + by.toString() + "' supposed to be not present";
						throw new AssertionError(default_message + " " + error_message);
				}
		}

		public String waitForElementAndGetAttribute(By by, String attribute, String error_message, long timeoutInSeconds) {
				WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
				return element.getAttribute(attribute);
		}
}