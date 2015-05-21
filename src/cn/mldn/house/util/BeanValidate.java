package cn.mldn.house.util;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 这个类的主要功能是进行数据对象是否为空的验证
 * @author mldn
 */
public class BeanValidate {
	private Map<String,String> errorMsg = new HashMap<String,String>() ;
	private Object obj ;	// 表示最外层的对象
	private Object currentObject ;	// 表示当前的操作对象
	private String property ;
	public BeanValidate() {
	}
	/**
	 * 进行操作对象与属性集合的验证
	 * @param obj
	 * @param property
	 */
	public BeanValidate(Object obj) {
		this.obj = obj ;
	}
	public void setObj(Object obj) {
		this.obj = obj;
	}
	public void setProperty(String property) {
		this.property = property;
	}
	public void handleString() {
		String result[] = this.property.split("\\."); // 按照.拆分，长度最小为2
		this.currentObject = this.obj; // 设置一个当前操作对象；
		try {
			if (result.length == 2) { // 只有一个“.”
				Method getMet = this.currentObject.getClass().getMethod("get" + StringUtils.initcap(result[1])) ;
				if (getMet.invoke(this.currentObject) == null) {	// 错误的结果
					this.errorMsg.put(this.property, "数据输入错误！") ;
				}
			} else {
				for (int x = 1; x < result.length; x++) { // 利用循环操作
					// getter方法是没有参数的，找到了getter方法
					Method met = this.currentObject.getClass().getMethod(
							"get" + StringUtils.initcap(result[x]));
					
					Method getMet = this.currentObject.getClass().getMethod("get" + StringUtils.initcap(result[x])) ;
					if (getMet.invoke(this.currentObject) == null) {	// 错误的结果
						this.errorMsg.put(this.property, "数据输入错误！") ;
					}
					if (x < result.length - 1) { // 后面还有对象
						this.currentObject = met.invoke(this.currentObject);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public Map<String, String> getErrorMsg() {
		return errorMsg;
	}
}
