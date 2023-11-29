package Boohoo.BHM.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractComponent {

	private WebDriver driver;
	private WebDriverWait wait;

	public AbstractComponent(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	public void waitForElementtoAppear(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void waitForElementtoAppear(By locator) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public void waitForElementtoDisappear(WebElement element) {
        wait.until(ExpectedConditions.invisibilityOf(element));
	}

	public void waitForElementtoDisappear(By locator) {
	   wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}

}
