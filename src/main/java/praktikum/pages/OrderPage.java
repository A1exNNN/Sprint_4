package praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import praktikum.EnvConfig;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

// Страницы заказа
// Описание кнопок и их методов ("Для кого самокат" и "Про аренду")

public class OrderPage {
    // Константы
    public static final By FIELDNAME = By.xpath("//input[@placeholder='* Имя']");
    public static final By FIELDSURNAME = By.xpath("//input[@placeholder='* Фамилия']");
    public static final By FIELDADDRESS = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");
    public static final By METROSTATIONLIST = By.xpath("//input[@placeholder='* Станция метро']");
    public static final By METROSTATION = By.xpath(".//li[@data-index='0']");
    public static final By FIELDPHONE = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");
    public static final By NEXTBUTTON = By.className("Button_Middle__1CSJM");
    public static final By CALENDAR = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    public static final By DATE = By.className("react-datepicker__day--019");
    public static final By LISTOFRENTALDAYS = By.className("Dropdown-placeholder");
    public static final By HOWMANYDAYS = By.xpath(".//*[@id='root']/div/div[2]/div[2]/div[2]/div[2]/div[1]");
    public static final By COMMENTFIELD = By.xpath("//input[@placeholder='* Комментарий для курьера']");
    public static final By ORDERBUTTON2 = By.xpath("//*[@id='root']/div/div[2]/div[3]/button[2]");
    public static final By YESBUTTON = By.xpath("//*[@id='root']/div/div[2]/div[5]/div[2]/button[2]");
    public final By button = By.className("Button_Button__ra12g");

    public WebDriver driver;

    // Конструктор класса
    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    // Метод явного ожидания видимости элемента
    public void waitForElementVisible(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.EXPLICIT_WAIT));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    // Методы взаимодействия с элементами

    // Ввод имени
    public void inputName(String firstName) {
        driver.findElement(FIELDNAME).sendKeys(firstName);
    }

    // Ввод фамилии
    public void inputSurname(String surName) {
        driver.findElement(FIELDSURNAME).sendKeys(surName);
    }

    // Ввод адреса
    public void inputAddress(String address) {
        driver.findElement(FIELDADDRESS).sendKeys(address);
    }

    // Раскрытие списка станций метро
    public void clickMetroStation() {
        driver.findElement(METROSTATIONLIST).click();
    }

    // Выбор станции метро из раскрытого списка
    public void chooseMetroStation() {
        waitForElementVisible(METROSTATION);
        driver.findElement(METROSTATION).click();
    }

    // Ввод номера телефона
    public void inputPhone(String phone) {
        driver.findElement(FIELDPHONE).sendKeys(phone);
    }

    // Нажать кнопку "Далее"
    public void clickNextButton() {
        driver.findElement(NEXTBUTTON).click();
    }

    // Открытие календаря
    public void openCalendar() {
        driver.findElement(CALENDAR).click();
    }

    // Выбор даты в открытом календаре
    public void chooseDate() {
        waitForElementVisible(DATE);
        driver.findElement(DATE).click();
    }

    // Раскрытие списка "Срок аренды"
    public void openListHowManyDays() {
        driver.findElement(LISTOFRENTALDAYS).click();
    }

    // Выбор количества дней аренды
    public void chooseHowManyDays() {
        waitForElementVisible(HOWMANYDAYS);
        driver.findElement(HOWMANYDAYS).click();
    }

    // Введение комментария
    public void inputComment() {
        driver.findElement(COMMENTFIELD).sendKeys("Тишина");
    }

    // Нажать кнопку "Заказать"
    public void clickOrderButton2() {
        driver.findElement(ORDERBUTTON2).click();
    }

    // Нажать кнопку "Да" в окне "Хотите оформить заказ"
    public void clickYes() {
        waitForElementVisible(YESBUTTON);
        driver.findElement(YESBUTTON).click();
    }

    // Проверка появления окна "Заказ оформлен"
    public void checkOrderSuccess() {
        waitForElementVisible(OrderCreationConfirmationWindow);
        assertTrue(driver.findElement(OrderCreationConfirmationWindow).isDisplayed());
    }

    private final By OrderCreationConfirmationWindow = By.xpath("//*[text() = 'Заказ оформлен']");

}

