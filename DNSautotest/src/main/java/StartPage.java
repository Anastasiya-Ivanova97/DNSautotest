import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class StartPage extends PageSettings {

    @FindBy(xpath = "/html/body/header/div[2]/div/div[2]/div[2]/div[2]/form/div/div/div/input")
    WebElement searchString;

    @FindBy(xpath = "//span[@class='ui-input-search__icon ui-input-search__icon_search']")
    private
    WebElement searchButton;

    public StartPage(){
        super();
        this.driver = Initialization.getDriver();
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, 5);
    }

    public WebElement waitForElement(WebElement element){
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }
    public void search(String searchItem) {
        System.out.println(driver.getCurrentUrl()+" "+driver.getTitle());
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", searchString);
        executor.executeScript("arguments[0].value = '" + searchItem + "'", searchString);
        //searchString.sendKeys(searchItem);
        executor.executeScript("arguments[0].click();", searchButton);
    }


}