package com.adidas.step_defs;

import com.adidas.pages.BasePage;
import com.adidas.pages.CartPage;
import com.adidas.pages.MainPage;
import com.adidas.pages.ProductionDetailPage;
import com.adidas.utilities.BrowserUtils;
import com.adidas.utilities.ConfigurationReader;
import com.adidas.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class purchaseStepDefs {

    MainPage mainPage = new MainPage();
    ProductionDetailPage productionDetailPage = new ProductionDetailPage();
    BasePage basePage = new BasePage();
    CartPage cartPage = new CartPage();
    WebDriverWait wait = new WebDriverWait(Driver.get(), 3);

    @Given("The user is on the main page")
    public void the_user_is_on_the_main_page() {
        String mainPageUrl = ConfigurationReader.get("url");
        Driver.get().get(mainPageUrl);
    }

    @When("The user selects {string} as category")
    public void the_user_selects_as_category(String categoryType) {
        mainPage.clickOnCategory(categoryType);
    }

    @When("The user clicks on {string} in Main page")
    public void the_user_clicks_on_in_Main_page(String productName) {
        mainPage.clickOnProduct(productName);
    }

    @Then("The user should be on {string} detail page")
    public void the_user_should_be_on_detail_page(String expectedProductName) {
        BrowserUtils.waitForVisibility(productionDetailPage.productContent, 2);
        String actualProductName = productionDetailPage.getProductNameText();
        Assert.assertEquals(expectedProductName, actualProductName);
    }

    @When("The user adds the product to cart")
    public void the_user_adds_the_product_to_cart() {
        productionDetailPage.addToCart();
        BrowserUtils.waitUntilAlertIsDisplayed(3);
        Driver.get().switchTo().alert().accept();
    }

    @When("The user navigates to {string}")
    public void the_user_navigates_to(String moduleToNavigate) {
        basePage.navigateToModule(moduleToNavigate);
    }

    @Then("The user should see {string} on the list")
    public void the_user_should_see_on_the_list(String productName) {
        BrowserUtils.waitForVisibility(cartPage.getProductElementList().get(0), 3);
        Assert.assertTrue(cartPage.getProductStringList().contains(productName));
    }

    @When("The user deletes {string} from cart")
    public void the_user_deletes_from_cart(String productToDelete) {
        cartPage.deleteProduct(productToDelete);
    }

    @Then("The user should not see {string} on the list")
    public void the_user_should_not_see_on_the_list(String productName) {
        Assert.assertFalse(cartPage.getProductStringList().contains(productName));
    }

    @When("The user clicks on Place Order in Cart page")
    public void the_user_clicks_on_Place_Order_in_Cart_page() {
        cartPage.clickOnPlaceOrder();
    }

    @When("The user fills out fields")
    public void the_user_fills_out_fields() {
        cartPage.fillOutFields();

    }

    @When("The user clicks on Purchase in Purchase Detail page")
    public void the_user_clicks_on_Purchase_in_Purchase_Detail_page() {
        cartPage.clickOnPurchase();
    }

    @Then("Thank you for your purchase message should be shown")
    public void purchase_alert_should_be_shown() {
        String expectedMessage = "Thank you for your purchase!";
        String actualMessage = cartPage.getPurchaseMessage();
        Assert.assertEquals(expectedMessage, actualMessage);
    }

    @Then("Bill amount should match with expected amount")
    public void bill_amount_should_match_with_expected_amount() {
        System.out.println("PurchaseDetail = \n" + cartPage.getPurchaseDetail());
        System.out.println("PurchaseID = " + cartPage.getPurchaseID());
        System.out.println("PurchaseAmount = " + cartPage.getPurchaseAmount());
        int expectedAmount = cartPage.getExpectedPrice();
        int actualAmount = cartPage.getPurchaseAmount();
        Assert.assertEquals(expectedAmount, actualAmount);
    }

    @When("The user clicks on OK in Purchase Detail page")
    public void the_user_clicks_on_OK_in_Purchase_Detail_page() {
        cartPage.clickOnOK();
    }

}
