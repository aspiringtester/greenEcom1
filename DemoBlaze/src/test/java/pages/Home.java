package pages;

import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import utility.BaseTest;

public class Home extends BaseTest {
	
	
	By search = By.xpath("//input[@type='search']");
	public static Logger log = (Logger) LogManager.getLogger(Home.class);
	

	public WebDriver driver;
	
	String[] itemsNeeded= {"Cucumber","Brocolli","Beetroot"};
	
	@BeforeTest
	
	public void firstStep() throws IOException, InterruptedException {
		driver = initializeDriver();
		driver.get(getWebpage());
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		log.info("webdriver initiated");
		
		
		
	}
	
	@AfterTest
	public void endOfTest() {
		driver.close();
		
	}
	
	
	
	
	@Test
	public void verifyData1() {
	// System.out.println(n1);
		String[] itemsNeeded = {"Cucumber",
				 "Brocolli",
				 "Beetroot"};
		
		int j=0;

		List<WebElement> products=driver.findElements(By.cssSelector("h4.product-name"));
		
		for(int i=0;i<products.size();i++) {
			
			//Brocolli - 1 Kg

			//Brocolli,    1 kg
			//format it to get actual vegetable name

			String[] name=products.get(i).getText().split("-");

			String formattedName=name[0].trim();	
			
		
			
        // converted the array into arraylist
			List<String> itemsNeededList = Arrays.asList(itemsNeeded);
			
		//  checking  whether name you extracted is present in arrayList or not-
			
			if(itemsNeededList.contains(formattedName)) {
				
				j++;
				
				//click on Add to cart

				driver.findElements(By.xpath("//div[@class='product-action']/button")).get(i).click();
				
				if(j==itemsNeeded.length)

				{

				break;

				}
				
			}// if statement ends here
			
		}// for loop ends here
		
		
	}


	
	
}// class ends here

