package commonFunctions;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;


import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;


public class Listeners extends BasePage implements ITestListener, ISuiteListener {

	
	public String messageBody;
	public void onTestStart(ITestResult result) {
		test = extent.createTest("TestCase: "+result.getMethod().getMethodName().toUpperCase());
	}

	public void onTestSuccess(ITestResult result) {		
		String methodName = result.getMethod().getMethodName();
		String logText = "<b>" + "TEST CASE:- " + methodName.toUpperCase() + " PASSED" + "</b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
		test.pass(m);
	}

	public void onTestFailure(ITestResult result) {
		try {
			//TestUtil.captureFullScreenUsingAshot(TestBase.screenshotpath, result.getMethod().getMethodName());
			captureScreenShots(screenshotpath, result.getMethod().getMethodName()); 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String methodName = result.getMethod().getMethodName();
		String excepionMessage = Arrays.toString(result.getThrowable().getStackTrace()); 
		test.fail(	"<details>"
						+"<summary>"
							+"<b>"+"<font color=" + "red>" + "Exception Occured:Click to see"+ "</font>"+"</b>"
						+"</summary>"
					 + excepionMessage.replaceAll(",", "<br>") + "</details>"+" \n"+"<br>"
					 +"<a href=\""+screenshotName+"\" target='_blank'>Click here to see full scereenshot in new tab</a>"
					 +"<br>"+"<br>"
					 +"<a href=\""+screenshotName+"\" target='_blank'><img src=\""+screenshotName+"\" height=250 width=250></a>");
		
		
		String logtext = "<b>" + "Test Case: "+methodName.toUpperCase()+": "+" FAILED"+"</b>";
		
		Markup m = MarkupHelper.createLabel(logtext, ExtentColor.RED);
		test.log(Status.FAIL, m);
	}

	public void onTestSkipped(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		String logtext = "<b>" + "Test Case: "+methodName.toUpperCase()+": "+" SKIPPED"+"</b>";
		Markup m = MarkupHelper.createLabel(logtext, ExtentColor.YELLOW);
		test.skip(m);

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

	}

	public void onFinish(ISuite context) {
		if (extent != null) {

			extent.flush();
		}
	}

	public void onStart(ISuite suite) {
		// TODO Auto-generated method stub
		
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}
}