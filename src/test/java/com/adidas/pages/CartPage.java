package com.adidas.pages;

import com.adidas.utilities.BrowserUtils;
import com.github.javafaker.Faker;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class CartPage extends BasePage {

    @FindBy(xpath = "//thead/tr/th")
    private List<WebElement> headerList;

    @FindBy(xpath = "//tbody/tr/td[3]")
    private List<WebElement> productPriceList;

    @FindBy(xpath = "//tbody/tr/td[4]/a")
    private List<WebElement> deleteElementList;

    @FindBy(xpath = "//tbody/tr/td[2]")
    private List<WebElement> productTitleList;

    @FindBy(xpath = "//button[.='Place Order']")
    private WebElement placeOrderButton;

    @FindBy(id = "name")
    private WebElement nameOfPlaceOrder;

    @FindBy(id = "country")
    private WebElement countryOfPlaceOrder;

    @FindBy(id = "city")
    private WebElement cityOfPlaceOrder;

    @FindBy(id = "card")
    private WebElement creditCardOfPlaceOrder;

    @FindBy(id = "month")
    private WebElement monthOfPlaceOrder;

    @FindBy(id = "year")
    private WebElement yearOfPlaceOrder;

    @FindBy(xpath = "//button[.='Purchase']")
    private WebElement purchaseOfPlaceOrder;

    @FindBy(css = ".sweet-alert")
    private WebElement purchaseAlert;

    @FindBy(xpath = "//div[@class='sweet-alert  showSweetAlert visible']/h2")
    private WebElement purchaseMessage;

    @FindBy(id = "totalp")
    private WebElement expectedTotalPrice;

    @FindBy(css = ".lead.text-muted")
    private WebElement purchaseDetail;

    @FindBy (xpath = "//button[.='OK']")
    private WebElement okOfPlaceOrder;


    public List<String> getProductStringList() {
        List<String> productListAsString = new ArrayList<>();
        for (WebElement eachWebElement : productTitleList) {
            productListAsString.add(eachWebElement.getText());
        }
        return productListAsString;
    }

    public List<WebElement> getProductElementList() {
        return productTitleList;
    }

    public void deleteProduct(String productName) {

        for (int i = 0; i < productTitleList.size(); i++) {
            if (productTitleList.get(i).getText().equals(productName)) {
                deleteElementList.get(i).click();
                BrowserUtils.waitForInvisibility(productTitleList.get(i), 2);
                break;
            }
        }
    }

    public void clickOnPlaceOrder() {
        placeOrderButton.click();
        BrowserUtils.waitForVisibility(nameOfPlaceOrder, 2);
    }

    public void fillOutFields() {
        Faker faker = new Faker();
        String name = faker.name().fullName();
        String country = faker.country().name();
        String city = faker.address().cityName();
        String creditCard = faker.business().creditCardNumber();
        int month = faker.number().numberBetween(0, 12);
        int year = faker.number().numberBetween(2020, 2025);

        nameOfPlaceOrder.sendKeys(name);
        countryOfPlaceOrder.sendKeys(country);
        cityOfPlaceOrder.sendKeys(city);
        creditCardOfPlaceOrder.sendKeys(creditCard);
        monthOfPlaceOrder.sendKeys("" + month);
        yearOfPlaceOrder.sendKeys("" + year);
    }

    public void clickOnPurchase() {
        purchaseOfPlaceOrder.click();
        BrowserUtils.waitForVisibility(purchaseAlert, 2);
    }

    public String getPurchaseMessage() {
        return purchaseMessage.getText();
    }

    public int getExpectedPrice() {
        return Integer.parseInt(expectedTotalPrice.getText());
    }

    public String getPurchaseDetail() {
        return purchaseDetail.getText();
    }

    /**
     * first split for dividing ID, Amount, CardNumber, Name and Date
     * Second split for getting ID itself..
     */
    public String getPurchaseID() {
        String[] purchaseDetailArr = getPurchaseDetail().split("\n");
        return purchaseDetailArr[0].split(" ")[1];
    }

    /**
     * first split for dividing ID, Amount, CardNumber, Name and Date
     * Second split for getting Amount itself..
     */
    public int getPurchaseAmount() {
        String[] purchaseDetailArr = getPurchaseDetail().split("\n");
        String purchaseAmountStr = purchaseDetailArr[1].split(" ")[1];
        return Integer.parseInt(purchaseAmountStr);
    }

    public void clickOnOK() {
        okOfPlaceOrder.click();
    }
}
