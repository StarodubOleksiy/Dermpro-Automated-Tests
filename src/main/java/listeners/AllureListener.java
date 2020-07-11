package listeners;

import configuration.BaseMethod;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class AllureListener extends BaseMethod implements ITestListener, ISuiteListener, IInvokedMethodListener {

    /*This belongs to ISuiteListener and will execute before the Suite Starts*/
    @Override
    public void onStart(ISuite arg0) {
        Reporter.log("Execution of the Suite '" + arg0.getName() + "' Started!", false);
    }


    /*This belongs to ISuiteListener and will execute after the Suite Ends*/
    @Override
    public void onFinish(ISuite arg0) {
        Reporter.log("Execution of the Suite '" + arg0.getName() + "' Completed!", false);
    }


    /*This belongs to ITestListener, It will execute at the time of Test Execution */
    @Override
    public void onStart(ITestContext arg0) {
        Reporter.log("About to begin executing Test " + arg0.getName(), false);
    }


    /*This belongs to ITestListener, It will execute at the End of Test*/
    @Override
    public void onFinish(ITestContext arg0) {
        Reporter.log("Completed executing test " + arg0.getName(), false);
    }


    /*This belongs to ITestListener, It will Execute only when the Test is PASSED*/
    @Override
    public void onTestSuccess(ITestResult result) {
        Reporter.log("\nSUCCESFULLY EXECUTED TEST: " + result.getTestClass().getName() + "." + result.getMethod().getMethodName() + "\n", true);
        Reporter.log("\n");
    }


    /*This belongs to ITestListener, It will Execute only when the Test is FAILED*/
    @Override
    public void onTestFailure(ITestResult result) {
        String name = result.getTestClass().getName() + "." + result.getMethod().getMethodName();
        Reporter.log("\nFAILED TEST: " + result.getTestClass().getName() + "." + result.getMethod().getMethodName() + "\n", true);
    }

    /*This belongs to ITestListener, It and will execute before the Main Test Starts (@Test)*/
    @Override
    public void onTestStart(ITestResult result) {
        Reporter.log("\nSTARTED TESTING: " + result.getTestClass().getName() + "." + result.getMethod().getMethodName() + "\n", true);
    }


    /*This belongs to ITestListener, It will execute only if any of the Main Test(@Test) Gets Skipped*/
    @Override
    public void onTestSkipped(ITestResult result) {
        Reporter.log("\nSKIPPED TEST: " + result.getTestClass().getName() + "." + result.getMethod().getMethodName() + "\n", true);
    }

    /**
     * Taking screenshot in case if configuration method (e.g.& @Before) is failed and next Test will be skipped
     *
     * @param arg0
     * @param arg1
     */
    @Override
    public void afterInvocation(IInvokedMethod arg0, ITestResult arg1) {
        if (/*arg0.isConfigurationMethod() &&*/ arg1.getStatus() == ITestResult.FAILURE) {
            System.out.println("Test or configuration method has been Failed");
            addAttachment("Screenshot after failed configuration method");
        }
    }

    /*This belongs to IInvokedMethodListener, It will execute before every method Including @Before @After @Test*/
    public void beforeInvocation(IInvokedMethod arg0, ITestResult arg1) {
        String textMsg = "About to begin executing following method : " + returnMethodName(arg0.getTestMethod());
        Reporter.log(textMsg, false);
    }

    /*This will return method names to the calling function*/
    private String returnMethodName(ITestNGMethod method) {
        return method.getRealClass().getSimpleName() + "." + method.getMethodName();
    }


    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

    private void addAttachment(String message) {
        byte[] screenShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        Allure.getLifecycle().addAttachment(message, "image/png", "png", screenShot);
        System.out.println("screenshot added");
    }

    private InputStream getScreenshotByteIS(WebDriver driver) {
        InputStream is = null;
        try {
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Path content = Paths.get(scrFile.getAbsolutePath());
            is = Files.newInputStream(content);
            System.out.println("screenshot: " + scrFile.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return is;
    }

}
