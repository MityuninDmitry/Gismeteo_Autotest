
import AdditionalFunctionality.TakeScreenShot;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.EkbPage;
import pages.MainPage;

public class IntegrationTest {
    WebDriver driver;
    @Before
    public void runDriver(){
        // создаем инстанс вебдрайвера
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
    }
    // Тест сверки инфы виджета Екатеринбурга на странице с прогнозом на 2 недели и на самом виджете
    @Test
    public void testInfoOfRandAvailableWidgetInNear2Week(){

        // инициализируем главную страницу
        MainPage mainPage = new MainPage(driver);


        EkbPage ekbPage = mainPage.
                            openMainPage(). // открываем главную страницу
                            typeNameOfCity("Екатеринбург"). // вводим название города
                            searchFirstRelevantPosition(). // выбираем 1 позицию из выпадающего списка
                            openEkbPage(); // открываем страницу Екатеринбурга

        // получаем текст виджета дня
        String first_result_text = ekbPage.
                                        openTwoWeeksPage(). // открываем страницу с прогнозом на две недели
                                        getRandomAvailableWidget(). // индекс случайного доступного для клика виджета
                                        getTextOfRandomAvailableWidget(); // получаем текст из виджета

        // после того, как перешли на страницу виджета, получаем текст заново
        String second_result_text = ekbPage.
                                        goToPageOfRandomAvailableWidget(). // идем на страницу виджета
                                        getTextOfTabOfRandomAvailableWidget(); // получаем текст из виджета

        // проверяем на совпадение тексты виджетов
        Assert.assertEquals(first_result_text,second_result_text);

        // сделать скриншот и положить его в папку
        TakeScreenShot.shoot(driver,"target/report/");

        // закрыть драйвер
        driver.close();
    }

}
