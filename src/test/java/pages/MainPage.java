package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MainPage {
    private WebDriver webDriver;
    private final String URL = "https://www.gismeteo.ru";
    private By search_input_Locator = By.id("js-search");
    private By search_container_Locator = By.xpath(".//*[@id='js-search-list']/*/a[1]");

    private WebElement search_container;
    private WebElement search_input;

    public MainPage(WebDriver webDriver){
        this.webDriver = webDriver;
    }

    public MainPage openMainPage(){
        webDriver.get(URL);
        return this;
    }
    public MainPage typeNameOfCity(String nameOfCity){
        // находим импут
        search_input = webDriver.findElement(search_input_Locator);
        // вводим туда текст
        search_input.sendKeys(nameOfCity);
        // спим несколько секунд, чтобы js выпадающего списка сообразил, какой список надо вывести
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this;
    }
    public MainPage searchFirstRelevantPosition(){
        // ищем первую строчку
        search_container = webDriver.findElement(search_container_Locator);
        return this;
    }

    public EkbPage openEkbPage(){
        // клик
        search_container.click();
        return new EkbPage(webDriver);
    }

}
