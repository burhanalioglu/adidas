package com.adidas.pages;

import com.adidas.utilities.Driver;
import org.openqa.selenium.By;

public class MainPage extends BasePage {
    public void clickOnCategory(String categoryType) {
        Driver.get().findElement(By.linkText(categoryType)).click();
    }

    public void clickOnProduct(String productName) {
        Driver.get().findElement(By.linkText(productName)).click();
    }
}
