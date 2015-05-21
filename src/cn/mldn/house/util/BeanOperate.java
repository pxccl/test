package cn.mldn.house.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;

/**
 * 本类的功能是根据属性的名称与属性设置传递的内容
 * 
 * @author mldn
 * 
 */
public class BeanOperate {
	private Object obj; // 操作对象
	private String property; // 操作属性
	private Object value; // 数据内容
	private Field field; // 要操作的属性
	private String fieldName; // 保存成员的名称
	private Object currentObject; // 操作属性的对象
	/**
	 * 本构造方法需要由用户自己调用处理参数与设置内容的操作
	 * @param obj
	 * @param property
	 */
	public BeanOperate(Object obj,String property) {
		this.obj = obj ;
		this.property = property ;
	}
	
	/**
	 * 本类里面实际上只需要一些基本信息
	 * 
	 * @param obj
	 *            要操作的对象
	 * @param property
	 *            属性名称
	 * @param value
	 *            要操作的内容
	 */
	public BeanOperate(Object obj, String property, Object value) {
		this.obj = obj;
		this.property = property;
		this.value = value;
		this.handleString();// 直接进行数据处理
		this.setFieldValue(); // 设置成员的内容
	}
	/**
	 * 设置要操作的数据
	 * @param value
	 */
	public void setValue(Object value) {
		this.value = value;
	}
	/**
	 * 此操作的核心功能就是要处理字符串，从里面区分出要操作的属性以及对象
	 */
	public void handleString() {
		String result[] = this.property.split("\\."); // 按照.拆分，长度最小为2
		this.currentObject = this.obj; // 设置一个当前操作对象；
		try {
			if (result.length == 2) { // 只有一个“.”
				this.field = this.currentObject.getClass().getDeclaredField(
						result[1]);
				this.fieldName = result[1];
			} else {
				for (int x = 1; x < result.length; x++) { // 利用循环操作
					// getter方法是没有参数的，找到了getter方法
					Method met = this.currentObject.getClass().getMethod(
							"get" + StringUtils.initcap(result[x]));
					this.field = this.currentObject.getClass()
							.getDeclaredField(result[x]);
					this.fieldName = result[x]; // 保存成员名称
					if (x < result.length - 1) { // 后面还有对象
						this.currentObject = met.invoke(this.currentObject);
					}
				}
			}
		} catch (Exception e) {
		}
	}

	/**
	 * 设置指定属性的内容，所有的操作一定要调用setter方法
	 */
	public void setFieldValue() {
		try {
			Method setMet = this.currentObject.getClass().getDeclaredMethod(
					"set" + StringUtils.initcap(this.fieldName),
					this.field.getType()); // 找到设置的setter方法
			String type = this.field.getType().getSimpleName();
			String valueType = this.value.getClass().getSimpleName();
			if ("string".equalsIgnoreCase(valueType)) {
				String val = this.value.toString();
				if ("int".equalsIgnoreCase(type)
						|| "Integer".equalsIgnoreCase(type)) { // 为数字型
					if (val.matches("\\d+")) {
						setMet.invoke(this.currentObject, Integer.parseInt(val));
					}
				} else if ("double".equalsIgnoreCase(type)
						|| "Double".equalsIgnoreCase(type)) {
					if (val.matches("\\d+(\\.\\d+)?")) {
						setMet.invoke(this.currentObject,
								Double.parseDouble(val));
					}
				} else if ("string".equalsIgnoreCase(type)) {
					setMet.invoke(this.currentObject, val);
				} else if ("date".equalsIgnoreCase(type)) {
					if (val.matches("\\d{4}-\\d{2}-\\d{2}")) {
						setMet.invoke(this.currentObject, new SimpleDateFormat(
								"yyyy-MM-dd").parse(val));
					}
				}
			} else { // 传入的内容为String字符串
				String val[] = (String[]) this.value; // 将Object变为数组
				this.field.setAccessible(true);
				if ("string[]".equalsIgnoreCase(type)) { // 如果是数组
					// setMet.invoke(this.currentObject, val); // 字符串数组
					this.field.set(this.currentObject, val);
				} else if ("int[]".equalsIgnoreCase(type)
						|| "Integer[]".equalsIgnoreCase(type)) {
					Integer data[] = new Integer[val.length];
					for (int x = 0; x < val.length; x++) {
						data[x] = Integer.parseInt(val[x]);
					}
					// setMet.invoke(this.currentObject, data); // 字符串数组
					this.field.set(this.currentObject, data);
				} else if ("double[]".equalsIgnoreCase(type)) {
					Double data[] = new Double[val.length];
					for (int x = 0; x < val.length; x++) {
						data[x] = Double.parseDouble(val[x]);
					}
					// setMet.invoke(this.currentObject, data); // 字符串数组
					this.field.set(this.currentObject, data);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Object getCurrentObject() {
		return currentObject;
	}

	public Field getField() {
		return field;
	}
}
