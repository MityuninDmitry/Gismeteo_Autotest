package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;

public class EkbPage {
    private By twoWeeksWeatherButton_Locator = By.xpath("//a[contains(@href,'2-weeks')]");
    private By elementsWithTemps_Locator  = By.className("svg-temp-temp");
    private By activeTab_Locator = By.xpath("//div[@class='tab  tooltip']");
    private By textWidget_Locator;

    private WebDriver webDriver;
    private WebElement widget;

    public EkbPage(WebDriver webDriver){
        this.webDriver = webDriver;
    }

    public EkbPage openTwoWeeksPage(){
        WebElement twoWeeksWeatherButton = webDriver.findElement(twoWeeksWeatherButton_Locator);
        twoWeeksWeatherButton.click();
        return this;
    }

    public EkbPage getRandomAvailableWidget(){
        /*
        В этом закомменченом блоке был расчет виджета с самой низкой температурой.
        Этот код оставил для просмотра, но т.к я слегка отошел от исходного задания, то воспользовался другим кодом ниже.
        // индекс виджета с минимальной температурой
        int index = 0;
        ArrayList<Integer> listOfTemperature = new ArrayList<Integer>(); // список с температурами
        // идем по всем виджетам дням
        for (WebElement element: webDriver.findElements(elementsWithTemps_Locator))
        {
            // Всего виджетов 14, в каждом из которых 2 температуры(макс и мин - всего 28):
            // 1 половиная - максимальная температура дней
            // 2 половина - минимальная температура дней <-- они нам нужны, чтобы вычислить виджет с самой низкой температурой

            // вторая половина элементов это минимальные температуры дней
            if (index > 13){
                String temp = element.getText().trim(); // получаем значение
                temp = temp.replaceAll("−", "-"); // меняем символ для парсинга
                listOfTemperature.add(Integer.parseInt(temp)); // добавляем в список температур
            }
            index++; // увеличиваем индекс
        }
        */
        // далее ищем индекс виджета самой маленькой температурой
        /*
        int min = listOfTemperature.get(0);
        for (int i = 0; i < listOfTemperature.size(); i++) {
            if (min > listOfTemperature.get(i)){
                min = listOfTemperature.get(i);
                index = i; // но не используем нигде, т.к поменял задание
            }
        }
        */
        int indexRandAvailableWidget = 1 + (int) (Math.random() * 9); // случайный из 10 доступных для клика виджетов
        // инициализируем локатор нашего виджета
        textWidget_Locator = By.xpath("//a[@class='widget__item tooltip' and position()=" + indexRandAvailableWidget +"]");

        return this;
    }

    public String getTextOfRandomAvailableWidget(){
        widget = webDriver.findElement(textWidget_Locator);
        return widget.getAttribute("data-text");
    }

    public EkbPage goToPageOfRandomAvailableWidget(){
        widget.click();
        return this;
    }
    public String getTextOfTabOfRandomAvailableWidget(){
        WebElement active_tab = webDriver.findElement(activeTab_Locator);
        return active_tab.getAttribute("data-text");
    }
}
