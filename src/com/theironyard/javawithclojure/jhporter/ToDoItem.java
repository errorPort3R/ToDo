package com.theironyard.javawithclojure.jhporter;

/**
 * Created by jeffryporter on 5/23/16.
 */
public class ToDoItem
{
    private String text;
    private boolean isDone;

    public ToDoItem(String text, boolean isDone)
    {
        this.text = text;
        this.isDone = isDone;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

}
