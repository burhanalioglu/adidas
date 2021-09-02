package com.adidas.pages;

import com.adidas.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class BasePage {

    public BasePage() {
        PageFactory.initElements(Driver.get(), this);
    }

    public void navigateToModule(String moduleName) {
        WebElement moduleToNavigate = Driver.get().findElement(By.partialLinkText(moduleName));
        moduleToNavigate.click();
    }
}
