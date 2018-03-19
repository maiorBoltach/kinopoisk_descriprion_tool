package ru.kinopoisk.api;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class IdStealer {

    public static void main(String[] args) {
        //fromToGetId("1968", "1977");
        //fromToGetId("1978", "1987");
        //fromToGetId("1988", "1997");
        //fromToGetId("1998", "2007");
        //fromToGetId("2008", "2017");
        //fromToGetId("2008", "2017");
    }

    public static void fromToGetId(String yearFrom, String yearTo) {
        int pageTotalWrite = 0;
        PrintWriter idWriter = null;
        PrintWriter errorsWriter = null;
        ChromeOptions options = new ChromeOptions();
        options.addExtensions(new File("src/main/resources/adblock/AdBlock_v3.26.1.crx"));
        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

        try {
            idWriter = new PrintWriter("src/main/resources/films id/" + yearFrom + " - " + yearTo + ".txt");
            errorsWriter = new PrintWriter("src/main/resources/films id/" + yearFrom + " - " + yearTo + " errors.txt");

            driver.get("https://www.kinopoisk.ru/s/type/film/list/1/m_act%5Bfrom_year%5D/" +
                    yearFrom + "/m_act%5Bto_year%5D/" + yearTo + "/");

            WebElement mySelectElement = driver.findElement(By.className("navigator_per_page"));
            Select dropdown = new Select(mySelectElement);
            dropdown.selectByValue("200");

            WebElement nextPage = driver.findElement(By.xpath("//a[.= '»»']"));
            int lastPageNumber = getLastPageNumber(nextPage.getAttribute("href"));
            System.out.println(lastPageNumber);

            for (int i = 1; i <= lastPageNumber; i++) {
                try {
                    int sleep = generateRandomNumberInRange(500, 1500);
                    Thread.sleep(sleep);
                    driver.get("https://www.kinopoisk.ru/s/type/film/list/1/order/rating/m_act%5Bfrom_year%5D/" +
                            yearFrom + "/m_act%5Bto_year%5D/" + yearTo + "/page/" + i + "/");
                    List<WebElement> listLinks = driver.findElements(By.xpath("//p[@class = 'name'] //a"));

                    if (listLinks.size() > 0) {
                        for (WebElement element : listLinks) {
                            idWriter.println(element.getAttribute("data-id"));
                        }
                    } else {
                        errorsWriter.println("ATTENTION!!!!!!!!!!!!!!!LIST SIZE 0, PAGE: " + i);
                        errorsWriter.flush();
                    }
                    idWriter.flush();
                    pageTotalWrite++;
                    System.out.println("On page: " + pageTotalWrite);
                } catch (Exception e) {
                    errorsWriter.println("Error on page: " + i + e.getMessage());
                    errorsWriter.flush();
                }

            }
        } catch (FileNotFoundException e) {

        } finally {
            idWriter.flush();
            idWriter.close();
            errorsWriter.flush();
            errorsWriter.close();
        }

        System.out.println("");
        driver.quit();
    }

    private static Integer getLastPageNumber(String lastPage) {
        String[] lines = lastPage.split("/");
        return Integer.parseInt(lines[lines.length - 1]);
    }

    private static int generateRandomNumberInRange(int min, int max) {
        max -= min;
        return (int) (Math.random() * ++max) + min;
    }

}
