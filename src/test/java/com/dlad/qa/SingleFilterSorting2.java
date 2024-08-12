package com.dlad.qa;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SingleFilterSorting2 {

	WebDriver driver;
	
	@Test(priority = 11, dataProvider = "dropdownStatusFilterOptions")
	public void verifyNameFilterDataInTableBody(String statusDropdownOptions) throws InterruptedException {
		
		driver.findElement(By.xpath("//button[@id='status']")).click();
		driver.findElement(By.xpath("//button[@id='status']")).sendKeys(statusDropdownOptions);
		driver.findElement(By.xpath("//div[@class='relative overflow-hidden']/div/div/div/div/div[1]")).clear();
		driver.findElement(By.xpath("//button[contains(text(),'Filter')]")).click();
		Thread.sleep(1000);
		
        WebElement table = driver.findElement(By.xpath("//table[@id='tbl']"));
        List<WebElement> rows = table.findElements(By.xpath("//tbody/tr[@class='border-b transition-colors hover:bg-muted/50 data-[state=selected]:bg-muted']"));
        List<String> columnData = new ArrayList<>();
        for (WebElement row : rows) {
            WebElement cell = row.findElement(By.xpath(".//td[3]"));
            columnData.add(cell.getText());
        }
        
        for (String data : columnData) {
            System.out.println(data);
        }
        
        String filteredStatusName = driver.findElement(By.xpath("//tr[@class = 'border-b transition-colors hover:bg-muted/50 data-[state=selected]:bg-muted'][1]/td[3]")).getText();
        Assert.assertEquals(filteredStatusName, "0-New");
	}


}
