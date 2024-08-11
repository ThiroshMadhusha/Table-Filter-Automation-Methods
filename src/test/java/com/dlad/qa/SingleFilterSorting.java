package com.dlad.qa;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SingleFilterSorting {

	WebDriver driver;
	
	@Test(priority = 11, dataProvider = "dropdownStatusFilterOptions")
	public void verifyNameFilterDataInTableBody(String dropdownOption, String expectedTableStatus) throws InterruptedException {
	    // Click on the status button to open the dropdown
	    driver.findElement(By.xpath("//button[@id='status']")).click();
	    
	    // Select the dropdown option
	    driver.findElement(By.xpath("//button[@id='status']")).sendKeys(dropdownOption);
	    driver.findElement(By.xpath("//div[@class='relative overflow-hidden']/div/div/div/div/div[1]")).clear();
	    
	    // Apply the filter
	    driver.findElement(By.xpath("//button[contains(text(),'Filter')]")).click();
	    
	    // Wait for the filter to be applied
	    Thread.sleep(1000);
	
	    // Get the table body rows
	    WebElement table = driver.findElement(By.xpath("//table[@id='tbl']"));
	    List<WebElement> rows = table.findElements(By.xpath("//tbody/tr[@class='border-b transition-colors hover:bg-muted/50 data-[state=selected]:bg-muted']"));
	    
	    // Extract the status name from the first row
	    String filteredStatusName = driver.findElement(By.xpath("//tr[@class='border-b transition-colors hover:bg-muted/50 data-[state=selected]:bg-muted'][1]/td[3]")).getText();
	    
	    // Print all the status names in the table (for debugging)
	    List<String> columnData = new ArrayList<>();
	    for (WebElement row : rows) {
	        WebElement cell = row.findElement(By.xpath(".//td[3]"));
	        columnData.add(cell.getText());
	    }
	    
	    for (String data : columnData) {
	        System.out.println(data);
	    }
	    
	    // Assert that the filtered status name matches the expected table status
	    Assert.assertEquals(filteredStatusName, expectedTableStatus, "Filtered status name does not match the expected table status.");
	}

	@DataProvider(name = "dropdownStatusFilterOptions")
	public Object[][] dropdownStatusFilterOptions() {
	    String[] dropdownOption = {"New", "Contact Made", "Sampling", "Pricing", "Account Application Sent", "Account Application Received", "Converted", "Dead"};
	    String[] tableStatus = {"0-New", "1-Contact Made", "2-Sampling", "3-Pricing", "4-Account Application Sent", "5-Account Application Received", "6-Converted", "7-Dead"};
	    
	    Object[][] data = new Object[dropdownOption.length][2];
	    
	    for (int i = 0; i < dropdownOption.length; i++) {
	        data[i][0] = dropdownOption[i];
	        data[i][1] = tableStatus[i];
	    }
	    
	    return data;
	}


}
