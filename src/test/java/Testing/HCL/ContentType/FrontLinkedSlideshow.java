package Testing.HCL.ContentType;

import Testing.CommonFunction;
import Testing.HCL.HCLFunctions;
import Testing.HCL.HCLAbstract;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.internal.MethodSorter;
import org.junit.runner.RunWith;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by om on 1/24/2015.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(value = Parameterized.class)
public class FrontLinkedSlideshow extends HCLAbstract {
    private String Title;
    private String Body;
    private String FrontSlideshowImage;
    private String AlternateText;
    private String PictureTitle;
    private String FrontSlideShowLink;
    private String MetatagDescription;
    private String MetatagKeyword;

    /**
     * Define a Collection method that will return the collection of parameters to the parallelTestThroughJUnit class
     * by using the @Parameters annotation.
     */
    @Parameterized.Parameters
    public static Collection testData() {
        return Arrays.asList(
                new Object[][]{
                        {"Title", "Body", FilesPath + "abc.jpg", "AltText", "PicTitle", "http://google.com/", "MetaDesc", "MetaKey", "All inputs are correct", "Admin"}
//                        {"Today", "-1", "70", "FB Total is negative"},
//                        {"Today", "181", "-1", "Twitter Total is negative"},
//                        {"Today", "178", "abc", "Twitter Total is alphabetic"},
//                        {"Today", "abc", "15","FB Total is alphabetic"},
//                        {"Yesterday", "160", "45", "When date of past"},
//                        {"Tomorrow", "160", "45", "When date of future"}
                }
        );
    }

    // Constructor will be used by the test runner to pass the parameters to the Excel_drive class instance.
    public FrontLinkedSlideshow(String Title, String Body, String FrontSlideshowImage, String AlternateText, String PictureTitle, String FrontSlideShowLink, String MetatagDescription, String MetatagKeyword, String TestCase, String Role) {
        this.Title = Title;
        this.Body = Body;
        this.FrontSlideshowImage = FrontSlideshowImage;
        this.AlternateText = AlternateText;
        this.PictureTitle = PictureTitle;
        this.FrontSlideShowLink = FrontSlideShowLink;
        this.MetatagDescription = MetatagDescription;
        this.MetatagKeyword = MetatagKeyword;
        this.TestCase = TestCase;
        this.Role = Role;
    }

    @Test
    public void CreateContent() throws Exception {
        try {
            // Move to Front Linked Slideshow
            driver.get(baseUrl + "/node/add/front-linked-slideshow");
            // Enter Title
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-title-field')]//input")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-title-field')]//input")).sendKeys(Title);
            // Select Language
            func2.SelectRandomSelectListOption("//div[contains(@class, 'form-item-language')]//select");
            // Enter Body
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-body')]//textarea[contains(@class, 'text-full')]/following-sibling::div[contains(@class, 'cke_browser_gecko')]//span[contains(@class, 'cke_button__source_label')]")).click();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-body')]//textarea[contains(@class, 'cke_editable_themed')]")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-body')]//textarea[contains(@class, 'cke_editable_themed')]")).sendKeys(Body);
            // Upload Front Slideshow Image
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-front-slideshow-image')]//input[@type='file']")).sendKeys(FrontSlideshowImage);
            // Click Upload
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-front-slideshow-image')]//input[@type='submit']")).click();
            // Wait for upload
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'field-name-field-front-slideshow-image')]//input[@value='Remove']")));
            // Enter alternate text
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-front-slideshow-image')]//input[contains(@id, 'alt')]")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-front-slideshow-image')]//input[contains(@id, 'alt')]")).sendKeys(AlternateText);
            // Enter picture title
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-front-slideshow-image')]//input[contains(@id, 'title')]")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-front-slideshow-image')]//input[contains(@id, 'title')]")).sendKeys(PictureTitle);
            // Enter Front Slideshow Link
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-front-slideshow-link')]//input")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-front-slideshow-link')]//input")).sendKeys(FrontSlideShowLink);
            // Enter Metatag Description
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-node-metatag-description')]//textarea")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-node-metatag-description')]//textarea")).sendKeys(MetatagDescription);
            // Enter Metatag Keyword
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-node-metatag-keywords')]//textarea")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-node-metatag-keywords')]//textarea")).sendKeys(MetatagKeyword);
            // Click Save
            driver.findElement(By.id("edit-submit")).click();
            // Check content created or not
            driver.findElement(By.xpath("//div[@class='messages messages--status']"));
            // Set Result Pass
            func.SetResultPass(Role, TestCase);
        } catch (Exception e) {
            // Set Result Fail
            func.SetResultFail(Role, TestCase, e);
        }
    }

    @Test
    public void UpdateContent() throws Exception {
        try {
            // Click edit link of that content which type is Front Linked Slideshow
            driver.get(baseUrl + "/admin/content");
            // Click edit link of that content which type is Front Linked Slideshow
            List<WebElement> Types = driver.findElements(By.xpath("//table[contains(@class, 'sticky-enabled')]/tbody/tr/td[3]"));
            String ContentType = null;
            int RowNumber = 0;
            for (int i = 0; i < Types.size(); i++) {
                ContentType = Types.get(i).getText();
                if (ContentType.equals("Front Linked Slideshow")) {
                    RowNumber = i + 1;
                    driver.findElement(By.xpath("//table[contains(@class, 'sticky-enabled')]/tbody/tr[" + RowNumber + "]/td[8]/ul/li[1]/a")).click();
                    break;
                }
            }
            // Enter Title
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-title-field')]//input")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-title-field')]//input")).sendKeys(Title);
            // Select Language
            func2.SelectRandomSelectListOption("//div[contains(@class, 'form-item-language')]//select");
            // Enter Body
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-body')]//textarea[contains(@class, 'text-full')]/following-sibling::div[contains(@class, 'cke_browser_gecko')]//span[contains(@class, 'cke_button__source_label')]")).click();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-body')]//textarea[contains(@class, 'cke_editable_themed')]")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-body')]//textarea[contains(@class, 'cke_editable_themed')]")).sendKeys(Body);
            // Remove Front Slideshow Image
            if (func.isElementPresent(By.xpath("//div[contains(@class, 'field-name-field-front-slideshow-image')]//input[@value='Remove']"))) {
                driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-front-slideshow-image')]//input[@value='Remove']")).click();
                // Wait for remove
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'field-name-field-front-slideshow-image')]//input[@type='file']")));
            }
            // Upload Front Slideshow Image
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-front-slideshow-image')]//input[@type='file']")).sendKeys(FrontSlideshowImage);
            // Click Upload
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-front-slideshow-image')]//input[@type='submit']")).click();
            // Wait for upload
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'field-name-field-front-slideshow-image')]//input[@value='Remove']")));
            // Enter alternate text
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-front-slideshow-image')]//input[contains(@id, 'alt')]")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-front-slideshow-image')]//input[contains(@id, 'alt')]")).sendKeys(AlternateText);
            // Enter picture title
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-front-slideshow-image')]//input[contains(@id, 'title')]")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-front-slideshow-image')]//input[contains(@id, 'title')]")).sendKeys(PictureTitle);
            // Enter Front Slideshow Link
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-front-slideshow-link')]//input")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-front-slideshow-link')]//input")).sendKeys(FrontSlideShowLink);
            // Enter Metatag Description
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-node-metatag-description')]//textarea")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-node-metatag-description')]//textarea")).sendKeys(MetatagDescription);
            // Enter Metatag Keyword
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-node-metatag-keywords')]//textarea")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-node-metatag-keywords')]//textarea")).sendKeys(MetatagKeyword);
            // Click Save
            driver.findElement(By.id("edit-submit")).click();
            // Check content created or not
            driver.findElement(By.xpath("//div[@class='messages status']"));
            // Set Result Pass
            func.SetResultPass(Role, TestCase);
        } catch (Exception e) {
            // Set Result Fail
            func.SetResultFail(Role, TestCase, e);
        }
    }
}
