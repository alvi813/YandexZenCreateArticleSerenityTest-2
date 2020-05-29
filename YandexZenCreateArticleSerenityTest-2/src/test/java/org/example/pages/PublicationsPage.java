package org.example.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;


@DefaultUrl("https://zen.yandex.ru/profile/editor/id/5ea219f9b6e0833eb9eeaa9f")

public class PublicationsPage extends PageObject {

    // кнопка "+":
    @FindBy(className = "new-publication-dropdown__add-button")
    private WebElementFacade newPublicationButton;

    // кнопка "Статья":
    @FindBy(className = "new-publication-dropdown__button-text")
    private WebElementFacade newTextPublicationButton;


    //--------------------------------------------------------------
    //--------------------------------------------------------------



    // ждать, пока появится кнопка "+", нажать на кнопку "+":
    public void clickNewPublicationButton() {
        newPublicationButton.waitUntilVisible();
        newPublicationButton.click();
    }

    // ждать, пока появится кнопка "статья", инажать на кнопку "статья":
    public void clickNewTextPublicationButton() {
        newTextPublicationButton.waitUntilVisible();
        newTextPublicationButton.click();
    }

}
