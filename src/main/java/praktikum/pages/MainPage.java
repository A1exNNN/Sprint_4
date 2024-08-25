package praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import praktikum.EnvConfig;

// Главная страница
// Описание кнопок и их методов
public class MainPage {

    // Локаторы

    // Кнопка принятяя куков
    private final By ACCEPTCOOKIESBUTTON = By.id("rcc-confirm-button");

    // Кнопка "заказать" вверху
    private final By ORDERTOPBUTTON = By.className("Button_Button__ra12g");

    // Кнопка "заказать" внизу
    private final By ORDERBOTTONBUTTOM = By.xpath(".//div[@class='Home_FinishButton__1_cWm']/button");

    private final WebDriver driver;

    // Конструктор класса
    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    // Методы взаимодействия с элементами

    // Открыть страницу сайта
    public void openWebPage() {
        driver.get(EnvConfig.BASE_URL);
    }

    // Нажать на кнопку принятия кук
    public void clickAcceptCookiesButton() {
        driver.findElement(ACCEPTCOOKIESBUTTON).click();
    }

    // Нажать на кнопку "заказать" расположенную вверху
    public void clickOrderTopButton() {
        driver.findElement(ORDERTOPBUTTON).click();
    }

    // Нажать на кнопку "заказать" расположенную внизу
    public void ClickOrderButtonAtTheBottom() {
        driver.findElement(ORDERBOTTONBUTTOM).click();
    }

    public Object ImportantQuestionsPage() {
        return null;
    }

}