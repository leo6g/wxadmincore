package com.lfc.wxadmincore.common.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;

import com.lfc.wxadmincore.common.dao.IBaseDao;

/**
 * 用户Dao基类
 */
public class BaseDaoImpl implements IBaseDao {
	private Logger log4j = LoggerFactory.getLogger(BaseDaoImpl.class);
	private SqlSessionTemplate sqlSessionTemplate;

	public SqlSessionTemplate getSqlSessionTemplate() {
		return sqlSessionTemplate;
	}

	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

	/**
	 * 根据Id获取对象
	 * 
	 * @param sqlId
	 *            脚本编号
	 * @param id
	 *            主键
	 * @return Object对象
	 */
	public Object queryForObject(String sqlId, int id) {
		return getSqlSessionTemplate().selectOne(sqlId, id);
	}

	/**
	 * 根据Id获取对象
	 * 
	 * @param sqlId
	 *            脚本编号
	 * @param id
	 *            主键
	 * @param cls
	 *            返回的对象Class
	 * @return cls对应的类
	 */
	@SuppressWarnings("unchecked")
	public <T> T queryForObject(String sqlId, int id, Class<T> cls) {
		return (T) getSqlSessionTemplate().selectOne(sqlId, id);
	}

	/**
	 * 根据条件获取对象
	 * 
	 * @param sqlId
	 *            脚本编号
	 * @param params
	 *            参数
	 * @return
	 */
	public Object queryForObject(String sqlId, Map<String, Object> params) {
		return getSqlSessionTemplate().selectOne(sqlId, params);
	}

	/**
	 * 根据条件获取对象
	 * 
	 * @param sqlId
	 *            脚本编号
	 * @param params
	 *            参数
	 * @param cls
	 *            返回的对象Class
	 * @return cls对应的类
	 */
	@SuppressWarnings("unchecked")
	public <T> T queryForObject(String sqlId, Map<String, Object> params,
			Class<T> cls) {
		return (T) getSqlSessionTemplate().selectOne(sqlId, params);
	}

	/**
	 * 获取数据总条�?
	 * 
	 * @param sqlId
	 *            脚本编号
	 * @param params
	 *            参数
	 * @return 条数
	 */
	public int getTotalCount(String sqlId, Map<String, Object> params) {
		return (Integer) getSqlSessionTemplate().selectOne(sqlId, params);
	}

	/**
	 * 查询列表
	 * 
	 * @param sqlId
	 *            脚本编号
	 * @param params
	 *            参数
	 * @param cls
	 *            返回的对象Class
	 * @return 列表<cls对应的类>
	 */
	public <T> List<T> queryForList(String sqlId, Map<String, Object> params,
			Class<T> cls) {
		return getSqlSessionTemplate().selectList(sqlId, params);
	}

	/**
	 * 查询列表
	 * 
	 * @param sqlId
	 *            脚本编号
	 * @param params
	 *            参数
	 * @return 列表
	 */
	public List<Map<String, Object>> queryForList(String sqlId,
			Map<String, Object> param) {
		List<Map<String, Object>> list = getSqlSessionTemplate().selectList(
				sqlId, param);
		return list;
	}

	/**
	 * 修改数据
	 * 
	 * @param sqlId
	 *            脚本编号
	 * @param object
	 *            对象
	 * @return 修改的行�?
	 */
	public int update(String sqlId, Object object) {
		return getSqlSessionTemplate().update(sqlId, object);
	}

	/**
	 * 插入数据
	 * 
	 * @param sqlId
	 *            脚本编号
	 * @param object
	 *            待插入的对象
	 * @return 插入条数
	 */
	public int insert(String sqlId, Object object) {
		return (Integer) getSqlSessionTemplate().insert(sqlId, object);
	}

	/**
	 * 删除数据
	 * 
	 * @param sqlId
	 *            脚本编号
	 * @param id
	 *            主键
	 * @return 主键
	 */
	public int delete(String sqlId, int id) {
		return getSqlSessionTemplate().delete(sqlId, id);
	}

	/**
	 * 删除数据
	 * 
	 * @param sqlId
	 *            脚本编号
	 * @param map
	 *            待删除的对象
	 * @return 主键
	 */
	public int delete(String sqlId, Map<String, Object> map) {
		return getSqlSessionTemplate().delete(sqlId, map);
	}

	/**
	 * 
	 * @param sqlid
	 * @param paramObj
	 * @return
	 */
	public int delete(String sqlid, Object paramObj) {
		return this.getSqlSessionTemplate().delete(sqlid, paramObj);
	}

	/**
	 * 批量更新 方法描述：批量更新（效率没有在配置文件上的高）
	 * 
	 */
	public void batchUpdate(final String statementName, final List list)
			throws DataAccessException {
		SqlSession session = sqlSessionTemplate.getSqlSessionFactory()
				.openSession(ExecutorType.BATCH, false);
		try {
			if (null != list || list.size() > 0) {
				int size = 10000;

				for (int i = 0, n = list.size(); i < n; i++) {
					this.update(statementName, list.get(i));
					if (i % 1000 == 0 || i == size - 1) {
						// 手动每1000个一提交，提交后无法回滚
						session.commit();
						// 清理缓存，防止溢出
						session.clearCache();
					}
				}
			}
		} catch (Exception e) {
			session.rollback();
			if (log4j.isDebugEnabled()) {
				e.printStackTrace();
				log4j.debug("batchUpdate error: id [" + statementName
						+ "], parameterObject [" + list + "].  Cause: "
						+ e.getMessage());
			}
		} finally {
			session.close();
		}
	}

	/**
	 * 批量插入 方法描述：批量插入（效率没有在配置文件上的高）
	 * 
	 */
	public void batchInsert(final String statementName, final List list)
			throws DataAccessException {

		SqlSession session = sqlSessionTemplate.getSqlSessionFactory()
				.openSession(ExecutorType.BATCH, false);

		int size = 10000;
		try {
			if (null != list || list.size() > 0) {
				for (int i = 0, n = list.size(); i < n; i++) {
					this.insert(statementName, list.get(i));
					if (i % 1000 == 0 || i == size - 1) {
						// 手动每1000个一提交，提交后无法回滚
						session.commit();
						// 清理缓存，防止溢出
						session.clearCache();
					}
				}
			}
		} catch (Exception e) {
			session.rollback();
			if (log4j.isDebugEnabled()) {
				e.printStackTrace();
				log4j.debug("batchInsert error: id [" + statementName
						+ "], parameterObject [" + list + "].  Cause: "
						+ e.getMessage());
			}
		} finally {
			session.close();
		}
	}

	/**
	 * 批量删除 方法描述：批量删除（效率没有在配置文件上的高）
	 * 
	 **/
	public void batchDelete(final String statementName, final List list)
			throws DataAccessException {
		SqlSession session = sqlSessionTemplate.getSqlSessionFactory()
				.openSession(ExecutorType.BATCH, false);

		int size = 10000;
		try {
			if (null != list || list.size() > 0) {
				for (int i = 0, n = list.size(); i < n; i++) {
					this.delete(statementName, list.get(i));
					if (i % 1000 == 0 || i == size - 1) {
						// 手动每1000个一提交，提交后无法回滚
						session.commit();
						// 清理缓存，防止溢出
						session.clearCache();
					}
				}
			}
		} catch (Exception e) {
			session.rollback();
			if (log4j.isDebugEnabled()) {
				e.printStackTrace();
				log4j.debug("batchDelete error: id [" + statementName
						+ "], parameterObject [" + list + "].  Cause: "
						+ e.getMessage());
			}
		} finally {
			session.close();
		}
	}

}