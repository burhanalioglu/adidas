package com.adidas.pages;

import com.adidas.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Instant;

public class ProductionDetailPage extends BasePage {
    @FindBy(css = ".product-content")
    public WebElement productContent;

    @FindBy(css = ".name")
    private WebElement productName;

    @FindBy(linkText = "Add to cart")
    private WebElement addToCart;

    public String getProductNameText() {
        return productName.getText();
    }

    public void addToCart() {
        addToCart.click();
    }
}
