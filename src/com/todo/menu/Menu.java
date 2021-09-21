package com.todo.menu;
public class Menu {

    public static void displaymenu()
    {
        System.out.println();
        System.out.println("1. 추가 ( add )");
        System.out.println("2. 삭제 ( del )");
        System.out.println("3. 변경 ( edit )");
        System.out.println("4. 출력 ( ls )");
        System.out.println("5. 제목순 출력 ( ls_asc )");
        System.out.println("6. 제목 역순 출력 ( ls_desc )");
        System.out.println("7. 날짜순 출력 ( ls_date )");
        System.out.println("8. 종료 ( exit )");
    }
    
    public static void prompt() {
    	System.out.println("\nCommand > ");
    }
}