package praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import praktikum.EnvConfig;

import java.time.Duration;

public class ImportantQuestionsPage {
    private final WebDriver driver;
    private final String answerIdPrefix = "accordion__panel-";
    public static final String MISMATCHED_TEXT_ERROR = "Текст ответа не соответствует ожидаемому";


    public ImportantQuestionsPage(WebDriver driver) {
        this.driver = driver;
    }


    public ImportantQuestionsPage checkAnswerIsInvisible(String id) {
        assert !driver.findElement(By.id(answerIdPrefix + id)).isDisplayed();

        return this;
    }

    // нажать на текст вопроса
    public ImportantQuestionsPage clickOnQuestion(String id) {
        String questionsIdPrefix = "accordion__heading-";
        driver.findElement(By.id(questionsIdPrefix + id)).click();

        return this;
    }

    // ожидание ответа
    public ImportantQuestionsPage waitForAnswer(String id) {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.IMPLICIT_WAIT))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id(answerIdPrefix + id)));

        return this;
    }

    // получить текст ответта
    public String getAnswerText(String id) {
        By answerLocator = By.id(answerIdPrefix + id);
        return driver.findElement(answerLocator).getText();
    }

    //  ожидаемый текст
    public String getExpectedText(String id) {
        switch (id) {
            case "0":
                return "Сутки — 400 рублей. Оплата курьеру — наличными или картой.";
            case "1":
                return "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.";
            case "2":
                return "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.";
            case "3":
                return "Только начиная с завтрашнего дня. Но скоро станем расторопнее.";
            case "4":
                return "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.";
            case "5":
                return "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.";
            case "6":
                return "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.";
            case "7":
                return "Да, обязательно. Всем самокатов! И Москве, и Московской области.";
            default:
                return "Текст не найден";
        }
    }
}