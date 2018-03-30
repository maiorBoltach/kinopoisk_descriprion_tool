package ru.kinopoisk.downloader.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import ru.kinopoisk.downloader.logger.LoggerClass;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class IdStealer {

    public static File downloadIdInYearInterval(String yearFrom, String yearTo) {
        LoggerClass.getInstanceSummaryLogger().trace("Executing download id tool ('" + yearFrom + "' - '" + yearTo + "'");
        int pageTotalWrite = 0;
        PrintWriter idWriter = null;
        ChromeOptions options = new ChromeOptions();
        options.addExtensions(new File("src/main/resources/extensions/AdBlock_v3.26.1.crx"));
        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        try {
            idWriter = new PrintWriter("src/main/resources/films id/" + yearFrom + " - " + yearTo + ".txt");

            driver.get("https://www.kinopoisk.ru/s/type/film/list/1/m_act%5Bfrom_year%5D/" +
                    yearFrom + "/m_act%5Bto_year%5D/" + yearTo + "/");

            WebElement mySelectElement = driver.findElement(By.className("navigator_per_page"));
            Select dropdown = new Select(mySelectElement);
            dropdown.selectByValue("200");

            WebElement nextPage = driver.findElement(By.xpath("//a[.= '»»']"));
            int lastPageNumber = getLastPageNumber(nextPage.getAttribute("href"));
            LoggerClass.getInstanceSummaryLogger().trace("All pages count: " + lastPageNumber);

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
                        LoggerClass.getInstanceSummaryLogger().warn("List size is 0, page: " + i);
                    }
                    idWriter.flush();
                    pageTotalWrite++;
                    LoggerClass.getInstanceSummaryLogger().trace("Working on page: " + pageTotalWrite + "/" + lastPageNumber);
                } catch (Exception e) {
                    LoggerClass.getInstanceSummaryLogger().warn("Error on page: " + i + e.getMessage());
                }

            }
        } catch (FileNotFoundException e) {
            LoggerClass.getInstanceSummaryLogger().fatal(e.getMessage());
        } finally {
            idWriter.flush();
            idWriter.close();
        }
        driver.quit();
        return new File("src/main/resources/films id/" + yearFrom + " - " + yearTo + ".txt");
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
