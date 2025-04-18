package srgst.practikum.ru;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;

@RunWith(Parameterized.class)
public class OrderScenarioLowerButtonTest {
    private WebDriver driver;

    private final String name;
    private final String surname;
    private final String address;
    private final String metro;
    private final String telephone;
    private final String dateDelivery;
    private final String comment;

    public OrderScenarioLowerButtonTest(String name, String surname, String address, String metro, String telephone, String dateDelivery, String comment) {
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
                        "Пол", "Ландерс", "Москва, ул.Воздвиженка, д.9", "Арбатская", "88005553535", "04.04.2025", "Хочу кататься по Москве"
                },
                {
                        "Кристоф", "Шнайдер", "Москва, ул.Театральная площадь, д.1", "Театральная", "89998887766", "05.04.2025", "На самокате можно многое успеть"
                },
        };
    }

    @Before
    public void startDriver(){
        //Реализация выбора браузера. По умолчанию используется Chrome
        String browser = System.getProperty("browser","chrome");
        WebDriverFactory factory = WebDriverFactoryBrowser.getFactory(browser);
        driver = factory.driver();
    }

    @Test

    public void checkOrderConfirmScenario() throws InterruptedException {

        MainPageScooter mainPageScooter = new MainPageScooter(driver);
        //Принимаем Куки
        mainPageScooter.clickOnCookieButton();

        //Скроллим страницу до кнопки Заказать в нижней части страницы
        mainPageScooter.scrollToLowerOrderButton();

        //Нажимаем на кнопку Заказать
        mainPageScooter.clickOnOrderButtonLowerSide();

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

        InfoAboutRentOrderPage otherInfoOrderPage = new InfoAboutRentOrderPage(driver);

        //Кликаем на поле Когда привезти самокат, заполняем его и закрываем окно с выбором даты
        otherInfoOrderPage.enterDateDeliveryField(dateDelivery);

        //Кликаем на поле Срок аренды и выбираем вариант Одни сутки из выпадающего списка
        otherInfoOrderPage.enterRentalPeriodField();

        //Кликаем на Чекбокс "черный жемчуг" в поле Цвет самоката
        otherInfoOrderPage.clickCnColourField();

        //Кликаем на поле Комментарий для курьера и заполняем его
        otherInfoOrderPage.enterCommentField(comment);


        //Кликаем на кнопку Заказать
        otherInfoOrderPage.clickOnOrderButtonFinish();
        //Кликаем на кнопку Да для подтверждения заказа
        otherInfoOrderPage.clickOnConfirmOrderButton();

        //Проверяем, что появилось окно Заказ оформлен
        otherInfoOrderPage.checkOrderConfirm();


    }



    @After
    public void tearDown() {
        driver.quit();
    }
}