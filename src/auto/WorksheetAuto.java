package auto;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;

public class WorksheetAuto {
	public static void main(String[] args) {
        //System.setProperty("webdriver.firefox.driver","");
		FirefoxOptions headlessOption = new FirefoxOptions();
		headlessOption.setHeadless(false);
		
		WebDriver firefoxDriver = new FirefoxDriver(headlessOption);
		
		try {
			firefoxDriver.get("https://docs.google.com/spreadsheets/d/xx");
			Thread.sleep(1000*60*3);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		firefoxDriver.manage().window().maximize();
		WebElement columnHeader = firefoxDriver.findElement(By.xpath("/html/body/div[4]/div/div[2]/div/div[5]/div[1]/div/div[2]"));
		
		if(columnHeader != null) {
			Point columnHeaderPoint = columnHeader.getLocation();
			 
			int columnHeaderPositionX = columnHeaderPoint.getX();
			int columnHeaderPositionY = columnHeaderPoint.getY();
			
			Actions act= new Actions(firefoxDriver);
			
			// click on column A
			act.moveByOffset(columnHeaderPositionX + 5, columnHeaderPositionY + 5).click().build().perform();
			
			// Press arrow right key 2 times to reach column C
			for(int i = 0; i < 2; i++) {
				try{
					Thread.sleep(1000*2);
				}catch(Exception ex) {
					ex.printStackTrace();
				}
				act.keyDown(Keys.SHIFT).build().perform();
				act.sendKeys(Keys.ARROW_RIGHT).build().perform();
			}
			act.keyUp(Keys.SHIFT).build().perform();
		}else {
			firefoxDriver.close();
		}
	}
}
