package com.zc.service;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
/**
 * 公共service
* @ClassName: MainService
* @Description:
* @author mxh
* @date 2019年12月16日 上午8:51:42
 */
public interface MainService {

	/**
	 * 通用删除方法
	* @Description: 
	* @author ：mxh
	* @date 2019年12月19日 上午8:44:21
	 */
	int delete(Map<String, Object> map) throws Exception ;
	
	/**
	 * 通用添加操作
	* @Description: 
	* @author ：mxh
	* @date 2019年12月19日 上午8:43:57
	 */
	Object insert(Map<String, Object> map) throws Exception ;
	/**
	 * 
	* @Description:通用更新操作 
	* @author ：mxh
	* @date 2019年12月19日 上午8:43:37
	 */
	int update(Map<String, Object> map)throws Exception ;
	
	 /**
     * 单标详情
    * @Description: 
    * @author ：mxh
    * @date 2019年12月16日 下午3:02:31
     */
    Map<String, Object> selectSingleInfo(Map<String, Object> map)throws Exception ;
    /**
     * 单表列表
    * @Description: 
    * @author ：mxh
    * @date 2019年12月16日 下午3:02:39
     */
    List<Map<String, Object>> selectSingleTableList(Map<String, Object> map,PageBounds bounds)throws Exception ;
    /**
     * 导出报表
    * @Description: 
    * @author ：mxh
    * @date 2019年12月18日 下午3:25:37
     */
    List<Map<String, Object>> exportListReport(Map<String, Object> map,PageBounds bounds)throws Exception ;
	
	
	

}
