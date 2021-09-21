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
		
		String title, desc;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("[추가]\n" + "제목 > ");
		title = sc.next();
		if (list.isDuplicate(title)) {
			System.out.printf("제목 중복 불가!");
			return;
		}
		
		sc.nextLine();
		System.out.println("설명 > ");
		desc = sc.nextLine().trim();
		
		TodoItem t = new TodoItem(title, desc);
		list.addItem(t);
		System.out.println("추가 완료!");
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("[삭제]\n"
				+ "제목 > ");
		
		String title = sc.next();
		
		for (TodoItem item : l.getList()) {
			if (title.equals(item.getTitle())) {
				l.deleteItem(item);
				System.out.println("삭제 완료!");
				break;
			}
		}
	}


	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("[변경]\n"
				+ "기존 제목 > ");
		String title = sc.next().trim();
		if (!l.isDuplicate(title)) {
			System.out.println("일치하는 제목 없음!");
			return;
		}

		System.out.println("새 제목 > ");
		String new_title = sc.next().trim();
		if (l.isDuplicate(new_title)) {
			System.out.println("제목 중복 불가!");
			return;
		}
		sc.nextLine();
		System.out.println("새 설명 > ");
		String new_description = sc.nextLine().trim();
		for (TodoItem item : l.getList()) {
			if (item.getTitle().equals(title)) {
				l.deleteItem(item);
				TodoItem t = new TodoItem(new_title, new_description);
				l.addItem(t);
				System.out.println("변경 완료!");
			}
		}

	}

	public static void listAll(TodoList l) {
		System.out.println("[출력]");
		for (TodoItem item : l.getList()) {
			System.out.println(item.toString());
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
				l.addItem(new TodoItem(st.nextToken(), st.nextToken()));
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
