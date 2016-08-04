package com.wyu.graduate.bean;



/**
 * {
 *   head{
 *     status 0 1
 *     msg:succ
 *   }
 *   
 *   body{
 *   
 *   }
 * 
 * }
 * 
 * 
 */

public class Result<T> {
	
	public static final int SUCC = 0;
	public static final int FAIL = 1;

	private Head head;
	private T body;

	public Head getHead() {
		return head;
	}

	public void setHead(Head head) {
		this.head = head;
	}

	@Override
	public String toString() {
		return "Result [head=" + head + ", body=" + body + "]";
	}

	public T getBody() {
		return body;
	}

	public void setBody(T body) {
		this.body = body;
	}

	public static class Head {
		private int status;
		private String msg;

		public int getStatus() {
			return status;
		}

		public void setStatus(int status) {
			this.status = status;
		}

		public String getMsg() {
			return msg;
		}

		public void setMsg(String msg) {
			this.msg = msg;
		}

		@Override
		public String toString() {
			return "Head [status=" + status + ", msg=" + msg + "]";
		}

	}

}
