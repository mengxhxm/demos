package com.zc.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.zc.publics.BaseAction;
import com.zc.publics.SplitPage;
import com.zc.service.MainService;
import com.zc.util.FileUtil;
import com.zc.util.WarpMap;

@Controller
@RequestMapping("/mainController	")
@SuppressWarnings("unchecked")
public class MainController extends BaseAction {

	private static final Logger LOG = Logger.getLogger(MainController.class);

	@Autowired
	private MainService service;

	@RequestMapping("/hello")
	@ResponseBody
	public String hello() {
		System.out.println("hello");
		return "hello";
	}

	/**
	 * 公用添加
	 * 
	 * @Description:
	 * @author ：mxh
	 * @date 2019年12月16日 上午10:59:00
	 */
	@RequestMapping("/addMain")
	@ResponseBody
	public Object insert(String json) {
		try {
			Map<String, Object> map = (Map<String, Object>) JSONObject.parse(json);
			System.out.println("add   " + map.toString());
			Object isAdd = service.insert(map);
			return responseMap(200, "1", "添加成功", isAdd);
		} catch (Exception e) {
			LOG.info(e.getMessage());
			return responseMap(400, "1", "添加失败", null);
		}

	}

	/**
	 * 公用修改
	 * 
	 * @Description:
	 * @author ：mxh
	 * @date 2019年12月16日 上午10:58:49
	 */
	@RequestMapping("/updateMain")
	@ResponseBody
	public Object update(String json) {
		try {
			Map<String, Object> map = (Map<String, Object>) JSONObject.parse(json);
			System.out.println("update   " + map.toString());
			int isUp = service.update(map);
			return responseMap(200, "1", "修改成功", isUp);
		} catch (Exception e) {
			LOG.info(e.getMessage());
			return responseMap(400, "1", "修改失败", null);
		}

	}

	/**
	 * 公用删除
	 * 
	 * @Description:
	 * @author ：mxh
	 * @date 2019年12月16日 上午10:58:49
	 */
	@RequestMapping("/deleteMain")
	@ResponseBody
	public Object delete(String json) {

		try {
			Map<String, Object> map = (Map<String, Object>) JSONObject.parse(json);
			int isDel = service.delete(map);
			return responseMap(200, "1", "删除成功", isDel);
		} catch (Exception e) {
			LOG.info(e.getMessage());
			return responseMap(400, "1", "删除失败", null);
		}

	}

	/**
	 * 公用查询
	 * 
	 * @Description:
	 * @author ：mxh
	 * @date 2019年12月16日 上午10:58:49
	 */
	@RequestMapping("/getInfo")
	@ResponseBody
	public Object selectInfo(String json) {
		try {
			System.out.println("  json" + json);
			Map<String, Object> map1 = (Map<String, Object>) JSONObject.parse(json);
			Map<String, Object> info = service.selectSingleInfo(map1);
			return responseMap(200, "1", "查询成功", info);
		} catch (Exception e) {
			LOG.info(e.getMessage());
			return responseMap(400, "1", "查询失败", null);
		}

	}

	/**
	 * 公用列表查询
	 * 
	 * @Description:
	 * @author ：mxh
	 * @date 2019年12月16日 上午10:58:49
	 */
	@RequestMapping("/getList")
	@ResponseBody
	public Object selectList(String json , SplitPage page) {
		try {
			System.out.println("list   " + json);
			Map<String, Object> map = (Map<String, Object>) JSONObject.parse(json);
			List<Map<String, Object>> list = service.selectSingleTableList(map,page.toPageBounds());
			return responseMap(200, "1", "查询成功", list);
		} catch (Exception e) {
			LOG.info(e.getMessage());
			return responseMap(400, "1", "查询失败", null);
		}

	}

	/**
	 * 公用列表查询
	 * 
	 * @Description:
	 * @author ：mxh
	 * @date 2019年12月16日 上午10:58:49
	 */
	@RequestMapping("/exportListReport")
	@ResponseBody
	public String exportListReport(HttpServletResponse response, String json,SplitPage page) {
		try {

			Map<String, Object> map = (Map<String, Object>) JSONObject.parse(json);
			Map<String, Object> jsonMap = WarpMap.reportWarpMap(map);
			List<Map<String, Object>> list = service.exportListReport(jsonMap,page.toPageBounds());
			String[] names = (String[]) jsonMap.get("names");
			String[] keys = (String[]) jsonMap.get("keys");
			FileUtil.toWriteExcelFile(response, "报表.xsl", list, keys, names);
			return "成功";
		} catch (Exception e) {
			LOG.info(e.getMessage());
			return "失败";
		}

	}

}
