package org.example.steps;

import net.thucydides.core.annotations.Steps;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import org.example.steps.serenity.EndUserSteps;

import java.io.IOException;
import java.net.URISyntaxException;


public class CreatingArticleSteps {

    @Steps
    EndUserSteps endUserSteps;

    @Given("the user is on the Publication page")
    public void givenTheUserIsOnThePublicationPage() {
        endUserSteps.openPublicationPage();
    }

    @When("the user create an article with title '$title', with text '$text', with image and with caption under the image '$captionText'")
    public void whenTheUserCreateAnArticleWith(String title, String text, String captionUnderTheImageText) throws IOException {
        endUserSteps.createArticle(title, text, captionUnderTheImageText);
    }

    @Then("they should see the created YandexZen article with title '$title', with text '$text', with image and with caption under the image '$captionText'")
    public void thenTheyShouldSeeCreatedYandexZenArticleContaining(String title, String text, String captionUnderTheImageText) {
        endUserSteps.should_see_created_article(title, text, captionUnderTheImageText);
    }

}













/*
    @Steps
    EndUserSteps endUser;

    @Given("the user is on the Wikionary home page")
    public void givenTheUserIsOnTheWikionaryHomePage() {
        endUser.is_the_home_page();
    }

    @When("the user looks up the definition of the word '$word'")
    public void whenTheUserLooksUpTheDefinitionOf(String word) {
        endUser.looks_for(word);
    }

    @Then("they should see the definition '$definition'")
    public void thenTheyShouldSeeADefinitionContainingTheWords(String definition) {
        endUser.should_see_definition(definition);
    }
*/
