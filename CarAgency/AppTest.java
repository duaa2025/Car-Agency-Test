package CarAgency.CarAgency;

import static org.testng.Assert.assertEquals;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AppTest

{

	String WebSite = "http://localhost:5000/";
	WebDriver driver = new ChromeDriver();

	String UserName = "admin";
	String Password = "1234";

	@BeforeTest
	public void MySetup() {
		driver.manage().window().maximize();
		driver.get(WebSite);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
	}

	@Test(priority = 1, enabled = true)
	public void Login() {
		WebElement Username = driver.findElement(By.id("username"));
		Username.sendKeys(UserName);
		WebElement PW = driver.findElement(By.id("password"));
		PW.sendKeys(Password);
		WebElement LoginButton = driver.findElement(By.id("btn-login"));
		LoginButton.click();

	}

	@Test(priority = 2, enabled = true)
	public void AddCar() {
		WebElement CarBrand = driver.findElement(By.id("brand-in"));
		CarBrand.sendKeys("Toyota");
		WebElement CarModel = driver.findElement(By.id("model-in"));
		CarModel.sendKeys("Corolla");
		WebElement AddCarButton = driver.findElement(By.id("btn-add"));
		AddCarButton.click();
		CarBrand.clear();
		CarBrand.sendKeys("Honda");
		CarModel.clear();
		CarModel.sendKeys("Accord");
		AddCarButton.click();
	}

	@Test(priority = 3, enabled = false)
	public void UpdateCar() throws InterruptedException {
		WebElement CarBrand = driver.findElement(By.xpath("//div[@data-id='1']//input[@data-field='brand']"));

		CarBrand.clear();
		Thread.sleep(2000);
		CarBrand.sendKeys("BMW");
		WebElement UpdateButton=driver.findElement(By.xpath("//div[@data-id='1']//button[@data-action='update']"));
		UpdateButton.click();
	}

	@Test(priority = 3, enabled = false)
	public void DeleteCar() throws InterruptedException {
		Thread.sleep(2000);
		WebElement DeleteButton=driver.findElement(By.xpath("//div[@data-id='1']//button[@data-action='delete']"));
		DeleteButton.click();
		Thread.sleep(2000);
		driver.switchTo().alert().accept();
	}
	@Test(priority = 4, enabled = true)
	public void CarStatus() {
		
		WebElement RentButton=driver.findElement(By.xpath("//div[@data-id='2']//button[@data-action='toggle']"));
		RentButton.click();
		
		String ExpectedStatusAfterClickRentButton="Rented";
		WebElement Status=driver.findElement(By.xpath("//div[@data-id='2']//span[@class='status-label rented']"));
		String AcualStatus=Status.getText();
		System.out.println(AcualStatus);
		assertEquals(AcualStatus, ExpectedStatusAfterClickRentButton);
	}
	@AfterTest
	public void Quit() {
	}
}
