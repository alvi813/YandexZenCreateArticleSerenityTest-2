package org.example.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.JarURLConnection;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;


public class ArticleCreationPage extends PageObject {

    // окно_с_подсказками:
    @FindBy(xpath = "//span[contains(text(), 'Статья')]")
    private WebElementFacade windowWithSuggestions;

    // кнопка закрытия окна_с_подсказками:
    @FindBy(xpath = "//div[@class='close-cross close-cross_black close-cross_size_s help-popup__close-cross']")
    private WebElementFacade closeButtonForWindowWithSuggestions;

    // окно_с_подсказками_2:
    @FindBy(xpath = "//p[contains(text(), 'Теперь в статью можно вставить виджет Яндекс Форм')]")
    private WebElementFacade windowWithSuggestions2;

    // кнопка закрытия окна_с_подсказками_2:
    @FindBy(xpath = "//div[@class='ui-lib-popup-element__close']")
    private WebElementFacade closeButtonForWindowWithSuggestions2;

    //--------------------------------------------------------------

    // поле ввода заголовка статьи:
    @FindBy(xpath = "//div[@class='editable-input editor__title-input']//div[@class='notranslate public-DraftEditor-content']")
    private WebElementFacade articleTitle;

    // поле ввода текста статьи:
    @FindBy(xpath = "//div[@class='zen-editor']//div[@class='notranslate public-DraftEditor-content']")
    private WebElementFacade articleText;

    // абзац с текстом:
    @FindBy(xpath = "//div[@class='zen-editor-block zen-editor-block-paragraph']")
    private WebElementFacade paragraphText;

    //--------------------------------------------------------------

    // кнопка вставки картинки:
    @FindBy(xpath = "//button[@class='side-button side-button_logo_image ']")
    private WebElementFacade insertingImageButton;

    // ПУСТОЙ элемент под картинкой (для вставки подписи под картинкой):
    @FindBy(xpath = "//figcaption[@class='zen-editor-block-image__caption zen-editor-block-image__caption_empty']")
    private WebElementFacade emptyCaptionElement;

    // ЗАПОЛНЕННЫЙ элемент под картинкой (с подписью к картинке):
    @FindBy(xpath = "//figcaption[@class='zen-editor-block-image__caption']")
    private WebElementFacade completedCaptionElement;

    // картинка:
    @FindBy(xpath = "//img[@class='zen-editor-block-image__image']")
    private WebElementFacade image;

    // загрузка картинки (на картинке появляется надпись "Загружается..."):
    @FindBy(xpath = "//span[@class='zen-editor-block-image__loading']")
    private WebElementFacade loadingImage;

    //--------------------------------------------------------------

    // текстовая надпись "Сохранено":
    @FindBy(xpath = "//div[contains(text(), 'Сохранено')]")
    private WebElementFacade isSavedTextLabel;


    //--------------------------------------------------------------
    //--------------------------------------------------------------



    public void closeWindowWithSuggestions() {
        try {
            // если появится окно с подсказками:
            if (windowWithSuggestions.isVisible()) {
                // закрываем появившееся окно с подсказками:
                closeButtonForWindowWithSuggestions.click();
            }
        } catch (Exception ignored) {
        }
    }

    public void closeWindowWithSuggestions2() {
        try {
            // если появится окно с подсказками:
            if (windowWithSuggestions2.isVisible()) {
                // закрываем появившееся окно с подсказками:
                closeButtonForWindowWithSuggestions2.click();
            }
        } catch (Exception ignored) {
        }
    }


    // ввод заголовка в поле ввода заголовка:
    public void enterArticleTitle(String title) {
        articleTitle.waitUntilVisible();
        articleTitle.sendKeys(title);
    }

    // ввод текста статьи в поле ввода текста:
    public void enterArticleText(String text) {
        articleText.sendKeys(text);
        articleText.sendKeys(Keys.ENTER);
    }

    // нажать на кнопку вставки изображения:
    public void clickInsertingImageButton() {
        insertingImageButton.click();
    }

    // отправить картинку в файловое поле ввода (печатать в поле ввода абсолютный путь к файлу с картинкой):
    public void sendAbsoluteImagePathToFileInputElement() throws IOException {

        // элемент, который является файловым полем ввода (input с типом file) - для вставки картинки:
        WebElementFacade fileInputElement = find(By.xpath("//input[@class='image-popup__file-input']"));

        // работаем с картинкой в jar-файле:
        JarFile jarFile = new JarFile("target/YandexZenSerenityTest-1.0-SNAPSHOT.jar");
        JarEntry jarEntry = new JarEntry("images/image_1.jpeg");
        BufferedImage image = ImageIO.read(jarFile.getInputStream(jarEntry));

        // создадим временный файл:
        File tempFile = File.createTempFile("image", ".jpeg");

        // поместим во временный файл картинку из jar-файла:
        if (image != null) {
            ImageIO.write(image, "jpeg", tempFile);
        }

        fileInputElement.sendKeys(tempFile.getAbsolutePath());
        loadingImage.waitUntilNotVisible();

        // удалим временный файл после того, как программа закончит работу:
        tempFile.deleteOnExit();
    }

    // перейти в элемент под картинкой (для вставки подписи под картинкой) и вставить туда текст:
    public void insertCaptionUnderTheImage(String captionText) {
        emptyCaptionElement.waitUntilVisible();
        emptyCaptionElement.click();
        articleText.sendKeys(captionText);
        articleText.sendKeys(Keys.ENTER);
    }

    // получить текст заголовка:
    public String getArticleTitle() {
        return articleTitle.getText();
    }

    // получить текст абзаца статьи:
    public String getArticleText() {
        return paragraphText.getText();
    }

    // проверить, что на странице присутствует элемент с картинкой:
    public boolean isPageHasImage() {
        return image.isPresent();
    }

    // получить текст подписи под картинкой:
    public String getCaptionUnderTheImageText() {
        return completedCaptionElement.getText();
    }

    // ждать, пока появится текстовая надпись сохранения статьи "Сохранено":
    public void waitForTextLabelIsSavedToAppear() {
        isSavedTextLabel.waitUntilVisible();
    }

}











/*
package org.example.pages;

import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import net.serenitybdd.core.pages.WebElementFacade;
import java.util.stream.Collectors;

import net.serenitybdd.core.annotations.findby.FindBy;

import net.thucydides.core.pages.PageObject;

import java.util.List;

@DefaultUrl("http://en.wiktionary.org/wiki/Wiktionary")
public class DictionaryPage extends PageObject {

    @FindBy(name="search")
    private WebElementFacade searchTerms;

    @FindBy(name="go")
    private WebElementFacade lookupButton;

    public void enter_keywords(String keyword) {
        searchTerms.type(keyword);
    }

    public void lookup_terms() {
        lookupButton.click();
    }

    public List<String> getDefinitions() {
        WebElementFacade definitionList = find(By.tagName("ol"));
        return definitionList.findElements(By.tagName("li")).stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }
}
 */
