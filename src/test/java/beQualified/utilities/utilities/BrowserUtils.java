package beQualified.utilities.utilities;

import beQualified.utilities.Driver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class BrowserUtils {

    public static void refreshPage()
    { beQualified.utilities.Driver.getDriver().navigate().refresh();}

    /**
     * navigate to back
     */
    public static void navigateBack(){
        beQualified.utilities.Driver.getDriver().navigate().back();
    }

    /**
     * navigate to forward
     */
    public static void navigateForward(){
        beQualified.utilities.Driver.getDriver().navigate().forward();
    }

    /*
    This method will accept int (in seconds)
    and execute Thread.sleep method for given duration
    Arg: int second
     */
    public static void sleep(int second){
        second *= 1000;
        try{
            Thread.sleep(second);
        }catch (InterruptedException e){

        }
    }

    /**
     * verify title
     * @param expectedTitle
     */
    public static void verifyTitle(String expectedTitle){
        Assert.assertEquals(beQualified.utilities.Driver.getDriver().getTitle(), expectedTitle);
    }

    public static void verifyTitleContains( String expectedInTitle){
        Assert.assertTrue(beQualified.utilities.Driver.getDriver().getTitle().contains(expectedInTitle));
    }

    /**
     * This method will accept a String as expected value and verify actual URL CONTAINS the value.
     */
    public static void verifyURLContains(String expectedInURL){
        Assert.assertTrue(beQualified.utilities.Driver.getDriver().getCurrentUrl().contains(expectedInURL));
    }

    /**
     * return a list of string from a list of elements
     *
     * @param list of webelements
     * @return list of string
     */
    public static List<String> getElementsText(List<WebElement> list) {
        List<String> elemTexts = new ArrayList<>();
        for (WebElement el : list) {
            elemTexts.add(el.getText());
        }
        return elemTexts;
    }

    /**
     * Extracts text from list of elements matching the provided locator into new List<String>
     *
     * @param locator
     * @return list of strings
     */
    public static List<String> getElementsText(By locator) {

        List<WebElement> elems = beQualified.utilities.Driver.getDriver().findElements(locator);
        List<String> elemTexts = new ArrayList<>();

        for (WebElement el : elems) {
            elemTexts.add(el.getText());
        }
        return elemTexts;
    }

    public static String getFirstElementText(List<WebElement> list) {
        if (list == null || list.isEmpty()) {
            return null;  // Return null if the list is empty or null
        }
        return list.get(0).getText();  // Return string of the first element
    }

    /**
     * Performs a pause
     *
     * @param seconds
     */
    public static void waitFor(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * wait untill elelment to visit
     * @param element
     * @param time
     * @return
     */
    public static WebElement waitForVisibility(WebElement element, int time) {
        WebDriverWait wait = new WebDriverWait(beQualified.utilities.Driver.getDriver(), Duration.ofSeconds(time));
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Verifies whether the element matching the provided locator is displayed on page
     * @param element
     */
    public static void verifyElementDisplayed(WebElement element) {
        try {
            Assert.assertTrue("Element not visible: " + element, element.isDisplayed());
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            Assert.fail("Element not found: " + element);
        }
    }

    /**
     * Waits for element matching the locator to be clickable
     *
     * @param locator
     * @param time
     * @return
     */
    public static WebElement waitForClickablility(By locator, int time) {
        WebDriverWait wait = new WebDriverWait(beQualified.utilities.Driver.getDriver(), Duration.ofSeconds(time));
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    /**
     * Waits for the given WebElement to be clickable
     *
     * @param element the WebElement to wait for
     * @param time the maximum wait time in seconds
     * @return the WebElement once it is clickable
     */
    public static WebElement waitForToClickElement(WebElement element, int time) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(time));
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void compareProductLists(List<String> expectedProductsList, List<String> actualProductsList) {
        Assert.assertEquals("The lists should have the same number of products", expectedProductsList.size(), actualProductsList.size());

        for (int i = 0; i < expectedProductsList.size(); i++) {
            Assert.assertEquals("Product information does not match at index " + i,
                    expectedProductsList.get(i), actualProductsList.get(i));
        }
    }

}