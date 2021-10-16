package com.todo.menu;
public class Menu {

    public static void displaymenu()
    {
        System.out.println();
        System.out.println("1. 추가 ( add )");
        System.out.println("2. 삭제 ( del )");
        System.out.println("3. 변경 ( edit )");
        System.out.println("4. 출력 ( ls )");
        System.out.println("5. 제목순 출력 ( ls_name )");
        System.out.println("6. 제목 역순 출력 ( ls_name_desc )");
        System.out.println("7. 날짜순 출력 ( ls_date )");
        System.out.println("8. 날짜순 역순 출력 ( ls_date_desc )");
        System.out.println("9. 제목 및 내용 검색 ( find Enter XXX )");
        System.out.println("10. 카테고리 검색 ( find_cate Enter XXX )");
        System.out.println("11. 카테고리 목록 ( ls_cate )");
        System.out.println("12. 항목 완료 ( complete Enter MULTI-숫자 )");
        System.out.println("13. 완료된 항목 출력 ( ls_comp )");
        System.out.println("14. 중요한 항목 ( important Enter MULTI-숫자 )");
        System.out.println("15. 중요한 항목 출력 ( ls_important )");
        System.out.println("16. 생산성 시각화 ( productivity )");
        System.out.println("17. Json 저장 ( save )");
        System.out.println("18. Json 로딩 ( load )");
        System.out.println("19. 종료 ( exit )");
    }
    
    public static void prompt() {
    	System.out.println("\nCommand > ");
    }
}