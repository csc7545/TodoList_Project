package com.todo.dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import com.todo.service.DbConnect;

public class TodoList {
	private List<TodoItem> list;
	Connection conn;

	public TodoList() {
		this.list = new ArrayList<TodoItem>();
		this.conn = DbConnect.getConnection();
	}
	// ADD
	public int addItem(TodoItem t) {
		String sql = "insert into list (title, memo, category, current_date, due_date, difficulty)" + " values (?,?,?,?,?,?);";
		PreparedStatement pstmt;
		int count = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, t.getTitle());
			pstmt.setString(2, t.getDesc());
			pstmt.setString(3, t.getCategory());
			pstmt.setString(4, t.getCurrent_date());
			pstmt.setString(5, t.getDue_date());
			pstmt.setString(6, t.getDifficulty());
			count = pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	// DELETE
	public int deleteItem(int num) {
		String sql = "delete from list where id=?;";
		PreparedStatement pstmt;
		int count = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			count = pstmt.executeUpdate();
			pstmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	// UPDATE
	public int updateItem(TodoItem t) {
		String sql = "update list set title=?, memo=?, category=?, current_date=?, due_date=?, difficulty=?" + " where id = ?;";
		PreparedStatement pstmt;
		int count = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, t.getTitle());
			pstmt.setString(2, t.getDesc());
			pstmt.setString(3, t.getCategory());
			pstmt.setString(4, t.getCurrent_date());
			pstmt.setString(5, t.getDue_date());
			pstmt.setString(6, t.getDifficulty());
			pstmt.setInt(7, t.getId());
			count = pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	// SQL, get list all
	public ArrayList<TodoItem> getList() {
		ArrayList<TodoItem> list = new ArrayList<TodoItem>();
		Statement stmt;
		try {
			stmt = conn.createStatement();
			String sql = "SELECT * FROM list;";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				int id = rs.getInt("id");
				String category = rs.getString("category");
				String title = rs.getString("title");
				String description = rs.getString("memo");
				String due_date = rs.getString("due_date");
				String current_date = rs.getString("current_date");
				String difficulty = rs.getString("difficulty");
				int is_completed = rs.getInt("is_completed");
				int is_important = rs.getInt("is_important");
				
				TodoItem t = new TodoItem(title, description, category, due_date, difficulty);
				t.setId(id);
				t.setCurrent_date(current_date);
				t.set_is_completed(is_completed);
				t.set_is_important(is_important);
				list.add(t);
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	// SQL, get list by KEYWORD
	public ArrayList<TodoItem> getList(String keyword){
		ArrayList<TodoItem> list = new ArrayList<TodoItem>();
		PreparedStatement pstmt;
		keyword = "%" + keyword + "%";
		try {
			String sql = "SELECT * FROM list WHERE title like ? or memo like ?;";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, keyword);
			pstmt.setString(2, keyword);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				String category = rs.getString("category");
				String title = rs.getString("title");
				String description = rs.getString("memo");
				String due_date = rs.getString("due_date");
				String current_date = rs.getString("current_date");
				String difficulty = rs.getString("difficulty");
				int is_completed = rs.getInt("is_completed");
				int is_important = rs.getInt("is_important");
				
				TodoItem t = new TodoItem(title, description, category, due_date, difficulty);
				t.setId(id);
				t.setCurrent_date(current_date);
				t.set_is_completed(is_completed);
				t.set_is_important(is_important);
				list.add(t);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	// SQL, get list by IS_COMPLETED or not
	public ArrayList<TodoItem> getCompletedList(int num){
		ArrayList<TodoItem> list = new ArrayList<TodoItem>();
		PreparedStatement pstmt;
		try {
			String sql = "SELECT * FROM list WHERE is_completed = ?;";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String category = rs.getString("category");
				String title = rs.getString("title");
				String description = rs.getString("memo");
				String due_date = rs.getString("due_date");
				String current_date = rs.getString("current_date");
				String difficulty = rs.getString("difficulty");
				int is_completed = rs.getInt("is_completed");
				int is_important = rs.getInt("is_important");
				
				TodoItem t = new TodoItem(title, description, category, due_date, difficulty);
				t.setId(id);
				t.setCurrent_date(current_date);
				t.set_is_completed(is_completed);
				t.set_is_important(is_important);
				list.add(t);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	// SQL, get list by IS_IMPORTANT or not
	public ArrayList<TodoItem> getImportantList(int num){
		ArrayList<TodoItem> list = new ArrayList<TodoItem>();
		PreparedStatement pstmt;
		try {
			String sql = "SELECT * FROM list WHERE is_important = ?;";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String category = rs.getString("category");
				String title = rs.getString("title");
				String description = rs.getString("memo");
				String due_date = rs.getString("due_date");
				String current_date = rs.getString("current_date");
				String difficulty = rs.getString("difficulty");
				int is_completed = rs.getInt("is_completed");
				int is_important = rs.getInt("is_important");
				
				TodoItem t = new TodoItem(title, description, category, due_date, difficulty);
				t.setId(id);
				t.setCurrent_date(current_date);
				t.set_is_completed(is_completed);
				t.set_is_important(is_important);
				list.add(t);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	// Counting list
	public int getCount() {
		Statement stmt;
		int count = 0;
		try {
			stmt = conn.createStatement();
			String sql = "SELECT count(id) FROM list;";
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			count = rs.getInt("count(id)");
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	// SQL, list ONLY categories
	public ArrayList<String> getCategories(){
		ArrayList<String> list = new ArrayList<String>();
		Statement stmt;
		try {
			stmt = conn.createStatement();
			String sql = "SELECT DISTINCT category FROM category;";
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				String category = rs.getString("category");
				list.add(category);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	// SQL, list the SPECIFIC category
	public ArrayList<TodoItem> getListCategory(String cate){
		ArrayList<TodoItem> list = new ArrayList<TodoItem>();
		PreparedStatement pstmt;
		try {
			String sql = "SELECT * FROM list WHERE category = ?;";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cate);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String category = rs.getString("category");
				String title = rs.getString("title");
				String description = rs.getString("memo");
				String due_date = rs.getString("due_date");
				String current_date = rs.getString("current_date");
				String difficulty = rs.getString("difficulty");
				int is_completed = rs.getInt("is_completed");
				int is_important = rs.getInt("is_important");
				
				TodoItem t = new TodoItem(title, description, category, due_date, difficulty);
				t.setId(id);
				t.setCurrent_date(current_date);
				t.set_is_completed(is_completed);
				t.set_is_important(is_important);
				list.add(t);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	// SQL, order the list by what User choose
	public ArrayList<TodoItem> getOrderedList(String orderby, int ordering){
		ArrayList<TodoItem> list = new ArrayList<TodoItem>();
		Statement stmt;
		try {
			stmt = conn.createStatement();
			String sql = "SELECT * FROM list ORDER BY " + orderby;
			if(ordering == 0)
				sql += " desc";
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String category = rs.getString("category");
				String title = rs.getString("title");
				String description = rs.getString("memo");
				String due_date = rs.getString("due_date");
				String current_date = rs.getString("current_date");
				String difficulty = rs.getString("difficulty");
				int is_completed = rs.getInt("is_completed");
				int is_important = rs.getInt("is_important");
				
				TodoItem t = new TodoItem(title, description, category, due_date, difficulty);
				t.setId(id);
				t.setCurrent_date(current_date);
				t.set_is_completed(is_completed);
				t.set_is_important(is_important);
				list.add(t);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	// Make a record COMPLETED
	public int completeItem(int num) {
		String sql = "update list set is_completed=1" + " where id = ?;";
		PreparedStatement pstmt;
		int count = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			count = pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	// Make a record IMPORTANT
	public int importantItem(int num) {
		String sql = "update list set is_important=1" + " where id = ?;";
		PreparedStatement pstmt;
		int count = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			count = pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	// Check Duplicate
	public Boolean isDuplicate(String title) {
		for (TodoItem item : getList()) {
			if (title.equals(item.getTitle())) return true;
		}
		return false;
	}
	// Return list size
	public int getSize() {
		return list.size();
	}
	//Update category table
	public void updateCategoryTable() {
		String sql = "INSERT INTO category(id, category) SELECT id, category FROM list WHERE id > 0;";
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			int result = pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/*public void importData(String filename) throws IOException, SQLException {
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			String line;
			String sql = "insert into list (title, memo, category, current_date, due_date)" + " values (?,?,?,?,?)";
			
			int records = 0;
			while((line = br.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(line, "##");
				String category = st.nextToken();
				String title = st.nextToken();
				String description = st.nextToken();
				String due_date = st.nextToken();
				String current_date = st.nextToken();

				
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1,title);
				pstmt.setString(2,description);
				pstmt.setString(3,category);
				pstmt.setString(4,current_date);
				pstmt.setString(5,due_date);
				int count = pstmt.executeUpdate();
				if(count>0) records++;
				pstmt.close();
			}
			System.out.println(records + " records read success!!");
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}*/
}
