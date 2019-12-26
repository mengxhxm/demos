package com.zc.service;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
/**
 * ����service
* @ClassName: MainService
* @Description:
* @author mxh
* @date 2019��12��16�� ����8:51:42
 */
public interface MainService {

	/**
	 * ͨ��ɾ������
	* @Description: 
	* @author ��mxh
	* @date 2019��12��19�� ����8:44:21
	 */
	int delete(Map<String, Object> map) throws Exception ;
	
	/**
	 * ͨ����Ӳ���
	* @Description: 
	* @author ��mxh
	* @date 2019��12��19�� ����8:43:57
	 */
	Object insert(Map<String, Object> map) throws Exception ;
	/**
	 * 
	* @Description:ͨ�ø��²��� 
	* @author ��mxh
	* @date 2019��12��19�� ����8:43:37
	 */
	int update(Map<String, Object> map)throws Exception ;
	
	 /**
     * ��������
    * @Description: 
    * @author ��mxh
    * @date 2019��12��16�� ����3:02:31
     */
    Map<String, Object> selectSingleInfo(Map<String, Object> map)throws Exception ;
    /**
     * �����б�
    * @Description: 
    * @author ��mxh
    * @date 2019��12��16�� ����3:02:39
     */
    List<Map<String, Object>> selectSingleTableList(Map<String, Object> map,PageBounds bounds)throws Exception ;
    /**
     * ��������
    * @Description: 
    * @author ��mxh
    * @date 2019��12��18�� ����3:25:37
     */
    List<Map<String, Object>> exportListReport(Map<String, Object> map,PageBounds bounds)throws Exception ;
	
	
	

}
