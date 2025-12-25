package sample;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Test1 {

	public static void main(String[] args) {

		WebDriver driver = new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		driver.get("https://shino.de/parkcalc/");

		// Select parking lot
		Select select = new Select(driver.findElement(By.name("ParkingLot")));
		select.selectByVisibleText("Valet Parking");

		String parentWindow = driver.getWindowHandle();

		// Click date picker
		driver.findElement(By.xpath("(//img[@alt='Pick a date'])[1]")).click();

		// 🔑 WAIT until popup window opens
		wait.until(ExpectedConditions.numberOfWindowsToBe(2));

		// Switch to calendar window
		for (String window : driver.getWindowHandles()) {
			if (!window.equals(parentWindow)) {
				driver.switchTo().window(window);
				break;
			}
		}

		// 🔑 WAIT for date link to be clickable
		WebElement date =
				wait.until(ExpectedConditions.elementToBeClickable(
						By.xpath("//a[normalize-space()='24']")
						));

		date.click();

		// Switch back to parent
		driver.switchTo().window(parentWindow);

		// Click date picker
		driver.findElement(By.xpath("(//img[@alt='Pick a date'])[2]")).click();


		// 🔑 WAIT until popup window opens
		wait.until(ExpectedConditions.numberOfWindowsToBe(2));

		// Switch to calendar window
		for (String window : driver.getWindowHandles()) {
			if (!window.equals(parentWindow)) {
				driver.switchTo().window(window);
				break;
			}
		}

		// 🔑 WAIT for date link to be clickable
		WebElement date1 =
				wait.until(ExpectedConditions.elementToBeClickable(
						By.xpath("//a[normalize-space()='25']")
						));

		date1.click();

		// Switch back to parent
		driver.switchTo().window(parentWindow);

		driver.findElement(By.name("Submit")).click();

		WebElement estimateAmount = driver.findElement(By.xpath("(//span[@class='SubHead'])[position()=1]/b"));

		String text = estimateAmount.getText();

		System.out.println(text);
		
		driver.close();
	}
}
