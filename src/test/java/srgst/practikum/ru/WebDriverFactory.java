package srgst.practikum.ru;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public interface WebDriverFactory {
    WebDriver driver();
}

class ChromeDriverFactory implements WebDriverFactory {
    @Override
    public WebDriver driver() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get(MainPageScooter.SCOOTER_URL);
        return driver;
    }
}

class FirefoxDriverFactory implements WebDriverFactory {
    @Override
    public WebDriver driver() {
        WebDriverManager.firefoxdriver().setup();
        WebDriver driver = new FirefoxDriver();
        driver.get(MainPageScooter.SCOOTER_URL);
        return driver;
    }
}

class WebDriverFactoryBrowser {
    public static WebDriverFactory getFactory(String browser){
        switch (browser){
            case "chrome":
                return new ChromeDriverFactory();
            case "firefox":
                return new FirefoxDriverFactory();
            default:
                throw new IllegalArgumentException("Браузер не поддерживается "+ browser);
        }
    }
}