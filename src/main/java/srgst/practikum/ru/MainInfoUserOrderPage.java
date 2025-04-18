package srgst.practikum.ru;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainInfoUserOrderPage {
    private WebDriver driver;
    private final By nameField = By.xpath(".//input[contains(@placeholder,'Имя')]");
    private final By surnameField = By.xpath(".//input[contains(@placeholder,'Фамилия')]");
    private final By addressField = By.xpath(".//input[contains(@placeholder,'Адрес')]");
    private final By metroStationField = By.xpath(".//div/input[@class='select-search__input']");
    private final By metroStationFromList = By.className("select-search__select");
    private final By telephoneField = By.xpath(".//input[contains(@placeholder,'Телефон')]");

    private final By nextStepButton = By.xpath(".//div[@class='Order_NextButton__1_rCA']/button[contains(text(),'Далее')]");

    public MainInfoUserOrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterNameField(String name ) {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(nameField));
        driver.findElement(nameField).click();
        driver.findElement(nameField).sendKeys(name);
    }



    public void enterSurnameField(String surname) {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(surnameField));
        driver.findElement(surnameField).click();
        driver.findElement(surnameField).sendKeys(surname);
    }



    public void enterAddressField(String address) {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(addressField));
        driver.findElement(addressField).click();
        driver.findElement(addressField).sendKeys(address);
    }

    public void enterMetroStationField(String metro){
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(metroStationField));
        driver.findElement(metroStationField).click();
        driver.findElement(metroStationField).sendKeys(metro);
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(metroStationFromList));
        driver.findElement(metroStationFromList).click();
    }

    public void enterTelephoneField(String telephone){
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(telephoneField));
        driver.findElement(telephoneField).click();
        driver.findElement(telephoneField).sendKeys(telephone);
    }

    public void clickNextStepButton(){
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(nextStepButton));
        driver.findElement(nextStepButton).click();
    }
}
