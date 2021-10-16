package com.todo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

import com.todo.dao.TodoList;
import com.todo.menu.Menu;
import com.todo.service.TodoUtil;

public class TodoMain {
	
	public static void start() throws IOException, SQLException {
	
		Scanner sc = new Scanner(System.in);
		TodoList l = new TodoList();
		boolean isList = false;
		boolean quit = false;
		//l.importData("todolist.txt");
		Menu.displaymenu();
		do {
			Menu.prompt();
			isList = false;
			String choice = sc.next();
			String key = sc.nextLine().trim();
			switch (choice) {

			case "add":
				TodoUtil.createItem(l);
				break;
			
			case "del":
				TodoUtil.deleteItem(l);
				break;
				
			case "edit":
				TodoUtil.updateItem(l);
				break;
				
			case "ls":
				TodoUtil.listAll(l);
				break;
		        
			case "ls_name":
		        System.out.println("제목순 출력 완료.");
		        TodoUtil.listAllOrdered(l, "title", 1);
		        break;

		    case "ls_name_desc":
		        System.out.println("제목역순 출력 완료.");
			    TodoUtil.listAllOrdered(l, "title", 0);
			    break;
		            
		    case "ls_date":
		    	System.out.println("날짜순 출력 완료.");
			    TodoUtil.listAllOrdered(l, "due_date", 1);
			    break;
		           
		    case "ls_date_desc":
		    	System.out.println("날짜역순 출력 완료.");
			    TodoUtil.listAllOrdered(l, "due_date", 0);
			    break;
			    
	        case "find":
		        String keyword = sc.nextLine().trim();
		        TodoUtil.findList(l, keyword);
		        break;
		        	
	        case "find_cate":
		        String cate = sc.nextLine().trim();
		        TodoUtil.findCateList(l, cate);
		        break;
		        	
		    case "ls_cate":
				TodoUtil.listCateAll(l);
				    break;
				    
		    case "ls_comp":
	        	TodoUtil.listAllCompleted(l,1);
	        	break;
	         
	        case "complete":
	        	System.out.print("[완료] ['/'로 구분]\n" + "번호 > ");
	        	String multi = sc.nextLine();
	        	String[] number = multi.split("/");
	        	TodoUtil.completeItem(l, number);
	        	break;
	        	
	        case "ls_important":
	        	TodoUtil.listAllImportant(l,1);
	        	break;
	        	
	        case "important":
	        	System.out.print("[중요] ['/'로 구분]\n" + "번호 > ");
	        	multi = sc.nextLine();
	        	number = multi.split("/");
	        	TodoUtil.importantItem(l, number);
	        	break;
	        	
	        case "productivity":
	         	TodoUtil.visualProductivity(l);
	         	break;
	         
	        case "saveJson":
	        	TodoUtil.saveJson(l);
	        	break;
	        	
	        case "loadJson":
	        	TodoUtil.loadJson(l);
	        	break;
				
			case "help":
				Menu.displaymenu();
				break;

			case "exit":
	        	System.out.println("종료됨!");
				quit = true;
				break;

			default:
				System.out.println("해당 명령 없음 ( help )");
				break;
			}
			
			if(isList) TodoUtil.listAll(l);
		} while (!quit);
	}
}
