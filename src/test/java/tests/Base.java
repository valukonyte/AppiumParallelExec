package tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.testng.annotations.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class Base {
    public AppiumDriver driver;
    private String deviceName;


    @Parameters("Device")
    @BeforeMethod
    public void setupRemoteDriver(String device){
        loadProperties(device);

        AppiumDriverLocalService service = new AppiumServiceBuilder()
                .withIPAddress("127.0.0.1")
                .usingAnyFreePort()
                .build();
        service.start();

        UiAutomator2Options options = new UiAutomator2Options()
                .setUdid(deviceName)
                .setPlatformName("Android")
                .setAutomationName("UiAutomator2")
                .setAvdReadyTimeout(Duration.ofSeconds(5))
                .setAvdLaunchTimeout(Duration.ofSeconds(5))
                .setApp("/Users/vaida/edON/appium/Android-MyDemoAppRN.1.3.0.build-244.apk")
                .eventTimings();

        driver = new AndroidDriver(service.getUrl(), options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    private void loadProperties(String device) {
        FileInputStream propertiesFIS;
        Properties properties = new Properties();
        String propertiesFilePath = String.format("%s/src/test/java/resources/%s.properties",
                System.getProperty("user.dir"), device);
        try {
            propertiesFIS = new FileInputStream(propertiesFilePath);
            properties.load(propertiesFIS);
            deviceName = properties.getProperty("device_name");

        } catch (IOException e) {
            System.out.println("Properties file is missing or invalid! Check path to file: " + propertiesFilePath);
            System.exit(0);
        }
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
