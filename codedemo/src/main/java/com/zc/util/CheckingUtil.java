package com.zc.util;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * У��map����,���˿�ֵ
* @ClassName: CheckingUtil
* @Description:
* @author mxh
* @date 2019��12��16�� ����6:18:00
 */
public class CheckingUtil {
  /**
   * �Ƴ�map�п�key����value��ֵ
   * @param map
   */
  @SuppressWarnings("rawtypes")
public static void removeNullEntry(Map map){
    removeNullKey(map);
    removeNullValue(map);
  }
  /**
   * �Ƴ�map�Ŀ�key
   * @param map
   * @return
   */
  @SuppressWarnings("rawtypes")
public static void removeNullKey(Map map){
    Set set = map.keySet();
    for (Iterator iterator = set.iterator(); iterator.hasNext();) {
      Object obj = (Object) iterator.next();
      remove(obj, iterator);
    }
  }
  /**
   * �Ƴ�map�е�value��ֵ
   * @param map
   * @return
   */
  @SuppressWarnings("rawtypes")
public static void removeNullValue(Map map){
    Set set = map.keySet();
    for (Iterator iterator = set.iterator(); iterator.hasNext();) {
      Object obj = (Object) iterator.next();
      Object value =(Object)map.get(obj);
      remove(value, iterator);
    }
  }
  @SuppressWarnings("rawtypes")
private static void remove(Object obj,Iterator iterator){
    if(obj instanceof String){
      String str = (String)obj;
      if(isEmpty(str)){ 
        iterator.remove();
      }
    }else if(obj instanceof Collection){
      Collection col = (Collection)obj;
      if(col==null||col.isEmpty()){
        iterator.remove();
      }
    }else if(obj instanceof Map){
      Map temp = (Map)obj;
      if(temp==null||temp.isEmpty()){
        iterator.remove();
      }
    }else if(obj instanceof Object[]){
      Object[] array =(Object[])obj;
      if(array==null||array.length<=0){
        iterator.remove();
      }
    }else{
      if(obj==null){
        iterator.remove();
      }
    }
  }
  public static boolean isEmpty(Object obj){
    return obj == null || obj.toString().length() == 0;
  }
}