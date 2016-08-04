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
		// 获取参数
		String totalNum = request.getParameter("totalNum"); 
		String havedNum = request.getParameter("havedNum");
		String investigation = request.getParameter("investigation");
		String user = request.getParameter("user");
		String name = request.getParameter("name");
		String imageUrl = request.getParameter("imageUrl");
		
		/**
		 * 去数据库查找
		 */
		TopicNumberManager manager = new TopicNumberManager();
		TopicNumber topicNum = manager.find(TopicNumber.class, user);
		
		// 判断是否存在
		if (topicNum == null) { // 数据不存在, 写入数据库
			
			TopicNumber insert = new TopicNumber(totalNum, havedNum, user, investigation, name, imageUrl);
			int ret = manager.insert(TopicNumber.class, insert);
			
			System.out.println("insert " + ret);
			
			// 判断是否写入成功
			if (ret == 0) { // 写入失败
				handleResult(Result.FAIL, "提交数据失败", response);
			}
		} else { // 数据已存在，进行更改
			int id = topicNum.getId();
			TopicNumber update = new TopicNumber(totalNum, havedNum, user, investigation, name, imageUrl);
			boolean ret = manager.update(TopicNumber.class, id, update);
			if (!ret) { // 写入失败
				handleResult(Result.FAIL, "提交数据失败", response);
			}
		}
	}
	
	/**
	 * 返回客户端的结果
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
