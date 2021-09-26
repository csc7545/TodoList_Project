package com.todo.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.*;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {
	
	public static void createItem(TodoList list) {
		
		String title, desc, category, due_date;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("[추가]");
		System.out.println("카테고리 > ");
		category = sc.next();
		System.out.println("제목 > ");
		title = sc.next();
		if (list.isDuplicate(title)) {
			System.out.printf("제목 중복 불가!");
			return;
		}
		
		sc.nextLine();
		System.out.println("설명 > ");
		desc = sc.nextLine().trim();
		System.out.print("마감 > ");
		due_date = sc.nextLine();
		
		TodoItem t = new TodoItem(title, desc, category, due_date);
		list.addItem(t);
		System.out.println("추가 완료!");
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("[삭제]");
		System.out.println("번호 > ");
		
		int num = sc.nextInt();
		
		if (num<=0 || num > l.getSize()) {
			System.out.printf("범위 초과!");
			return;
		}
		
		l.deleteItem(num-1);
		System.out.println("삭제 완료!");
	}


	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("[변경]");
		System.out.println("번호 > ");
		int num = sc.nextInt();
		if (num<=0 || num > l.getSize()) {
			System.out.printf("범위 초과!");
			return;
		}
		
		l.deleteItem(num-1);
		System.out.print("새 카테고리 > ");
		String new_category = sc.next().trim();
		System.out.println("새 제목 > ");
		String new_title = sc.next().trim();
		
		if (l.isDuplicate(new_title)) {
			System.out.println("제목 중복 불가!");
			return;
		}
		
		System.out.println("새 설명 > ");
		sc.nextLine();
		String new_description = sc.nextLine().trim();
		System.out.print("새 마감일자 > ");
		String new_duedate = sc.nextLine().trim();
		
		TodoItem t = new TodoItem(new_category, new_title, new_description, new_duedate);
		l.addItem(t);
		System.out.println("변경 완료!");

	}

	public static void listAll(TodoList l) {
		System.out.println("[전체 출력, 총 " + l.getSize() + "개]");
		for (TodoItem item : l.getList()) {
			System.out.println(l.indexOf(item) + 1 +  ". " + "[" + item.getCategory() + "] " + item.getTitle() + " : " + item.getDesc() + " - " + item.getDue_date() + " <" + item.getCurrent_date() + "> ");
		}
	}
	
	public static void saveList(TodoList l, String filename) {
		try {
			Writer w = new FileWriter("todolist.txt");
			for(TodoItem t : l.getList()) {
				w.write(t.toSaveString());
			}
			w.close();
			System.out.println("저장!");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void loadList(TodoList l, String filename) {
		try {
			BufferedReader br = new BufferedReader(new FileReader("todolist.txt"));
			String oneline;
			int count = 0;
			
			while((oneline = br.readLine()) != null) {
				StringTokenizer st = new StringTokenizer (oneline, "##");
				count++;
				l.addItem(new TodoItem(st.nextToken(), st.nextToken(), st.nextToken(), st.nextToken()));
			}
			
			System.out.println(count + "개 로딩 완료!");
			br.close();
		} catch (FileNotFoundException e) {
			System.out.println("파일 없음!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
