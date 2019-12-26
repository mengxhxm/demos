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
 * @date 2019��12��14�� ����12:05:54
 */
public class WarpMap {
	/**
	 * ��Ӳ�����ʽ��
	 * 
	 * @Description:
	 * @author ��mxh
	 * @date 2019��12��16�� ����10:11:46
	 */
	public static Map<String, Object> addWarpMap(Map<String, Object> map) {
		Map<String, Object> info = new HashMap<String, Object>();
		map.put("createTime",new Date());
		StringBuffer s = new StringBuffer();
		// ��ȡ����
		if (map.get("tableName") != null) {
			info.put("tableName", map.get("tableName").toString());
		}
		map.remove("tableName");
		CheckingUtil.removeNullEntry(map);
		// ����ת��Ϊ����
		List<String> columList = new ArrayList<String>(map.keySet());
		// key��ʽ��Ϊ����ƴ�ӵ��ַ���
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
	 * �޸Ĳ�����ʽ��
	 * 
	 * @Description:
	 * @author ��mxh
	 * @date 2019��12��16�� ����10:11:27
	 */
	public static Map<String, Object> updateWarpMap(Map<String, Object> map) {
		Map<String, Object> info = new HashMap<String, Object>();
		if (map.get("createTime") != null) {
			map.remove("createTime");
		}
		// ��ȡ����
		if (map.get("tableName") != null) {
			info.put("tableName", map.get("tableName").toString());
		}
		map.remove("tableName");
		// ��ȡ����id
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
	 * �޸Ĳ�����ʽ��
	 * 
	 * @Description:
	 * @author ��mxh
	 * @date 2019��12��16�� ����10:11:27
	 */
	public static Map<String, Object> deleteWarpMap(Map<String, Object> map) {
		Map<String, Object> info = new HashMap<String, Object>();
		// ��ȡ����
		if (map.get("tableName") != null) {
			info.put("tableName", map.get("tableName").toString());
		}
		map.remove("tableName");
		// ��ȡ����id
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
		// ��ȡ����
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
