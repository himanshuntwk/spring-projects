package com.himanshu.graphqlserver.domain;

public class Person {

    private Integer personId;
    private String personName;
    private PersonAddress personAddress;

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public PersonAddress getPersonAddress() {
        return personAddress;
    }

    public void setPersonAddress(PersonAddress personAddress) {
        this.personAddress = personAddress;
    }

    public Person(Integer personId, String personName, PersonAddress personAddress) {
        this.personId = personId;
        this.personName = personName;
        this.personAddress = personAddress;
    }
}
