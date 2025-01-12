package com.himanshu.modulith_demo.module1.exposed;

public class TestEvent {
    int id;
    String name;

    public TestEvent(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "TestEvent{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
