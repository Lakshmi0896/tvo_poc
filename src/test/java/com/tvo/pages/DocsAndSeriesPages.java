package com.tvo.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.tvo.driver.Driver;
import com.tvo.utilities.Log;

public class DocsAndSeriesPages extends Driver{
	
	@FindBy(xpath = "(//div[text()='Docs & Series'])[1]/../..")
	WebElement docsSeries_Menu;
	
	@FindBy(xpath = "//p[text()='All Docs']")
	WebElement allDocs_link;
	
	@FindBy(xpath = "//span[text()='Series']")
	WebElement series_link;
	
	@FindBy(xpath = "//span[text()='Docs']")
	WebElement docs_link;
	
	@FindBy(xpath = "//span[text()='A-Z']")
	WebElement a_z_link;
	
	@FindBy(xpath = "//span[text()='TVO Originals']")
	WebElement tvoOriginals_link;
	
	@FindBy(xpath = "//span[text()='Canadian Docs']")
	WebElement canadian_docs_link;
	
	@FindBy(xpath = "//span[text()='Current Affairs']")
	WebElement currentAffairs_link;
	
	public DocsAndSeriesPages(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void navigateTo_HomePage(){
		Log.info("Method to navigate to Application URL");
		driver.get(prop.getProperty("TVO_URL"));
	}
	
	public void test_AllDocs() {
		Log.info("Method to switch to ");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		docsSeries_Menu.click();
		series_link.click();
		docs_link.click();
		a_z_link.click();
		tvoOriginals_link.click();
		canadian_docs_link.click();
		currentAffairs_link.click();
	}
}
