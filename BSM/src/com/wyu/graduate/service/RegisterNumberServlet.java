package com.wyu.graduate.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wyu.graduate.bean.Result;
import com.wyu.graduate.bean.TopicNumber;
import com.wyu.graduate.bean.Result.Head;
import com.wyu.graduate.dao.TopicNumberManager;
import com.wyu.graduate.util.GsonUtil;

/**
 * Servlet implementation class RegisterNumberServlet
 */
@WebServlet("/RegisterNumberServlet")
public class RegisterNumberServlet extends BaseServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doWork(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ��ȡ����
		String totalNum = request.getParameter("totalNum"); 
		String havedNum = request.getParameter("havedNum");
		String investigation = request.getParameter("investigation");
		String user = request.getParameter("user");
		String name = request.getParameter("name");
		String imageUrl = request.getParameter("imageUrl");
		
		/**
		 * ȥ���ݿ����
		 */
		TopicNumberManager manager = new TopicNumberManager();
		TopicNumber topicNum = manager.find(TopicNumber.class, user);
		
		// �ж��Ƿ����
		if (topicNum == null) { // ���ݲ�����, д�����ݿ�
			
			TopicNumber insert = new TopicNumber(totalNum, havedNum, user, investigation, name, imageUrl);
			int ret = manager.insert(TopicNumber.class, insert);
			
			System.out.println("insert " + ret);
			
			// �ж��Ƿ�д��ɹ�
			if (ret == 0) { // д��ʧ��
				handleResult(Result.FAIL, "�ύ����ʧ��", response);
			}
		} else { // �����Ѵ��ڣ����и���
			int id = topicNum.getId();
			TopicNumber update = new TopicNumber(totalNum, havedNum, user, investigation, name, imageUrl);
			boolean ret = manager.update(TopicNumber.class, id, update);
			if (!ret) { // д��ʧ��
				handleResult(Result.FAIL, "�ύ����ʧ��", response);
			}
		}
	}
	
	/**
	 * ���ؿͻ��˵Ľ��
	 * @param status
	 * @param msg
	 * @param response 
	 * @throws IOException 
	 */
	private void handleResult(int status, String msg, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		Result result = new Result();
		Head head = new Head();
		head.setStatus(status);
		head.setMsg(msg);
		result.setHead(head);
		String json = GsonUtil.Obj2Json(result);
		
		PrintWriter writer = response.getWriter();
		writer.write(json);
		writer.flush();
		writer.close();
	}

}
