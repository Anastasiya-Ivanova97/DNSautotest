import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class SearchResultPage extends PageSettings {
    public SearchResultPage() {
        super();
    }
    @FindBy(xpath = "//a[contains(@href,'playstation-4-slim-black')]")
    private
    WebElement searchItem;

    public void gotoItemPage(String keywords) {
        wait.until(ExpectedConditions.visibilityOf(searchItem));
        searchItem.click();
    }
}
