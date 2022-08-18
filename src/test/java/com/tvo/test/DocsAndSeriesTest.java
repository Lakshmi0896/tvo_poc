package com.tvo.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.logging.log4j.core.config.properties.PropertiesConfigurationBuilder;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.tvo.driver.Driver;
import com.tvo.pages.DocsAndSeriesPages;
import com.tvo.utilities.Log;
import com.tvo.utilities.ReadPropertiesFile;

public class DocsAndSeriesTest extends Driver{
	
	public static final String filename = null;
	public ReadPropertiesFile readfile = new ReadPropertiesFile();
	public Properties prop = readfile.readPropertiesFile(filename);
	public 	DocsAndSeriesPages docsAndSeriesPages;
	
	@BeforeTest
	public void logging() throws IOException {
		Log.info("Inside before class ");
		DateFormat dateFormat = new SimpleDateFormat("yy-MM-dd_HH_mm_ss");
		Date date= new Date();
		String reportDir = dateFormat.format(date);
		System.setProperty("log_dir",reportDir + "//logs//testlog.log");
		File file = new File(System.getProperty("user.dir") + "//properties//log4j.properties");
		Properties properties = new Properties();
		try (InputStream inputStream = new FileInputStream(file)) {
		properties.load(inputStream);
		}
		LoggerContext context = (LoggerContext) LogManager.getContext(false);
		Configuration config = new PropertiesConfigurationBuilder()
		.setConfigurationSource(ConfigurationSource.NULL_SOURCE).setRootProperties(properties)
		.setLoggerContext(context).build();
		context.setConfiguration(config);
		Configurator.initialize(config);
	}
	
	@BeforeClass(alwaysRun=true)
	public void pageInstantiation() throws Exception {
		docsAndSeriesPages = new DocsAndSeriesPages(driver);
	}
	
	@BeforeMethod(alwaysRun=true)
	public void init() {
		Log.info("Inside before method ");
		Driver.init(prop.getProperty("Browser"));
		docsAndSeriesPages.navigateTo_HomePage();
	}
	
	@Test(priority = 1)
	public void aboutThisItem_test() throws Exception {
		Log.info("Test Started ");
		docsAndSeriesPages = new DocsAndSeriesPages(driver);
		docsAndSeriesPages.test_AllDocs();
		Log.info("Test Ended ");
	}
	
	@AfterMethod
	public void tearDown(ITestResult result) {
		Log.info("Inside After Method");
		if (ITestResult.FAILURE == result.getStatus()) {
			try {
				TakesScreenshot ts = (TakesScreenshot) driver;
				File source = ts.getScreenshotAs(OutputType.FILE);
				FileHandler.copy(source, new File("./Screenshots/" + result.getName() + ".png"));
				System.out.println("Screenshot taken");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	@AfterClass
	public void quit() {
		Log.info("Inside After Class");
		driver.quit();
	}
}
