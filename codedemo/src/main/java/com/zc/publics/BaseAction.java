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
 * ���Ʋ����
 * @author: �ܺ���
 * @date: 2018��10��24�� ����6:16:13
 */
public class BaseAction {

	/**
	 * ��ҳ������JSONֵ
	 * @author: �ܺ���
	 * @date: 2018��10��24�� ����6:23:10
	 * @param response
	 * @param list
	 * @param totalCount
	 */
	public void toPagination(HttpServletResponse response, Object list, Integer totalCount){
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			Map<String, Object> jsonMap = new HashMap<String, Object>();//����map  
            jsonMap.put("total", totalCount);//total�� ����ܼ�¼���������  
            jsonMap.put("rows", list);//rows�� ���ÿҳ��¼ list  
            //��javabeanת����json�ַ���
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
	 * ���ͽṹ����ͳһ����
	 * @author: �ܺ���
	 * @date: 2019��5��6�� ����3:36:08
	 * @param response
	 * @param content
	 */
	public void toTreeGridJson(HttpServletResponse response, Object content){
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			Map<String, Object> jsonMap = new HashMap<String, Object>();//����map  
            jsonMap.put("total", 0);//total�� ����ܼ�¼���������  
            jsonMap.put("rows", content);//rows�� ���ÿҳ��¼ list  
            //��javabeanת����json�ַ���
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
	 * ר��Tree�ؼ���
	 * @author: �ܺ���
	 * @date: 2019��7��12�� ����12:11:43
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
				//��javabeanת����json�ַ���
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
            //��javabeanת����json�ַ���
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
	 * ͳһ��ѯ�б��سɹ��ӿ����ݸ�ʽ
	 * @auther ggk
	 * @date 2019��4��29������2:24:47
	 * @param total  ������
	 * @param list   ����
	 * @return
	 */
	public Map<String,Object> queryCommonMap(Object list,Integer total){
		JSONObject jsonObject =new JSONObject();
		jsonObject.put("total", total);
		jsonObject.put("rows", list);
		return responseMap(200, "1", "��ѯ�ɹ�", jsonObject);
	}
	/**
	 * ͨ�ýӿڷ��غ���
	 * @author: �ܺ���
	 * @date: 2018��11��14�� ����2:47:58
	 * @param status �ɹ���ʶ(200:�ɹ� 400:ʧ��)
	 * @param code ������
	 * @param msg  ������Ϣ
	 * @param data ��������
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
	 * ͨ�ýӿڷ��غ���(jquery���ýӿ���)
	 * @author: �ܺ���
	 * @date: 2018��11��27�� ����3:00:48
	 * @param code 0��ʾʧ�ܡ�1��ʾ�ɹ� 
	 * @param msg  ������Ϣ
	 * @param data ��������
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
	 * ����JSONֵ
	 * @author: �ܺ���
	 * @date: 2018��10��24�� ����6:23:10
	 * @param response
	 * @param list
	 * 2018-12-26 ��·����д����json���飬����ҳ
	 */
	public void toWrittenJson(HttpServletResponse response, Object list){
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			Map<String, Object> jsonMap = new HashMap<String, Object>();//����map  
            jsonMap.put("rows", list);//rows�� ���ÿҳ��¼ list  
            //��javabeanת����json�ַ���
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
