import org.junit.Assert;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.function.Function;

public class BasketPage extends PageSettings {
    Integer temp;
    public BasketPage() {
        super();
    }

    @FindBy(xpath = "//div[@class='radio radio_checked']/child::label")
    private
    WebElement check;

    @FindBy(xpath = "//a[contains(@href,'playstation-4-slim-black')]/parent::div/parent::div/descendant::span[1]")
    private
    WebElement item1;

    @FindBy(xpath = "//a[contains(@href,'igra--detroit')]/parent::div/parent::div/descendant::span[1]")
    private
    WebElement item2;

    @FindBy(xpath = "//a[contains(@href,'igra--detroit')]/parent::div/parent::div/descendant::button[@class='remove-button']")
    private
    WebElement removeItem;

    @FindBy(xpath = "//a[contains(@href,'igra--detroit')]")
    private
    WebElement removedItem;

    @FindBy(xpath = "//div[@class='total-amount__info-block']/descendant::span")
    WebElement sum;

    @FindBy(xpath = "//div[@class='count-buttons']")
    private
    WebElement plus;

    @FindBy(xpath = "//span[@class='pseudo-link']")
    private
    WebElement itemBack;

    @FindBy(xpath = "//input[@class='count-buttons__input']")
    private
    WebElement amount;

    public void checkGuarantee() {
        Assert.assertTrue(check.isEnabled());
    }

    public void checkSum(ItemPage itemPage) {
        wait.until(ExpectedConditions.visibilityOf(item1));
        wait.until(ExpectedConditions.visibilityOf(item2));
        Assert.assertTrue(itemPage.getBefore().intValue()==itemPage.toNumber(item1).intValue());
        Assert.assertTrue(itemPage.getNewItem().intValue()==itemPage.toNumber(item2).intValue());
        Assert.assertTrue(itemPage.getNewItem().intValue()+itemPage.getBefore().intValue()+itemPage.getUpdated().intValue()==itemPage.toNumber(sum).intValue());
        temp = itemPage.toNumber(sum);


    }
    public void removeItem() {
        removeItem.click();
    }
     public void checkAfterRemove(ItemPage itemPage){
         Function<? super WebDriver, Object> isPriceChanged = (ExpectedCondition<Object>) webDriver -> !itemPage.toNumber(sum).equals(temp);
         wait.until(isPriceChanged);
         int sum1 = itemPage.getBefore()+itemPage.getUpdated();
         int sum2 = itemPage.toNumber(sum);

        Assert.assertTrue(sum1==sum2);
     }

     public void addItems(Integer count) {
         for (int i = 0; i == count ; i++) {
             plus.click();
         }
     }
     public void checkNew(ItemPage itemPage) {
        temp = ((itemPage.getBefore()*3)+(itemPage.getUpdated()*3));
         System.out.println(temp);
         Function<? super WebDriver, Object> isAmountChanged = new ExpectedCondition<Object>() {
             @Override
             public Boolean apply(WebDriver webDriver) {
                 return  temp.equals(itemPage.toNumber(sum));
             }
         };
         wait.until(isAmountChanged);

        Assert.assertTrue(temp.intValue()==itemPage.toNumber(sum).intValue());
     }

     public void takeBack(ItemPage itemPage) {
        itemBack.click();
        Assert.assertTrue(removedItem.isDisplayed());
        Assert.assertTrue(temp+itemPage.getNewItem().intValue()==itemPage.toNumber(sum).intValue());
     }

}
