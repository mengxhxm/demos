package com.zc.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
/**
 * 公共mapper
* @ClassName: MainMapper
* @Description:
* @author mxh
* @date 2019年12月16日 上午8:51:56
 */
@Repository
public interface MainMapper {
	/**
	 * 公共删除方法(可批量,可条件)
	* @Description: 
	* @author ：mxh
	* @date 2019年12月16日 上午8:52:05
	 */
    int delete(Map<String, Object> map);
    /**
     * 
     * @Description: 
     * @author ：mxh
     * @date 2019年12月16日 上午8:52:05
     */
    int deletelAll(Map<String, Object> map);

    /**
     * 公共添加方法
    * @Description: 
    * @author ：mxh
    * @date 2019年12月16日 上午8:52:29
     */
    int insert(Map<String, Object> map);

    /**
     * 公共修改方法
    * @Description: 
    * @author ：mxh
    * @date 2019年12月16日 上午8:52:43
     */
    int update(Map<String, Object> map);
    
    /**
     * 单标详情
    * @Description: 
    * @author ：mxh
    * @date 2019年12月16日 下午3:02:31
     */
    Map<String, Object> selectSingleInfo(Map<String, Object> map);
    /**
     * 单表列表
    * @Description: 
    * @author ：mxh
    * @date 2019年12月16日 下午3:02:39
     */
    List<Map<String, Object>> selectSingleTableList(Map<String, Object> map,PageBounds bounds);

}