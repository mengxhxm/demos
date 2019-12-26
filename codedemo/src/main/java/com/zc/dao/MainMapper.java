package com.zc.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
/**
 * ����mapper
* @ClassName: MainMapper
* @Description:
* @author mxh
* @date 2019��12��16�� ����8:51:56
 */
@Repository
public interface MainMapper {
	/**
	 * ����ɾ������(������,������)
	* @Description: 
	* @author ��mxh
	* @date 2019��12��16�� ����8:52:05
	 */
    int delete(Map<String, Object> map);
    /**
     * 
     * @Description: 
     * @author ��mxh
     * @date 2019��12��16�� ����8:52:05
     */
    int deletelAll(Map<String, Object> map);

    /**
     * ������ӷ���
    * @Description: 
    * @author ��mxh
    * @date 2019��12��16�� ����8:52:29
     */
    int insert(Map<String, Object> map);

    /**
     * �����޸ķ���
    * @Description: 
    * @author ��mxh
    * @date 2019��12��16�� ����8:52:43
     */
    int update(Map<String, Object> map);
    
    /**
     * ��������
    * @Description: 
    * @author ��mxh
    * @date 2019��12��16�� ����3:02:31
     */
    Map<String, Object> selectSingleInfo(Map<String, Object> map);
    /**
     * �����б�
    * @Description: 
    * @author ��mxh
    * @date 2019��12��16�� ����3:02:39
     */
    List<Map<String, Object>> selectSingleTableList(Map<String, Object> map,PageBounds bounds);

}