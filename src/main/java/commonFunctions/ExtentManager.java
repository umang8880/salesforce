package commonFunctions;

import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


public class ExtentManager extends BasePage{

	
	private static ExtentReports extent;
	static Date d = new Date();
	//static String fileName = "Extent_" + d.toString().replace(":", "_").replace(" ", "_") + ".html";
	static String fileName = "ExtentReport.html";
	public static ExtentReports createInstance() {
    	
    	
    	if (extent == null) {
	        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(extent_reports_path+fileName);
	       
	        htmlReporter.config().setEncoding("utf-8");
	        htmlReporter.config().setDocumentTitle("Salesfoce Reports");
	        htmlReporter.config().setReportName("Salesforce Convert Lead");
	        htmlReporter.config().setTheme(Theme.STANDARD);
	        
	        extent = new ExtentReports();
	        extent.attachReporter(htmlReporter);
	        extent.setSystemInfo("Automation Tester", "Umang Parekhg");
	        extent.setSystemInfo("Organization", "XXX");
	        extent.setSystemInfo("Build no", "202104151256");	      
    	}
    	return extent;
    }
}
