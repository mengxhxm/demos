package com.zc.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.inject.New;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.zc.dao.MainMapper;
import com.zc.service.MainService;
import com.zc.util.WarpMap;

@Transactional
@Service
@SuppressWarnings("unchecked")
public class MainServiceImpl implements MainService {

	@Autowired
	private MainMapper mapper;

	public int delete(Map<String, Object> map) {
		Map<String, Object> maps = WarpMap.deleteWarpMap(map);
		return mapper.delete(maps);
	}

	public Object insert(Map<String, Object> map) {
		int add = 0;
		if (map.get("children") != null) {
			List<Map<String, Object>> childMapList = (List<Map<String, Object>>) map.get("children");
			map.remove("children");
			Map<String, Object> maps = WarpMap.addWarpMap(map);
			add = mapper.insert(maps);
			String tableName = maps.get("tableName").toString();
			tableName = tableName.substring(tableName.indexOf("_") + 1, tableName.length());
			String id = maps.get("id").toString();
			for (Map<String, Object> childMap : childMapList) {
				childMap.put(tableName + "Id", new Long(id));
				mapper.insert(WarpMap.addWarpMap(childMap));

			}
		} else {
			map.remove("children");
			Map<String, Object> maps = WarpMap.addWarpMap(map);
			System.out.println("1" + maps.toString());
			add = mapper.insert(maps);
		}
		return add;
	}

	public int update(Map<String, Object> map) {
		if (map.get("children") != null) {
			List<Map<String, Object>> childMapList = (List<Map<String, Object>>) map.get("children");
			Map<String, Object> info=new HashMap<String, Object>();
			Map<String, Object> info1=new HashMap<String, Object>();
			String tableName = map.get("tableName").toString();
			tableName = tableName.substring(tableName.indexOf("_") + 1, tableName.length());
			String id = map.get("id").toString();
			info1.put(tableName + "Id", new Long(id));
			for (Map<String, Object> childMap : childMapList) {
				info.put("tableName",childMap.get("tableName").toString());
			}
			info.put("info", info1);
			mapper.deletelAll(info);
			for (Map<String, Object> childMap : childMapList) {
				childMap.put(tableName + "Id", new Long(id));
				mapper.insert(WarpMap.addWarpMap(childMap));
			}
		}
		map.remove("children");
		return mapper.update(WarpMap.updateWarpMap(map));
	}

	public Map<String, Object> selectSingleInfo(Map<String, Object> map) throws Exception {
		Map<String, Object> info = mapper.selectSingleInfo(map);
		dateTimeFormate(info);
		return info;
	}

	public List<Map<String, Object>> selectSingleTableList(Map<String, Object> map, PageBounds bounds)
			throws Exception {
		List<Map<String, Object>> list = mapper.selectSingleTableList(WarpMap.updateWarpMap(map), bounds);
		for (Map<String, Object> info : list) {
			dateTimeFormate(info);
		}
		return list;
	}

	public List<Map<String, Object>> exportListReport(Map<String, Object> map, PageBounds bounds) throws Exception {
		List<Map<String, Object>> list = mapper.selectSingleTableList(map, bounds);
		for (Map<String, Object> info : list) {
			dateTimeFormate(info);
		}
		return list;
	}

	/**
	 * 格式化日期
	 * 
	 * @Description:
	 * @author ：mxh
	 * @date 2019年12月19日 上午10:11:25
	 */
	private void dateTimeFormate(Map<String, Object> map) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS");
		Date date = new Date();
		if (map.get("createTime") != null) {
			date = (Date) map.get("createTime");
			String date1 = format.format(date);
			map.put("createTime", date1);
		}
	}
}
