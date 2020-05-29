package org.example.steps.serenity;

import org.example.pages.ArticleCreationPage;
import net.thucydides.core.annotations.Step;
import org.example.pages.PublicationsPage;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class EndUserSteps {


    ArticleCreationPage articleCreationPage;
    PublicationsPage publicationsPage;



    // открыть страницу "Публикации":
    @Step
    public void openPublicationPage() {
        publicationsPage.open();
    }

    // Перейти на пустую страницу создания статьи:
    @Step
    public void goToBlankCreatingArticlePage() {
        publicationsPage.clickNewPublicationButton();
        publicationsPage.clickNewTextPublicationButton();
    }

    // Ввести заголовок, текст абзаца, вставить картинку и подпись под картинкой:
    @Step
    public void fillInCreatingArticlePage(String title, String text, String captionText) throws IOException {
        articleCreationPage.enterArticleTitle(title);
        articleCreationPage.enterArticleText(text);
        articleCreationPage.clickInsertingImageButton();
        articleCreationPage.sendAbsoluteImagePathToFileInputElement();
        articleCreationPage.insertCaptionUnderTheImage(captionText);
        articleCreationPage.waitForTextLabelIsSavedToAppear();
    }


    // шаги создания статьи - перейти на пустую страницу создания статьи, пропустить подсказки и заполнить поля статьи:
    @Step
    public void createArticle(String title, String text, String captionUnderTheImageText) throws IOException {
        goToBlankCreatingArticlePage();
        articleCreationPage.closeWindowWithSuggestions();
        articleCreationPage.closeWindowWithSuggestions2();
        fillInCreatingArticlePage(title, text, captionUnderTheImageText);
    }


    // пользователь должен увидеть заголовок, текст абзаца, наличие картинки и текст подписи под картинкой:
    @Step
    public void should_see_created_article(String title, String text, String captionUnderTheImageText) {
        assertThat(articleCreationPage.getArticleTitle(), containsString(title));
        assertThat(articleCreationPage.getArticleText(), containsString(text));
        assertThat(articleCreationPage.isPageHasImage(), equalTo(true));
        assertThat(articleCreationPage.getCaptionUnderTheImageText(), containsString(captionUnderTheImageText));
    }

}













/*
package org.example.steps.serenity;

import org.example.pages.DictionaryPage;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasItem;

public class EndUserSteps {

    DictionaryPage dictionaryPage;

    @Step
    public void enters(String keyword) {
        dictionaryPage.enter_keywords(keyword);
    }

    @Step
    public void starts_search() {
        dictionaryPage.lookup_terms();
    }

    @Step
    public void should_see_definition(String definition) {
        assertThat(dictionaryPage.getDefinitions(), hasItem(containsString(definition)));
    }

    @Step
    public void is_the_home_page() {
        dictionaryPage.open();
    }

    @Step
    public void looks_for(String term) {
        enters(term);
        starts_search();
    }
}
 */