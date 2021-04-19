package pages;

import java.util.Hashtable;

import commonFunctions.BasePage;

public class LeadPage extends BasePage{
	
	public void createLead(Hashtable<String,String> data) throws InterruptedException {
		driver.get(config.getProperty("LeadPageurl"));
		Thread.sleep(2000);
		click("newBtn_XPATH");		
		typeatext("phone_XPATH", data.get("phone"));
		typeatext("mobile_XPATH", data.get("mobile"));
		
		Thread.sleep(1500);
		dropdown("salutation_XPATH", "select_salutation_XPATH", data.get("salutation"));
		typeatext("firstname_XPATH", data.get("firstname"));
		typeatext("lastname_XPATH", data.get("lastname"));
		typeatext("company_XPATH", data.get("company"));
		typeatext("title_XPATH", data.get("title"));
		typeatext("email_XPATH", data.get("email"));
		dropdown("lead_source_xpath", "select_lead_source_XPATH", data.get("lead_source"));
		
		typeatext("street_XPATH", data.get("street"));
		typeatext("city_XPATH", data.get("city"));
		typeatext("state_XPATH", data.get("state"));
		typeatext("postal_code_XPATH", data.get("postal_code"));
		typeatext("country_XPATH", data.get("country"));
		typeatext("description_XPATH", data.get("description"));
		
		click("save_XPATH");
		Thread.sleep(2000);
	}
	
	//convert lead
	public void convertLead() throws InterruptedException {
		mouseHoverAndClick("progressbar_converted_XPATH");
		Thread.sleep(2000);
		mouseHoverAndClick("selectConvertedSatus_XPATH");
		Thread.sleep(2000);
		mouseHoverAndClick("convertLead_convertBtn_XPATH");
	}

}
