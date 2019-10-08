import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ItemPage extends PageSettings {
    private Integer before;
    private Integer updated;
    private Integer newItem;
    public ItemPage() {
        super();
    }
    @FindBy(xpath = "//span[@data-role='current-price-value']")
    private
    WebElement price;

    @FindBy(xpath = "//div[@class='desktop-selector']/child::select")
    private
    WebElement guarantee;

    @FindBy(xpath = "//option[@data-additional-years='2']")
    private
    WebElement chooseGuarantee;

    @FindBy(xpath = "//button[@data-action='cart-button']")
    private
    WebElement button;

    @FindBy(name = "q")
    private
    WebElement searchString;

    @FindBy(xpath = "//span[@class='ui-input-search__icon ui-input-search__icon_search']")
    private
    WebElement searchButton;

    @FindBy(xpath = "(//a[@href='/order/begin/']/descendant::span[3]")
    private
    WebElement totalPrice;



    public Integer toNumber(WebElement element) {
        String[] parts = element.getText().split("\\s");
        String full = "";
        for (int i = 0; i < parts.length ; i++) {
            full+= parts[i];
        }
        return Integer.parseInt(full);
    }
    public Integer toNumber(String string) {
        String[] parts = string.split("\\s");
        String full = "";
        for (int i = 0; i < parts.length ; i++) {
            full+= parts[i];
        }
        return Integer.parseInt(full);
    }

    public void changeGuarantee() {
        wait.until(ExpectedConditions.elementToBeClickable(guarantee));
        guarantee.click();
        chooseGuarantee.click();
    }

    public Integer setBefore() {
        before = toNumber(price);
        return before;
    }

    public Integer setAfter() {
        updated = toNumber(price)- before;
        return updated;
    }

    public void buyAndSearchAnotherItem(String keys) {
        button.click();
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", searchString);
        executor.executeScript("arguments[0].value = '" + keys + "'", searchString);
        executor.executeScript("arguments[0].click();", searchButton);
    }
    public void buy() {
        button.click();
        wait.until(ExpectedConditions.textToBe(By.xpath("//button[@data-action='cart-button']"),"В корзине"));
    }


    public Integer setNewItem() {
        newItem = toNumber(price);
        return newItem;
    }

    public Integer getBefore() {
        return before;
    }

    public Integer getUpdated() {
        return updated;
    }

    public Integer getNewItem() {
        return newItem;
    }

     public void checkTotalPrice() {
            String html = "<span data-of=\"totalPrice\">33 278</span>";
            Document doc = Jsoup.parse(html);
            String text = doc.body().text();
            Integer sum = toNumber(text);
            System.out.println(sum.equals(before+updated+newItem) ? "Сумма верна" : "Сумма неверна");
        }

   public void goToBasket() {
       button.click();
   }
}

