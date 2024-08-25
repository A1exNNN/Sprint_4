package praktikum;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import praktikum.pages.MainPage;
import praktikum.pages.OrderPage;

// Заказ самоката.
// Нужно проверить весь флоу позитивного сценария с двумя наборами данных.
// Проверить точки входа в сценарий, их две:
// кнопка «Заказать» вверху страницы и внизу.
// Из чего состоит позитивный сценарий:
// Нажать кнопку «Заказать». На странице две кнопки заказа.
// Заполнить форму заказа.
// Проверить, что появилось всплывающее окно с сообщением об успешном создании заказа.
// Нужно написать тесты с разными данными: минимум два набора.
// Какие именно данные использовать — на твоё усмотрение.


@RunWith(Parameterized.class)
public class OrderTest {
    private WebDriver driver;
    private  final String firstName;
    private  final String surName;
    private  final String adress;
    private  final String phone;
    private final boolean isHeader;

    // Заменяем @Before и @After на @Rule

    @Rule
    public DriverRule factory = new DriverRule();

    // конструктор класса
    public OrderTest(String firstName, String surName, String address, String phone, boolean isHeader) {
        this.firstName = firstName;
        this.surName = surName;
        this.phone = phone;
        this.adress = address;
        this.isHeader = isHeader;
    }

    // применяем параметризацию т.к. тесты отличаются только данными
    @Parameterized.Parameters
    public static Object[][] gettingClientData() {
        return new Object[][] {
                { "Александр", "Чернов", "Калуга", "+79530000000", true},
                { "Мария", "Лютая", "Брянск", "89107777777", false},
        };
    }

    // позитивный сценарий выполнения заказа
    @Test
    public void openMainPage() throws Exception {
        WebDriver driver = factory.getDriver();
        var mainPage = new MainPage(driver);
        var orderPage = new OrderPage(driver);

        // Открываем сайт заказа самокатов
        mainPage.openWebPage();

        // Закрываем банер о куках, кликнув на кнопку "да все привыкли"
        mainPage.clickAcceptCookiesButton();

        // Нажимаем на кнопку "Заказать" т.к. кнопки две (вверху и внизу страницы)
        // они есть в наборе параметризации
        if(isHeader) {
            mainPage.clickOrderTopButton();
        } else {
            mainPage.ClickOrderButtonAtTheBottom();
        }

        // заполнить форму "Для кого самокат"
        // заполнить поле имя
        orderPage.inputName(firstName);
        // заполнить поле фамилия
        orderPage.inputSurname(surName);
        // заполнить поле адрес куда привезти самокат
        orderPage.inputAddress(adress);
        // нажать на поле выбора "станция метро" для его раскрытия
        orderPage.clickMetroStation();
        // после раскрытия списка, выбрать станцию метро
        orderPage.chooseMetroStation();
        // заполнить поле телефон
        orderPage.inputPhone(phone);
        // нажать на кнопку Далее
        orderPage.clickNextButton();

        // заполнить форму "Про аренду"
        // после нажатия на кнопку "далее" в форме "Для кого самокат"
        // происходит переход на экран "Про аренду"
        // нажать на поле "Когда привезти самокат"
        orderPage.openCalendar();
        // выбрать дату после появления календаря
        orderPage.chooseDate();
        // нажать на поле "Срок аренды"
        orderPage.openListHowManyDays();
        // выбрать длительность аренды после появления выпадающего списка
        orderPage.chooseHowManyDays();

        // нажать на кнопку "Заказать"
        orderPage.clickOrderButton2();

        // после появления окна "Хотите оформить заказ?" нажать на кнопку "Да"
        orderPage.clickYes();

        // подождать пока появится окно оформленного заказа
        // проверить появилось ли окно
        orderPage.checkOrderSuccess();
    }
}