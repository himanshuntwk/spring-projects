package com.himanshu.phonebook.views;

import com.himanshu.phonebook.model.Person;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.dataview.GridListDataView;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

import java.util.ArrayList;
import java.util.List;

@Route("")
public class PhoneView extends VerticalLayout {

  static List<Person> personList = new ArrayList<>();
  static GridListDataView<Person> personGridListDataView;

  static {
    Person himanshu = new Person("Himanshu", "Sharma", "123456789", "987654321");
    Person john = new Person("John", "Doe", "123445789", "900654321");
    Person charles = new Person("Charles", "Burn", "123456712", "987653921");
    Person kris = new Person("Kris", "Traught", "223456789", "983754321");
    personList.add(himanshu);
    personList.add(john);
    personList.add(charles);
    personList.add(kris);
  }

  public PhoneView() {
    Grid<Person> grid = new Grid<>(Person.class);
    grid.addColumn(Person::firstName).setHeader("First Name");
    grid.addColumn(Person::lastName).setHeader("Last Name");
    grid.addColumn(Person::work).setHeader("Work");
    grid.addColumn(Person::home).setHeader("Home");
    personGridListDataView = grid.setItems(personList);

    TextField searchField = new TextField();
    searchField.setPlaceholder("Search");
    searchField.setWidth("50%");
    searchField.setValueChangeMode(ValueChangeMode.EAGER);
    searchField.addValueChangeListener(e -> personGridListDataView.refreshAll());

    personGridListDataView.addFilter(person -> {
      String search = searchField.getValue();
      return (matchTerm(person.firstName(), search) || matchTerm(person.lastName(), search));
    });

    add(addPersonForm(), searchField, grid);

  }

  private static boolean matchTerm(String column, String search) {
    return (column.toLowerCase().contains(search.toLowerCase()));
  }

  private static FormLayout addPersonForm() {
    FormLayout formLayout = new FormLayout();
    TextField firstName = new TextField("First Name");
    TextField lastName = new TextField("Last Name");
    TextField home = new TextField("Home Phone");
    TextField work = new TextField("Work Phone");
    Button addButton = new Button("Add");

    addButton.addClickListener(e -> {
      if(firstName.isEmpty() || (home.isEmpty() && work.isEmpty())) {
        Notification.show("Kindly enter First name and one phone number");
        return;
      }
      Person newPerson = new Person(firstName.getValue(), lastName.getValue(), home.getValue(), work.getValue());
      personList.add(newPerson);
      personGridListDataView.refreshAll();
    });

    Button clearButton = new Button("Clear");
    clearButton.addClickListener(e -> {
      firstName.clear();
      lastName.clear();
      work.clear();
      home.clear();
    });

    formLayout.add(firstName, lastName, home, work, addButton, clearButton);
    return formLayout;
  }
}
