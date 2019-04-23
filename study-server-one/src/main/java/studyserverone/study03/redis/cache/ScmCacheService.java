package studyserverone.study03.redis.cache;

import org.springframework.util.ObjectUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Author: houMedia
 * @Description: 业务缓存服务
 * @Date: Created in 0:57 2019/2/1
 */
public interface ScmCacheService<F,T>{

	/**
	 * 按照商户id及缓存feild批量获取缓存信息
	 * @param feildList 缓存feild
	 * @return map数据格式结果集
	 */
	default Map<F, T> hgetMap(Set<F> feildList){
		return Collections.emptyMap();
	};

	/**
	 * 按照商户id及缓存feild获取单个缓存信息
	 * @param feild 缓存feild
	 * @return 缓存结果
	 */
	T hgetCache(F feild);

	/**
	 * 注意: pipelinedGet接口 不保证入参、结果对应顺序。即 结果无序。
	 * 按照商户id和缓存feild集合批量获取缓存信息
	 * @param list 缓存feild集合
	 * @return list数据格式结果结
	 */
	default List<T> pipelinedGetCache(Set<F> list) {
		return Collections.emptyList();
	};


	/**
	 * 按照商户id跟feild删除缓存
	 * @param feild 缓存feild
	 * @return 删除影响条数
	 */
	long hdelCache(F feild);
	
	/**
	 * 按照商户id删除缓存
	 * @return 删除影响条数
	 */
	default void hdelCacheByTenantId() {
	};

	/**
	 * 按照商户id跟feild结果集批量删除缓存
	 * @param list 缓存feild集
	 * @return 影响条数
	 */
	default long pipelinedDelCache(Set<F> list) {
		return 0;
	};

	/**
	 * 按照商户id获取键的组装值
	 * @return 组装后的建
	 */
	String getKey();

	/**
	 * 初始化实现
	 */
	default void init() {};

	/**
	 * 参数校验
	 * @param params
	 * @return
	 */
	default boolean checkParam(Object... params) {
		if (params == null) {
			return false;
		}

		for (final Object val : params) {
			if (ObjectUtils.isEmpty(val)) {
				return false;
			}
		}
		return true;
	}
}
