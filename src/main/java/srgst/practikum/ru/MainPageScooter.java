package srgst.practikum.ru;

import org.hamcrest.MatcherAssert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.hamcrest.CoreMatchers.is;

public class MainPageScooter {
    private WebDriver driver;

    public static final String SCOOTER_URL = "https://qa-scooter.praktikum-services.ru";

    private final By cookieButton = By.className("App_CookieButton__3cvqF");

    private final By orderButtonUpperSide = By.xpath(".//button[@class='Button_Button__ra12g']");

    private final By orderButtonLowerSide = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");

    private final By lowerOrderButtonLocation = By.className("Home_RoadMap__2tal_");

    private final By questionsBlockLocation = By.className("Home_FourPart__1uthg");

    private final By questionAboutPrice = By.id("accordion__heading-0");
    private final By questionAboutQuantity = By.id("accordion__heading-1");
    private final By questionAboutRentalTime = By.id("accordion__heading-2");
    private final By questionAboutRentalToday = By.id("accordion__heading-3");
    private final By questionAboutRentalPeriod = By.id("accordion__heading-4");
    private final By questionAboutCharger = By.id("accordion__heading-5");
    private final By questionAboutCancelOrder = By.id("accordion__heading-6");
    private final By questionAboutDeliveryDistance = By.id("accordion__heading-7");

    private final By answerAboutPriceOnWeb = By.xpath("//*[@id='accordion__panel-0']/p");
    private final By answerAboutQuantityOnWeb = By.xpath("//*[@id='accordion__panel-1']/p");
    private final By answerAboutRentalTimeOnWeb = By.xpath("//*[@id='accordion__panel-2']/p");
    private final By answerAboutRentalTodayOnWeb = By.xpath("//*[@id='accordion__panel-3']/p");
    private final By answerAboutRentalPeriodOnWeb = By.xpath("//*[@id='accordion__panel-4']/p");
    private final By answerAboutChargerOnWeb = By.xpath("//*[@id='accordion__panel-5']/p");
    private final By answerAboutCancelOrderOnWeb = By.xpath("//*[@id='accordion__panel-6']/p");
    private final By answerAboutDeliveryDistanceOnWeb = By.xpath("//*[@id='accordion__panel-7']/p");

    private final By[] questions = {
            questionAboutPrice,
            questionAboutQuantity,
            questionAboutRentalTime,
            questionAboutRentalToday,
            questionAboutRentalPeriod,
            questionAboutCharger,
            questionAboutCancelOrder,
            questionAboutDeliveryDistance
    };

    private final By[] answers = {
            answerAboutPriceOnWeb,
            answerAboutQuantityOnWeb,
            answerAboutRentalTimeOnWeb,
            answerAboutRentalTodayOnWeb,
            answerAboutRentalPeriodOnWeb,
            answerAboutChargerOnWeb,
            answerAboutCancelOrderOnWeb,
            answerAboutDeliveryDistanceOnWeb
    };

    public MainPageScooter(WebDriver driver) {
        this.driver = driver;
    }

    public void clickOnCookieButton() {
        driver.findElement(cookieButton).click();
    }

    public void scrollToLowerOrderButton() {
        WebElement element = driver.findElement(lowerOrderButtonLocation);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    public void clickOnOrderButtonUpperSide() {
        driver.findElement(orderButtonUpperSide).click();
    }

    public void clickOnOrderButtonLowerSide() {
        driver.findElement(orderButtonLowerSide).click();
    }


    public void scrollToQuestionsBlock() {
        WebElement element = driver.findElement(questionsBlockLocation);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
    }


    public void checkTextInAnswer(int index, String expectedTextAnswer) {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(questions[index]));
        driver.findElement(questions[index]).click();
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(answers[index]));
        String textInAnswer = driver.findElement(answers[index]).getText();
        MatcherAssert.assertThat("Текст ответа не совпадает", expectedTextAnswer, is(textInAnswer));
    }
}
