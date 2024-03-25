package com.example.application.views.main;

import com.example.application.service.AIService;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Main")
@Route(value = "")
public class MainView extends VerticalLayout {

  private final AIService aiService;
  private TextArea searchResultArea = new TextArea();
  private TextArea genResultArea = new TextArea();

  public MainView(AIService aiService) {
    this.aiService = aiService;
    setMargin(true);
    setHorizontalComponentAlignment(Alignment.START);
    add(uploadArea(), searchArea(), resultArea());
  }


  private HorizontalLayout uploadArea() {
    HorizontalLayout uploadLayout = new HorizontalLayout(Alignment.START);
    TextArea dataText = new TextArea();
    Button submitButton = new Button("Submit");

    dataText.setPlaceholder("Enter data to upload");
    dataText.setWidth("50vw");
    dataText.setHeight("20vw");
    submitButton.addClickListener(e -> aiService.addData(dataText.getValue()));

    uploadLayout.add(dataText, submitButton);
    return uploadLayout;
  }

  private HorizontalLayout searchArea() {
    HorizontalLayout searchLayout = new HorizontalLayout(Alignment.START);
    TextField searchField = new TextField();
    searchField.setPlaceholder("Enter your search query");
    searchField.setWidth("50vw");
    Button searchButton = new Button("Search");

    searchButton.addClickListener(e -> {
      String result = aiService.searchData(searchField.getValue());
      searchResultArea.setValue(result);
    });

    Button summaryButton = new Button("Summary");
    summaryButton.addClickListener(e -> {
      String genResult = aiService.summarizeContent(searchResultArea.getValue());
      genResultArea.setValue(genResult);
    });
    searchLayout.add(searchField, searchButton, summaryButton);
    return searchLayout;
  }

  private HorizontalLayout resultArea() {
    HorizontalLayout resultLayout = new HorizontalLayout(Alignment.START);

    searchResultArea.setPlaceholder("Your search result will appear here");
    searchResultArea.setWidth("35vw");
    searchResultArea.setHeight("20vw");

    genResultArea.setPlaceholder("Your AI gen result will appear here");
    genResultArea.setWidth("35vw");
    genResultArea.setHeight("20vw");


    resultLayout.add(searchResultArea, genResultArea);
    return resultLayout;
  }

}
