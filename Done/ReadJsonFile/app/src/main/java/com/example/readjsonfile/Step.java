package com.example.readjsonfile;

public class Step {
    private String id;
    private String name;
    private String task_id;
    private String intent_names;
    private String order;

    public Step(String id, String name, String task_id, String intent_names, String order) {
        this.id = id;
        this.name = name;
        this.task_id = task_id;
        this.intent_names = intent_names;
        this.order = order;
    }

    public Step() {

    }

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

    public String getTask_id() {
        return task_id;
    }

    public void setTask_id(String task_id) {
        this.task_id = task_id;
    }

    public String getIntent_names() {
        return intent_names;
    }

    public void setIntent_names(String intent_names) {
        this.intent_names = intent_names;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }
}



