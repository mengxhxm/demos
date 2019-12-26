package com.zc.util;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * 校验map参数,过滤空值
* @ClassName: CheckingUtil
* @Description:
* @author mxh
* @date 2019年12月16日 下午6:18:00
 */
public class CheckingUtil {
  /**
   * 移除map中空key或者value空值
   * @param map
   */
  @SuppressWarnings("rawtypes")
public static void removeNullEntry(Map map){
    removeNullKey(map);
    removeNullValue(map);
  }
  /**
   * 移除map的空key
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
   * 移除map中的value空值
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