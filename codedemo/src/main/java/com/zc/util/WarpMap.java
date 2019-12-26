package com.zc.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @ClassName: WarpMap
 * @Description:
 * @author mxh
 * @date 2019年12月14日 下午12:05:54
 */
public class WarpMap {
	/**
	 * 添加参数格式化
	 * 
	 * @Description:
	 * @author ：mxh
	 * @date 2019年12月16日 上午10:11:46
	 */
	public static Map<String, Object> addWarpMap(Map<String, Object> map) {
		Map<String, Object> info = new HashMap<String, Object>();
		map.put("createTime",new Date());
		StringBuffer s = new StringBuffer();
		// 提取表名
		if (map.get("tableName") != null) {
			info.put("tableName", map.get("tableName").toString());
		}
		map.remove("tableName");
		CheckingUtil.removeNullEntry(map);
		// 参数转换为集合
		List<String> columList = new ArrayList<String>(map.keySet());
		// key格式化为逗号拼接的字符串
		for (int j = 0; j < columList.size(); j++) {
			if (j < columList.size() - 1) {
				s.append(columList.get(j));
				s.append(",");
			} else {
				s.append(columList.get(j));
			}
		}
		List<Object> propertyList = new ArrayList<Object>(map.values());
		info.put("keys", s);
		info.put("values", propertyList);
		return info;
	}

	/**
	 * 修改参数格式化
	 * 
	 * @Description:
	 * @author ：mxh
	 * @date 2019年12月16日 上午10:11:27
	 */
	public static Map<String, Object> updateWarpMap(Map<String, Object> map) {
		Map<String, Object> info = new HashMap<String, Object>();
		if (map.get("createTime") != null) {
			map.remove("createTime");
		}
		// 提取表名
		if (map.get("tableName") != null) {
			info.put("tableName", map.get("tableName").toString());
		}
		map.remove("tableName");
		// 提取主键id
		if (map.get("id") != null) {
			info.put("id", map.get("id").toString());
		}
		map.remove("id");
		CheckingUtil.removeNullEntry(map);
		if(map.keySet().size()>0 ){
			info.put("maps", map);
		}
		return info;
	}

	/**
	 * 修改参数格式化
	 * 
	 * @Description:
	 * @author ：mxh
	 * @date 2019年12月16日 上午10:11:27
	 */
	public static Map<String, Object> deleteWarpMap(Map<String, Object> map) {
		Map<String, Object> info = new HashMap<String, Object>();
		// 提取表名
		if (map.get("tableName") != null) {
			info.put("tableName", map.get("tableName").toString());
		}
		map.remove("tableName");
		// 提取主键id
		if (map.get("ids") != null) {
			String[] strs = map.get("ids").toString().split(",");
			List<Integer> ids = new ArrayList<Integer>();
			for (String string : strs) {
				ids.add(Integer.valueOf(string));
			}
			info.put("ids", ids);
		}
		map.remove("ids");
		CheckingUtil.removeNullEntry(map);
		info.put("maps", map);
		return info;
	}


	public static Map<String, Object> reportWarpMap(Map<String, Object> map) {
		Map<String, Object> info = new HashMap<String, Object>();
		// 提取表名
		if (map.get("tableName") != null) {
			info.put("tableName", map.get("tableName").toString());
		}
		if (map.get("names") != null) {
			String[] name = map.get("names").toString().split(",");
			info.put("names", name);
		}
		if(map.get("keys")!=null) {
			String[] keys =map.get("keys").toString().split(",");
			info.put("keys", keys);
		}
		return info;
	}

}
