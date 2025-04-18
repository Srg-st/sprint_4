package srgst.practikum.ru;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;

@RunWith(Parameterized.class)
public class OrderScenarioUpperButtonTest {
    private WebDriver driver;

    private final String name;
    private final String surname;
    private final String address;
    private final String metro;
    private final String telephone;
    private final String dateDelivery;
    private final String comment;

    public OrderScenarioUpperButtonTest(String name, String surname, String address, String metro, String telephone, String dateDelivery, String comment) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metro = metro;
        this.telephone = telephone;
        this.dateDelivery = dateDelivery;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] getUserInfo() {
        return new Object[][]{
                {
                        "Тиль", "Линдеманн", "Москва, ул.Бажова, д.26", "ВДНХ", "88005553535", "04.04.2025", "Хочу кататься по Москве"
                },
                {
                        "Рихард", "Круспе", "Москва, ул.Тверская, д.10", "Лубянка", "89998887766", "05.04.2025", "На самокате можно многое успеть"
                },
        };
    }

    @Before
    public void startDriver() {
        //Реализация выбора браузера. По умолчанию используется chrome
        String browser = System.getProperty("browser","chrome");
        WebDriverFactory factory = WebDriverFactoryBrowser.getFactory(browser);
        driver = factory.driver();

    }

    @Test

    public void checkOrderConfirmScenario() throws InterruptedException {

        MainPageScooter mainPageScooter = new MainPageScooter(driver);
        //Принимаем Куки
        mainPageScooter.clickOnCookieButton();

        //Нажимаем на кнопку Заказать
        mainPageScooter.clickOnOrderButtonUpperSide();


        MainInfoUserOrderPage mainInfoUserOrderPage = new MainInfoUserOrderPage(driver);
        // Кликаем на поле Имя и заполняем его
        mainInfoUserOrderPage.enterNameField(name);


        //Кликаем на поле Фамилия и заполняем его
        mainInfoUserOrderPage.enterSurnameField(surname);


        //Кликаем на поле Адрес и заполняем его
        mainInfoUserOrderPage.enterAddressField(address);

        //Кликаем на поле Метро, заполняем его и кликаем на введенное название метро в выпадающем списке
        mainInfoUserOrderPage.enterMetroStationField(metro);

        //Кликаем на поле Телефон и заполняем его
        mainInfoUserOrderPage.enterTelephoneField(telephone);

        //Кликаем на кнопку Далее
        mainInfoUserOrderPage.clickNextStepButton();

        InfoAboutRentOrderPage infoAboutRentOrderPage = new InfoAboutRentOrderPage(driver);
        //Кликаем на поле Когда привезти самокат, заполняем его и закрываем окно с выбором даты
        infoAboutRentOrderPage.enterDateDeliveryField(dateDelivery);

        //Кликаем на поле Срок аренды и выбираем вариант Одни сутки из выпадающего списка
        infoAboutRentOrderPage.enterRentalPeriodField();

        //Кликаем на Чекбокс "черный жемчуг" в поле Цвет самоката
        infoAboutRentOrderPage.clickCnColourField();
        //Кликаем на поле Комментарий для курьера и заполняем его
        infoAboutRentOrderPage.enterCommentField(comment);


        //Кликаем на кнопку Заказать
        infoAboutRentOrderPage.clickOnOrderButtonFinish();
        //Кликаем на кнопку Да для подтверждения заказа
        infoAboutRentOrderPage.clickOnConfirmOrderButton();
        //Проверяем, что появилось окно Заказ оформлен
        infoAboutRentOrderPage.checkOrderConfirm();


    }


    @After
    public void tearDown() {
        driver.quit();
    }
}
