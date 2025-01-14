package com.himanshu.modulith_demo.module1.exposed;

import org.springframework.modulith.events.Externalized;

@Externalized(target = "test-topic")
public class TestEvent {
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String id;
    private String name;

    public TestEvent(String id, String name) {
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
