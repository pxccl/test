package cn.mldn.house.util;
/**
 * �ַ���������࣬����ʵ���ַ���ݵĴ���
 * @author mldn
 */
public class StringUtils {
	/**
	 * ʵ���ַ�����ĸ��д���ܣ�������ĸ���ִ�Сд����
	 * @param str Ҫת�����ַ����
	 * @return ���ַ������ĸ��д�󷵻�
	 */
	public static String initcap(String str) {
		if (str == null) { 
			return null;
		}
		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}
}
