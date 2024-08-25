package praktikum;

import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.JavascriptExecutor;
import praktikum.pages.ImportantQuestionsPage;
import praktikum.pages.MainPage;

import static org.junit.Assert.assertEquals;

// Параметризацию применяют когда тесты отличаются только тестовыми данными
@RunWith(Parameterized.class)

// когда нажимаешь на стрелочку или текст вопроса, открывается соответствующий текст
public class ImportantQuestionsTest {
    // Необходимо ввести переменную для вопросов
    private final String itemId;

    // Браузер открывается в начале теста, а не перед проверкой каждого вопроса
    @ClassRule
    public static DriverRule driverRule = new DriverRule();

    // Конструктор
    public ImportantQuestionsTest(String itemId) {
        this.itemId = itemId;
    }

    @BeforeClass
    public static void acceptCookies() {
        var mainPage = new MainPage(driverRule.getDriver());
        mainPage.openWebPage();
        mainPage.clickAcceptCookiesButton();
        // прокручиваем страницу. без прокрутки в firefox тест падал
        JavascriptExecutor js = (JavascriptExecutor) driverRule.getDriver();
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }

    @Parameterized.Parameters
    public static Object[][] ImportantQuestionsData() {
        return new Object[][]{
                {"0"},
                {"1"},
                {"2"},
                {"3"},
                {"4"},
                {"5"},
                {"6"},
                {"7"}
        };
    }

      @Test
    public void clickOnImportantQuestionsItem() throws Exception {
        ImportantQuestionsPage importantQuestionsPage = new ImportantQuestionsPage(driverRule.getDriver());
        // список должен быть свернут, дальше нажимаем на текст вопроса и ожидаем пока откроется ответ
        importantQuestionsPage.checkAnswerIsInvisible(itemId).clickOnQuestion(itemId).waitForAnswer(itemId);
        // необходимо получить текст ответа на вопрос
        String actualAnswerText = importantQuestionsPage.getAnswerText(itemId);
        // Сверяем соотвтетствие текста с фактическим текстом
        String expectedAnswerText = importantQuestionsPage.getExpectedText(itemId);
        assertEquals(ImportantQuestionsPage.MISMATCHED_TEXT_ERROR, expectedAnswerText, actualAnswerText);
    }
}