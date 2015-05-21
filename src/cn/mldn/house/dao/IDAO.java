package cn.mldn.house.dao;

import java.util.List;
import java.util.Set;
/**
 * 负责表的数据层基本操作标准，具体的操作类型由子接口来决定
 * @param <K> 主键的类型
 * @param <V> VO的类型
 * @author mldn
 */
public interface IDAO<K,V> {
	public static final String ORDER_ASC = "asc" ;
	public static final String ORDER_DESC = "desc" ;
	/**
	 * 数据增加
	 * @param vo 包含了要增加数据的VO类对象
	 * @return 增加成功返回true，增加失败返回false
	 * @throws Exception 如果信息过长或者是数据为空
	 */
	public boolean doCreate(V vo) throws Exception;
	/**
	 * 数据更新操作，此更新操作是更新数据表的全部字段
	 * @param vo 包含了要修改数据的VO类对象
	 * @return 修改成功返回true，修改返回false
	 * @throws Exception 如果信息过长或者是数据为空
	 */
	public boolean doUpdate(V vo) throws Exception ;
	/**
	 * 执行数据的批量删除操作，所有要删除的id编号以Set集合传递
	 * @param ids 保存所有的要删除数据的编号，不能够重复
	 * @return 删除成功返回true，否则返回false
	 * @throws Exception 如果表编写错误，或者存在外键关系
	 */
	public boolean doRemoveBatch(Set<K> ids) throws Exception ;
	/**
	 * 根据雇员编号查询数据
	 * @param id 要查询的数据ID
	 * @return 如果查询成功则将数据以VO类对象的形式返回，如果失败或者没有查询到数据返回null
	 * @throws Exception 如果表字段填写错误
	 */
	public V findById(K id) throws Exception ;
	/**
	 * 查询表之中的全部记录
	 * @return 会返回多个VO类对象，使用List集合返回，
	 *  如果现在表中没有数据，则依然会返回集合，但是集合的长度为0（size() == 0）
	 * @throws Exception 如果表字段填写错误
	 */
	public List<V> findAll(String order) throws Exception ;
	/**
	 * 数据的模糊&分页显示查询，会返回所有满足查询条件的数据
	 * @param column 要模糊查询的列
	 * @param keyWord 设置的查询关键字，如果此关键字的内容为空字符串（""不是null），则表示查询全部
	 * @param currentPage 当前所在页
	 * @param lineSize 每页显示的数据行
	 * @return 会返回多个VO类对象，使用List集合返回，
	 *  如果现在表中没有数据，则依然会返回集合，但是集合的长度为0（size() == 0）
	 * @throws Exception 如果表字段填写错误
	 */
	public List<V> findAll(String column, String keyWord,
			Integer currentPage, Integer lineSize,String order) throws Exception;
	/**
	 * 实现信息的统计，可以针对于模糊查询实现数据量的统计操作，使用COUNT()函数操作
	 * @param column 要模糊查询的列
	 * @param keyWord 设置的查询关键字，如果此关键字的内容为空字符串（""不是null），则表示查询全部
	 * @return 返回查询的结果数量，如果没有内容则返回0
	 * @throws Exception 如果表字段填写错误
	 */
	public Integer getAllCount(String column,String keyWord) throws Exception ;
}
 