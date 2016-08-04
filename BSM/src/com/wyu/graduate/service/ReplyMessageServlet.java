package com.wyu.graduate.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wyu.graduate.bean.ReplyMessage;
import com.wyu.graduate.bean.Student;
import com.wyu.graduate.bean.Teacher;
import com.wyu.graduate.bean.TeacherMessages;
import com.wyu.graduate.bean.TopicNumber;
import com.wyu.graduate.dao.ReplyMessageManager;
import com.wyu.graduate.dao.StudentManager;
import com.wyu.graduate.dao.TeacherManager;
import com.wyu.graduate.dao.TeacherMessagesManager;
import com.wyu.graduate.dao.TopicNumberManager;

@WebServlet("/ReplyMessageServlet")
public class ReplyMessageServlet extends BaseServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doWork(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("replyMessage");
		
		String userString = request.getParameter("studentUser");
		String teacherString = request.getParameter("teacherUser");
		String imageString  = request.getParameter("imageUrl");
		String nameString = request.getParameter("name");
		String reasonString = request.getParameter("reason");
		String greenString = request.getParameter("green");
		
		/**
		 * 删除老师的消息
		 */
		TeacherMessagesManager teacherManager = new TeacherMessagesManager();
		TeacherMessages teacher = teacherManager.find(TeacherMessages.class, userString);
		int id = teacher.getId();
		int r = teacherManager.delete(id);
		System.out.println("delete" + r);
		
//		System.out.println(userString + teacherString + nameString + reasonString + greenString);
		/**
		 * 添加回复信息
		 */
		String topic = teacher.getTopic();
		ReplyMessageManager manager = new ReplyMessageManager();
		ReplyMessage replyMessage = new ReplyMessage(userString, teacherString, reasonString, imageString, greenString, nameString, topic);
		int ret = manager.insert(ReplyMessage.class, replyMessage);
		System.out.println(ret);
		
		/**
		 * 判断是否同意
		 */
		if (greenString.equals("no"))  return;
		
		/**
		 * 更改老师可带人数
		 */
		TopicNumberManager topicNumberManager = new TopicNumberManager();
		TopicNumber topicNumber = topicNumberManager.find(TopicNumber.class, teacherString);
		String haveString = topicNumber.getHavedNum();
		if (haveString.equals("null")) {
			haveString = "0";
		}
		int haveNum = Integer.parseInt(haveString) + 1;
		haveString = String.valueOf(haveNum);
		topicNumber.setHavedNum(haveString);
		int topid = topicNumber.getId();
		topicNumberManager.update(TopicNumber.class, topid, topicNumber);
		
		/**
		 * 添加已带学生
		 */
		String studentName = teacher.getName();
		String imageUrl = teacher.getImageUrl();
		String profession = teacher.getProfession();
		StudentManager studentManager = new StudentManager();
		Student student = new Student(studentName, userString, teacherString, topic, imageUrl, profession);
		int re = studentManager.insert(Student.class, student);
		System.out.println(re);
		
		/**
		 * 添加已选毕业老师
		 */
		TeacherManager teaManager = new TeacherManager();
		Teacher tea = teaManager.find(Teacher.class, userString);
		if (tea == null) {
			tea = new Teacher(userString, teacherString, nameString, imageUrl, topic);
			teaManager.insert(Teacher.class, tea);
		}
	}

}
