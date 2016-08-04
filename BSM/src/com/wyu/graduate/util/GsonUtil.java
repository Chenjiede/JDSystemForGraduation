package com.wyu.graduate.util;

import java.util.List;

import com.google.gson.Gson;
import com.wyu.graduate.bean.Topic;

public class GsonUtil {
	
	
	/**
	 * List<<Topic> topicss = gson.fromJson(topics, new TypeToken<Topic>(){}.getType());
		
		
		gson.fromJson(topics, Topic.class);
		
	 */
	/**
	 * 吧一个对象转成json
	 * @param t
	 * @return
	 */
	public static <T> String Obj2Json(T t) {
	
		Gson gson = new Gson();
		return gson.toJson(t);
		
	}

}
