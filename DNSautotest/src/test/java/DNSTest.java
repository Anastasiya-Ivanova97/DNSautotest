import org.junit.Before;
import org.junit.Test;

public class DNSTest {
    @Before
    public void start() {
        Initialization.initialize();
    }
    @Test
    public void dnsTest()  {
        StartPage startPage = new StartPage();
      
        startPage.search("playstation");

        SearchResultPage searchResultPage = new SearchResultPage();
        searchResultPage.gotoItemPage("playstation slim 4 black");

        ItemPage itemPage = new ItemPage();
        itemPage.setBefore();
        itemPage.changeGuarantee();
        itemPage.setAfter();
        itemPage.buyAndSearchAnotherItem("detroit");
        itemPage.setNewItem();
        itemPage.buy();
        itemPage.checkTotalPrice();
        itemPage.goToBasket();
        BasketPage basketPage = new BasketPage();
        basketPage.checkGuarantee();
        basketPage.checkSum(itemPage);
        basketPage.removeItem();
        basketPage.checkAfterRemove(itemPage);
        basketPage.addItems(3);
        basketPage.checkNew(itemPage);
        basketPage.takeBack(itemPage);

    }
}
