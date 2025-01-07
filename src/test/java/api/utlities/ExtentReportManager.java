package api.utlities;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager implements ITestListener{
	
public ExtentSparkReporter sparkreporter;  // for look and feel
public ExtentReports extent;  // for system informations
public ExtentTest test;  // test results.
String repName;

/* what is testng listener: once you execute test methods, based on the status
   listener will perform some post actions, suppose my test method may pass or fail.
   so report generation is one of the post method
   to create apost action we will create utility file which is implemented ITestListener interface
    

*/
public void onStart(ITestContext testContext) {
	
	String timestamp=new SimpleDateFormat("YYYY.MM.dd.HH.mm.ss").format(new Date()); // time stamp
	repName="test-Report"+timestamp+".html";
	
	// Look and feel of the report
	sparkreporter=new ExtentSparkReporter(".//reports//"+repName); // specify location of report
	sparkreporter.config().setDocumentTitle("Projectname"); //title of the project
	sparkreporter.config().setReportName("reportname"); //report name
	sparkreporter.config().setTheme(Theme.DARK); //Set report theme - DARK/WHITE

	// Adding system information in the report
	extent= new ExtentReports();
	extent.attachReporter(sparkreporter);
	extent.setSystemInfo("Application", "Roche");
	extent.setSystemInfo("Operating system", System.getProperty("os.name"));
	extent.setSystemInfo("UserName ", System.getProperty("user.name"));
	extent.setSystemInfo("Environment", "QA");
	extent.setSystemInfo("user", "Ravi");
}

public void onTestSuccess(ITestResult result) {
	test=extent.createTest(result.getName());
	test.assignCategory(result.getMethod().getGroups());
	test.createNode(result.getName());
	test.log(Status.PASS, "Test Passed");
}

public void onTestFailure(ITestResult result) {
	test=extent.createTest(result.getName());
	test.assignCategory(result.getMethod().getGroups());
	test.createNode(result.getName());
	test.log(Status.FAIL, "Test Failed");
	test.log(Status.FAIL, result.getThrowable().getMessage());

}

public void onTestSkipped(ITestResult result) {
	test=extent.createTest(result.getName());
	test.assignCategory(result.getMethod().getGroups());
	test.createNode(result.getName());
	test.log(Status.SKIP, "Test Failed");
	test.log(Status.SKIP, result.getThrowable().getMessage());

}

public void onFinish(ITestContext testContext) {
	extent.flush();  // if not flushed the report will not be generated.
}
}


