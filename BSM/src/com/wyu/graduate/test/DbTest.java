package com.wyu.graduate.test;

import java.sql.Connection;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.wyu.graduate.bean.ChatMessage;
import com.wyu.graduate.bean.ChatPeople;
import com.wyu.graduate.bean.People;
import com.wyu.graduate.bean.ReplyMessage;
import com.wyu.graduate.bean.Student;
import com.wyu.graduate.bean.Teacher;
import com.wyu.graduate.bean.TeacherMessages;
import com.wyu.graduate.bean.Topic;
import com.wyu.graduate.bean.TopicNumber;
import com.wyu.graduate.dao.StudentManager;
import com.wyu.graduate.db.SqliteUtil;
import com.wyu.graduate.db.impl.SqliteConn;
import com.wyu.graduate.util.GsonUtil;

public class DbTest {

	@Test
	public void testCreateTable() throws Exception{
		
		SqliteConn sqliteConn = new SqliteConn();
		Connection conn = sqliteConn.openConection();
		
//		SqliteUtil.createTable(conn, "student", Student.class);
		SqliteUtil.createTable(conn, "teacher", Teacher.class);
//		SqliteUtil.createTable(conn, "people", People.class);
		
//		SqliteUtil.createTable(conn, "topicNumber", TopicNumber.class);
//		SqliteUtil.createTable(conn, "topic", Topic.class);
//		SqliteUtil.createTable(conn, "replyMessage", ReplyMessage.class);
//		SqliteUtil.createTable(conn, "chatmessage", ChatMessage.class);
//		SqliteUtil.createTable(conn, "chatpeople", ChatPeople.class);
//		SqliteUtil.createTable(conn, "teacherMessages", TeacherMessages.class);
	}
	
	
	@Test
	public void testInesrt() throws Exception{
		
		SqliteConn sqliteConn = new SqliteConn();
		Connection conn = sqliteConn.openConection();
		
		for (int i = 3; i < 7; i++) {
			Student student = new Student();
			student.setName("xxxx");
			
			SqliteUtil.insert(conn, "student",student ,Student.class);
			
		}
		

		
	}
	
	
	@Test
	public void testDelete() throws Exception{
		
		SqliteConn sqliteConn = new SqliteConn();
		Connection conn = sqliteConn.openConection();
		
		boolean ret = SqliteUtil.delete(conn, "student", 2);
		System.out.println(ret);
		
	}
	
	@Test
	public void testFind() throws Exception{
		
		SqliteConn sqliteConn = new SqliteConn();
		Connection conn = sqliteConn.openConection();
		
		Student ret = SqliteUtil.find(conn, "student",Student.class, 1);
		System.out.println(ret);
		
	}
	
	
	@Test
	public void testFindAll() throws Exception{
		
		SqliteConn sqliteConn = new SqliteConn();
		Connection conn = sqliteConn.openConection();
		
		List<Student> ret = SqliteUtil.findAll(conn, "student",Student.class, 0,8);
		System.out.println(Arrays.toString(ret.toArray()));
		
	}
	
	
	@Test
	public void testFindCount() throws Exception{
		
		SqliteConn sqliteConn = new SqliteConn();
		Connection conn = sqliteConn.openConection();
		
		long ret = SqliteUtil.count(conn, "student");
		System.out.println(ret);
		
	}
	
	
	@Test
	public void testFindUpdate() throws Exception{
//		
//		SqliteConn sqliteConn = new SqliteConn();
//		Connection conn = sqliteConn.openConection();
//		Student student = new Student(0,"aaaaaaaaaaxxxxaaaaaaa","bbbbbbbbbb");
//		boolean ret = SqliteUtil.update(conn, "student",Student.class, 1, student);
//		System.out.println(ret);
		
	}
	
	
	@Test 
	public void testgetStudentbySelection() throws Exception{
		StudentManager manager = new StudentManager();
		List<Student> students = manager.findBySelection(Student.class,"teacherUser" ,"0717654321");
		System.out.println(Arrays.toString(students.toArray()));
		String json = GsonUtil.Obj2Json(students);
		System.out.println(json);
	}

	
	
}
