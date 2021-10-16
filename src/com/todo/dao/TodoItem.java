 package com.todo.dao;

import java.util.Date;
import java.text.SimpleDateFormat;

public class TodoItem {
	private int id;
	private int is_completed;
	private int is_important;
    private String title;
    private String desc;
    private String current_date;
    private String category;
    private String due_date;
    private String difficulty;


    public TodoItem(String title, String desc, String category, String due_date, String difficulty){
        this.title=title;
        this.desc=desc;
        this.category=category;
        this.due_date=due_date;
        SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd kk:mm:ss");
        this.current_date = f.format(new Date());
        this.difficulty=difficulty;
        this.is_completed = 0;
        this.is_important = 0;
    }
    
    public void setId(int id) {
    	this.id = id;
    }
    
    public int getId() {
    	return id;
    }
    
    public void set_is_completed(int num) {
    	this.is_completed = num;
    }
    
    public int get_is_completed() {
    	return is_completed;
    }
    
    public void set_is_important(int num) {
    	this.is_important = num;
    }
    
    public int get_is_important() {
    	return is_important;
    }
    
    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCurrent_date() {
        return current_date;
    }

    public void setCurrent_date(String current_date) {
        this.current_date = current_date;
    }
    
    public String getCategory() {
    	return category;
    }
    
    public void setCategory(String category) {
    	this.category = category;
    }
    
    public String getDue_date() {
    	return due_date;
    }
    
    public void setDue_date(String due_date) {
    	this.due_date = due_date;
    }
    
    @Override
    public String toString() {
    	if(this.is_completed == 0) {
    		if(this.is_important == 0) {
    			return id + ". " + "[" + difficulty + "]" + "[" + category + "] " + title + " - " + desc + " - " + due_date + " - " + current_date;
    		}
    		else {
    			return id + ". " + "[" + difficulty + "]" + "[" + category + "] [⭐]" + title + " - " + desc + " - " + due_date + " - " + current_date;
    		}
    	}
    	else {
    		if(this.is_important == 0) {
    			return id + ". " + "[" + difficulty + "]" + "[" + category + "] " + "[V] " + title + " - " + desc + " - " + due_date + " - " + current_date;
    		}
    		else {
    			return id + ". " + "[" + difficulty + "]" + "[" + category + "] " + "[⭐] [V] " + title + " - " + desc + " - " + due_date + " - " + current_date;
    		}
    	}
    }
}
