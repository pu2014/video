package com.webframe.genderate.common.util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.TypeReference;


public class ReflectUtil {
	
	
	private static Logger logger = LoggerFactory.getLogger(ReflectUtil.class);
	
	
    /**
     * json数组转为bean数组
     * @param mapListStr
     * @param cls
     * @return
     * @throws JSONException
     */
    public static <T> List<T> jsonStrToBeanList(String jsonStr,Class<T> cls) throws JSONException{
    	List<Map<String,Object>> mapList = (List<Map<String,Object>>)  JSON.parseObject(jsonStr, new TypeReference<List<Map<String,Object>>>(){});
    	return mapListToBeanList(mapList,cls);
    }
	
	
    /**
     * 将map数组转为相应的bean数组
     * @param mapList
     * @param cls
     */
    public static <T> List<T> mapListToBeanList(List<Map<String,Object>> mapList,Class<T> cls){
    	List<T> dataList = new ArrayList<T>();
    	if(mapList == null){
    		return dataList;
    	}
    	
    	PropertyDescriptor[] propertyArr =  null;
    	List<String> fieldNameArr = null;
    	List<Method> methodArr = null;
    	List<String> typeArr = null;
    	try{
	    	BeanInfo beanInfo = Introspector.getBeanInfo(cls);
	        propertyArr =  beanInfo.getPropertyDescriptors(); 
	        fieldNameArr = new ArrayList<String>();
	        methodArr = new ArrayList<Method>();
	        typeArr = new ArrayList<String>();
	        for (int i = 0; i< propertyArr.length; i++) { 
	            PropertyDescriptor descriptor = propertyArr[i]; 
	            String propertyName = descriptor.getName(); 
	            if(!propertyName.equals("class")){
	            	Method readMethod = descriptor.getWriteMethod();
	            	String type = readMethod.getParameterTypes()[0].toString();
	            	
	            	fieldNameArr.add(propertyName);
	            	methodArr.add(readMethod);
	            	typeArr.add(type);
	            }
	        } 
	        
			for(Map<String,Object> map : mapList){
				dataList.add(getObj(fieldNameArr,methodArr,typeArr,map,cls));
			}
   	 	}catch(Exception e){
	      	
	    }
		return dataList;
	}
    
    
    /**
     * 将map转为Bean对象
     * @param map
     * @param cls
     * @return
     */
    public static <T> T mapToBean(Map<String,Object> map,Class<T> cls){
    	if(map == null){
    		return null;
    	} 
    	
    	
    	PropertyDescriptor[] propertyArr =  null;
    	List<String> fieldNameArr = null;
    	List<Method> methodArr = null;
    	List<String> typeArr = null;
    	try{
 	    	BeanInfo beanInfo = Introspector.getBeanInfo(cls);
 	        propertyArr =  beanInfo.getPropertyDescriptors(); 
 	        fieldNameArr = new ArrayList<String>();
 	        methodArr = new ArrayList<Method>();
 	        typeArr = new ArrayList<String>();
 	        for (int i = 0; i< propertyArr.length; i++) { 
 	            PropertyDescriptor descriptor = propertyArr[i]; 
 	            String propertyName = descriptor.getName(); 
 	            if(!propertyName.equals("class")){
 	            	Method readMethod = descriptor.getWriteMethod();
 	            	String type = readMethod.getParameterTypes()[0].toString();
 	            	
 	            	fieldNameArr.add(propertyName);
 	            	methodArr.add(readMethod);
 	            	typeArr.add(type);
 	            }
 	        } 
 	       return getObj(fieldNameArr,methodArr,typeArr,map,cls);	
         }catch(Exception e){
         	
         }
		return null;
	}
    
    
    
    /**
     * 将map数组转为相应的bean数组
     * @param mapList
     * @param cls
     */
    public static List<Map<Object,Object>> beanListToMapList(List<?> beanList,Class<?> cls){
    	List<Map<Object,Object>> mapList = new ArrayList<Map<Object,Object>>();
    	if(beanList == null){
    		return mapList;
    	}
   	 	try{
	        BeanInfo beanInfo = Introspector.getBeanInfo(cls);
	        PropertyDescriptor[] propertyDescriptors =  beanInfo.getPropertyDescriptors(); 
	        ArrayList<String> propertyList = new ArrayList<String>();
	        ArrayList<Method> methodList = new ArrayList<Method>();
	        for (int i = 0; i< propertyDescriptors.length; i++) { 
	             PropertyDescriptor descriptor = propertyDescriptors[i]; 
	             String propertyName = descriptor.getName(); 
	             if (!propertyName.equals("class")) { 
	                 Method method = descriptor.getReadMethod(); 
	                 propertyList.add(propertyName);
	                 methodList.add(method);
	             }
	        }
	        
	        Map<Object,Object> map = null;
	        for(Object bean : beanList){
	        	map = new HashMap<Object,Object>(); 
	        	for (int i = 0; i < propertyList.size(); i++) { 
	        		 Object result = methodList.get(i).invoke(bean, new Object[0]); 
	        		 map.put(propertyList.get(i), result == null ? "" : result); 
		        }
	        	mapList.add(map);
	        }
        }catch (Exception e) {
			
		}
		return mapList;
	}
    
    
    /**
     * bean转为map
     * @param bean
     * @return
     */
    public static Map<String,Object> beanToMap(Object bean){
    	 Class<?> type = bean.getClass(); 
         Map<String,Object> returnMap = new HashMap<String,Object>(); 
         BeanInfo beanInfo = null;
		 try {
			 beanInfo = Introspector.getBeanInfo(type);
		 } catch (IntrospectionException e1) {
			 logger.error("Introspection期间发生异常异常",e1);
		 } 
         PropertyDescriptor[] propertyDescriptors =  beanInfo.getPropertyDescriptors(); 
         try{
	         for (int i = 0; i< propertyDescriptors.length; i++) { 
	             PropertyDescriptor descriptor = propertyDescriptors[i]; 
	             String propertyName = descriptor.getName(); 
	             if (!propertyName.equals("class")) { 
	                 Method readMethod = descriptor.getReadMethod(); 
	                
	                 Object result = readMethod.invoke(bean, new Object[0]); 
	                 if (result != null) { 
	                     returnMap.put(propertyName, result); 
	                 } else { 
	                     returnMap.put(propertyName, ""); 
	                 } 
	             } 
	         } 
         }catch (Exception e) {
			
		 }
         return returnMap; 
    }
    
    
    private static <T> T getObj(List<String> fieldNameArr, List<Method> methodArr, List<String> typeArr, Map<String,Object> map, Class<T> cls){
		
		T model = null;
		try{
			model = cls.newInstance();
			String fieldName = "";
			String type = "";
			Method method = null;
			for (int i = 0; i < fieldNameArr.size(); i++) { 
				fieldName = fieldNameArr.get(i);
				type = typeArr.get(i);
				method = methodArr.get(i);
				if(type.startsWith("class")){
					if (type.equals("class java.lang.String")) { 
						method.invoke( model, StringUtil.null2String(map.get(fieldName)));   
		            } else if (type.equals("class java.util.Date")) { 
		            	if(map.get(fieldName) instanceof java.util.Date){
		            		method.invoke(model, map.get(fieldName) );   
		            	}else{
		            		method.invoke(model, DateUtil.parseDate(StringUtil.null2String(map.get(fieldName) )));   
		            	}
		            	
		            }
		            //java基本数据类型的封装类 
		            else if (type.equals("class java.lang.Long")) { 
		            	method.invoke(model,StringUtil.strToLong(map.get(fieldName) ));   
		            } else if (type.equals("class java.lang.Double")) { 
		            	method.invoke(model, StringUtil.strToDouble(map.get(fieldName) ));   
		            } else if (type.equals("class java.lang.Float")) { 
		            	method.invoke(model, StringUtil.strToFloat(map.get(fieldName) ));   
		            } else if (type.equals("class java.lang.Integer")) { 
		            	method.invoke(model,StringUtil.strToInt(map.get(fieldName) ));   
		            } else if (type.equals("class java.lang.Boolean")) { 
		            	method.invoke(model,StringUtil.strToBoolean(map.get(fieldName)));   
		            } else if (type.equals("class java.lang.Character")) { 
		            	method.invoke(model, StringUtil.strToChar(map.get(fieldName) ));   
		            } else if (type.equals("class java.lang.Byte")) { 
		            	method.invoke(model, StringUtil.strToByte(map.get(fieldName) ));   
		            } else if (type.equals("class java.lang.Short")) { 
		            	method.invoke(model,StringUtil.strToShort(map.get(fieldName) ));   
		            } 
				}else{
					//java 基本数据类型
					if (type.equals("long")) { 
		            	method.invoke(model,StringUtil.str2Long(map.get(fieldName) ));   
		            } else if (type.equals("double")) { 
		            	method.invoke(model, StringUtil.str2Double(map.get(fieldName) ));   
		            } else if (type.equals("float")) { 
		            	method.invoke(model, StringUtil.str2Float(map.get(fieldName) ));   
		            } else if (type.equals("int")) { 
		            	method.invoke(model, StringUtil.str2Int(map.get(fieldName) ));   
		            } else if (type.equals("boolean")) { 
		            	method.invoke(model, StringUtil.str2Boolean(map.get(fieldName) ));   
		            } else if (type.equals("char")) { 
		            	method.invoke(model, StringUtil.str2Char(map.get(fieldName) ));   
		            } else if (type.equals("byte")) { 
		            	method.invoke(model, StringUtil.str2Byte(map.get(fieldName) ));   
		            } else if (type.equals("short")) { 
		            	method.invoke(model, StringUtil.str2Short(map.get(fieldName) ));   
		            }         
				}
	        }  
		}catch (Exception e){
			logger.error(e.getMessage());
		}
		return model;
	} 
    
    
    
	/**
	 * 比较两个对象
	 * @param oldObj 旧的对象
	 * @param newObj 新的对象
	 * return 属性值不一样的值及属性 ,col,oldValue,newValue
	 */
	public static List<Map<String,Object>> compareObject(Object oldObj,Object newObj){
		
		Map<String,Object> oldMap = beanToMap(oldObj);
		Map<String,Object> newMap = beanToMap(newObj);
		
		List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
		Map<String,Object> tmpMap = null;
		for(String key : oldMap.keySet()){
			if( !oldMap.get(key).equals(newMap.get(key)) ){
				tmpMap = new HashMap<String, Object>();
				tmpMap.put("changeCol", key);
				tmpMap.put("oldValue", oldMap.get(key));
				tmpMap.put("newValue", newMap.get(key));
				resultList.add(tmpMap);
			}
		}
		
		return resultList;
	}
    
    
    /*
    public static Map<String, Object> beanToMap(Object obj) { 
		  
        Map<String, Object> params = new HashMap<String, Object>(0); 
        try { 
            PropertyUtilsBean propertyUtilsBean = new PropertyUtilsBean(); 
            PropertyDescriptor[] descriptors = propertyUtilsBean.getPropertyDescriptors(obj); 
            for (int i = 0; i < descriptors.length; i++) { 
                String name = descriptors[i].getName(); 
                if (!"class".equals(name)) { 
                    params.put(name, propertyUtilsBean.getNestedProperty(obj, name)); 
                } 
            } 
        } catch (Exception e) { 
            e.printStackTrace(); 
        } 
        return params; 
	  }
    
     */
    
}
