package com.zc.publics;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * 控制层基类
 * @author: 周海龙
 * @date: 2018年10月24日 下午6:16:13
 */
public class BaseAction {

	/**
	 * 分页栏返回JSON值
	 * @author: 周海龙
	 * @date: 2018年10月24日 下午6:23:10
	 * @param response
	 * @param list
	 * @param totalCount
	 */
	public void toPagination(HttpServletResponse response, Object list, Integer totalCount){
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			Map<String, Object> jsonMap = new HashMap<String, Object>();//定义map  
            jsonMap.put("total", totalCount);//total键 存放总记录数，必须的  
            jsonMap.put("rows", list);//rows键 存放每页记录 list  
            //将javabean转化成json字符串
            String jsonStr = JSON.toJSONString(jsonMap);
			out.println(jsonStr);
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(out != null){
				out.flush();
				out.close();
			}
		}
	}
	/**
	 * 树型结构返回统一方法
	 * @author: 周海龙
	 * @date: 2019年5月6日 下午3:36:08
	 * @param response
	 * @param content
	 */
	public void toTreeGridJson(HttpServletResponse response, Object content){
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			Map<String, Object> jsonMap = new HashMap<String, Object>();//定义map  
            jsonMap.put("total", 0);//total键 存放总记录数，必须的  
            jsonMap.put("rows", content);//rows键 存放每页记录 list  
            //将javabean转化成json字符串
            String jsonStr = JSON.toJSONString(jsonMap);
			out.println(jsonStr);
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(out != null){
				out.flush();
				out.close();
			}
		}
	}
	/**
	 * 专用Tree控件用
	 * @author: 周海龙
	 * @date: 2019年7月12日 下午12:11:43
	 * @param response
	 * @param content
	 */
	public void writerJson(HttpServletResponse response, Object content){
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
            if("[]".equals(content)){
				out.println("{\"total\":0,\"rows\":[]}");
			}else{
				//将javabean转化成json字符串
	            String jsonStr = JSON.toJSONString(content);
				out.println(jsonStr);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(out != null){
				out.flush();
				out.close();
			}
		}
	}
	
	
	public void toComboTreeJson(HttpServletResponse response, Object content){
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
            //将javabean转化成json字符串
            String jsonStr = JSON.toJSONString(content);
			out.println(jsonStr);
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(out != null){
				out.flush();
				out.close();
			}
		}
	}
	
	/**
	 * 统一查询列表返回成功接口数据格式
	 * @auther ggk
	 * @date 2019年4月29日下午2:24:47
	 * @param total  总条数
	 * @param list   集合
	 * @return
	 */
	public Map<String,Object> queryCommonMap(Object list,Integer total){
		JSONObject jsonObject =new JSONObject();
		jsonObject.put("total", total);
		jsonObject.put("rows", list);
		return responseMap(200, "1", "查询成功", jsonObject);
	}
	/**
	 * 通用接口返回函数
	 * @author: 周海龙
	 * @date: 2018年11月14日 下午2:47:58
	 * @param status 成功标识(200:成功 400:失败)
	 * @param code 返回码
	 * @param msg  返回信息
	 * @param data 返回数据
	 * @return
	 */
	public Map<String, Object> responseMap(Integer status,String code,String msg,Object data){
		Map<String, Object> retMap = new LinkedHashMap<String, Object>();
		retMap.put("status", status);
        retMap.put("code", code);
        retMap.put("msg", msg);
        retMap.put("data", data);
        return retMap;
	}
	
	/**
	 * 通用接口返回函数(jquery调用接口用)
	 * @author: 周海龙
	 * @date: 2018年11月27日 下午3:00:48
	 * @param code 0表示失败、1表示成功 
	 * @param msg  返回消息
	 * @param data 返回数据
	 * @return
	public Map<String, Object> responseMap(String code,String msg,Object data){
		Map<String, Object> retMap = new LinkedHashMap<String, Object>();
        retMap.put("code", code);
        retMap.put("msg", msg);
        retMap.put("data", data);
        return retMap;
	}
	*/
	
	/**
	 * 返回JSON值
	 * @author: 周海龙
	 * @date: 2018年10月24日 下午6:23:10
	 * @param response
	 * @param list
	 * 2018-12-26 刘路安改写返回json数组，不分页
	 */
	public void toWrittenJson(HttpServletResponse response, Object list){
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			Map<String, Object> jsonMap = new HashMap<String, Object>();//定义map  
            jsonMap.put("rows", list);//rows键 存放每页记录 list  
            //将javabean转化成json字符串
            String jsonStr = JSON.toJSONString(jsonMap);
			out.println(jsonStr);
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(out != null){
				out.flush();
				out.close();
			}
		}
	}
	
}
