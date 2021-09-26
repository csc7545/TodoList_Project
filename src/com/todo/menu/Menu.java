package com.todo.menu;
public class Menu {

    public static void displaymenu()
    {
        System.out.println();
        System.out.println("1. 추가 ( add )");
        System.out.println("2. 삭제 ( del )");
        System.out.println("3. 변경 ( edit )");
        System.out.println("4. 출력 ( ls )");
        System.out.println("5. 제목순 출력 ( ls_name_asc )");
        System.out.println("6. 제목 역순 출력 ( ls_name_desc )");
        System.out.println("7. 날짜순 출력 ( ls_date )");
        System.out.println("8. 날짜순 역순 출력 ( ls_date_desc )");
        System.out.println("9. 제목 검색 ( find_title )");
        System.out.println("10. 내용 검색> ( find_desc )");
        System.out.println("11. 카테고리 검색 ( find_cate )");
        System.out.println("12. 카테고리 목록 ( ls_cate )");
        System.out.println("13. 종료 ( exit )");
    }
    
    public static void prompt() {
    	System.out.println("\nCommand > ");
    }
}