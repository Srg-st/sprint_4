package srgst.practikum.ru;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

public class InfoAboutRentOrderPage {

    private WebDriver driver;

    private final By dateDeliveryScooterField = By.xpath(".//input[contains(@placeholder,'Когда')]");

    private final By rentalPeriodField = By.className("Dropdown-placeholder");
    private final By dropDownList = By.className("Dropdown-menu");
    private final By oneDayRentalSelect = By.xpath(".//div[text()='сутки']");

    private final By blackColourScooterField = By.id("black");

    private final By commentForCourierField = By.xpath(".//input[contains(@placeholder,'Комментарий')]");

    private final By orderButtonFinish = By.xpath(".//div[@class='Order_Buttons__1xGrp']/button[contains(text(),'Заказать')]");

    private final By confirmOrderButton = By.xpath(".//div[@class='Order_Buttons__1xGrp']/button[contains(text(),'Да')]");

    private final By confirmWindow = By.className("Order_ModalHeader__3FDaJ");

    private final String orderConfirmText = "Заказ оформлен";

    public InfoAboutRentOrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterDateDeliveryField(String dateDelivery){
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.elementToBeClickable(dateDeliveryScooterField));
        driver.findElement(dateDeliveryScooterField).click();
        driver.findElement(dateDeliveryScooterField).sendKeys(dateDelivery);
        //Закрываем окно с календарем с помощью клавиши Escape
        driver.findElement(dateDeliveryScooterField).sendKeys(Keys.ESCAPE);
    }


    public void enterRentalPeriodField(){
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(rentalPeriodField));
        driver.findElement(rentalPeriodField).click();
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(dropDownList));
        driver.findElement(oneDayRentalSelect).click();
    }


    public void clickCnColourField(){
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(blackColourScooterField));
        driver.findElement(blackColourScooterField).click();
    }

    public void enterCommentField(String comment){
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(commentForCourierField));
        driver.findElement(commentForCourierField).click();
        driver.findElement(commentForCourierField).sendKeys(comment);
    }



    public void clickOnOrderButtonFinish(){
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(orderButtonFinish));
        driver.findElement(orderButtonFinish).click();
    }

    public void clickOnConfirmOrderButton(){
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(confirmOrderButton));
        driver.findElement(confirmOrderButton).click();
    }

    public void checkOrderConfirm(){
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(confirmWindow));
        String textOrder = driver.findElement(confirmWindow).getText();
        assertThat("Заказ не был сформирован", textOrder, containsString(orderConfirmText));

    }
}
