package com.luke.web.repo.dao;

import com.luke.web.tool.exception.AppException;
import com.luke.web.vo.Page;
import com.luke.web.vo.VORedisUser;

import javax.persistence.Query;
import java.util.List;

 public interface IDBDao {



     VORedisUser getRedisUser(String key) throws AppException ;

    /** *************************************redis***************************************************************************/

    /**
     * 以key 从redis里取值
     * @param key
     * @param <T>
     * @return
     * @throws AppException
     */
     <T> T getRedisValue(String key) throws AppException ;

    /**
     *  保存 key  与 val 到redis
     * @param key
     * @param val
     * @param <T>
     * @return
     * @throws AppException
     */
     <T> T setRedisValue(String key,T val) throws AppException ;

    /**
     * 删除指定 key
     * @param key
     * @return
     * @throws AppException
     */
     Boolean delRedisValueByKey(String key) throws AppException ;

    /**
     * 保存 key 与 val 到redis 并设置过期时间 时间为分钟
     * @param key
     * @param val
     * @param time
     * @param <T>
     * @return
     * @throws AppException
     */
     <T> T setRedisValueAndEX(String key ,T val ,Long time) throws AppException ;
    /** *******************************************jdbc*********************************************************************/

     String jdbcTemplateUpdate(String sql,Object[] objs)throws AppException ;
    /** ****************************************************JPA************************************************************/
    /**
     * hibernate session save ;
     * @param obj
     * @param <T>
     * @return
     */
     <T> T save(T obj) throws AppException ;

    /**
     * hibernate ql　删除<br>
     * this.delete_ql("delete from TG_Price p where p.goods.id=:goodsId", vo) ; vo中要有goodsId属性并且有值
     * @param ql
     * @param param
     * @return
     * @throws AppException
     */
     boolean delete_hql(String ql,Object param) throws AppException ;

    /**
     * this.delete_jdbc("delete from tg_price where goodsId=?",vo.getGoodsId()) ;
     * @param sql
     * @param params
     * @return
     * @throws AppException
     */
     boolean delete_jdbc(String sql,Object... params) throws AppException ;

    /**
     * hibernate qh 更新
     * @param ql
     * @param param
     * @return
     * @throws AppException
     */
     boolean update_ql(String ql,Object param) throws AppException ;




    /**
     * hibernate session 更新对象
     * @param obj
     * @param <T>
     * @return
     * @throws AppException
     */
     <T> T updateObj(T obj) throws AppException ;

    /**
     * hibernate session get+update
     * @param clss
     * @param id
     * @param val
     * @param <T>
     * @param <C>
     * @return
     * @throws AppException
     */
     <T,C> T updateObj(Class<T> clss ,Long id,C val) throws AppException;



    /**
     * hibernate 批量保存
     * @param list
     * @param <T>
     * @return
     * @throws AppException
     */
     <T> List<T> saveAll(List<T> list) throws AppException;

    /**
     * 以条件查询唯一值,可以返回null 值
     * @param ql
     * @param param
     * @param <T>
     * @return
     * @throws AppException
     */
     <T> T getUnique(String ql ,Object param) throws AppException;

    /**
     * 以条件查询唯一值,可以返回null 值
     * @param ql
     * @param param
     * @param <T>
     * @return
     * @throws AppException
     */
     <T> T getUnique(String ql ,Object param,Class<T> toBean) throws AppException;
    /**
     * hibernate session ql查询 <br>  注意，toBean与changeMap都为空时，查询出映射类的列表 toBean与changeMap不能同时使用
     * @param ql
     * @param param
     * @param page
     * @param toBean
     * @param changeMap
     * @param <T>
     * @return
     * @throws AppException
     */
     <T> List<T> find(String ql , Object param , Page page, Class<T> toBean, Boolean changeMap) throws AppException;

    /**
     * hibernate session ql查询 <br>  注意，toBean与changeMap都为空时，查询出映射类的列表 toBean与changeMap不能同时使用
     * @param ql
     * @param <T>
     * @return
     * @throws AppException
     */
     <T> List<T> find(String ql) throws AppException;

    /**
     * 查询数据
     * @param ql
     * @param toBean
     * @param <T>
     * @return
     * @throws AppException
     */
     <T> List<T> find(String ql ,Class toBean ) throws AppException;
    /**
     * hibernate session ql查询   <br>  注意 这是直接查询出映射类的列表
     * @param ql
     * @param param
     * @param <T>
     * @return
     * @throws AppException
     */
     <T> List<T> find(String ql ,Object param ) throws AppException;

    /**
     * hibernate session ql查询
     * @param ql
     * @param param
     * @param page
     * @param <T>
     * @return
     * @throws AppException
     */
     <T> List<T> find(String ql ,Object param ,Page page) throws AppException;

    /**
     * 可能返回null值
     * @param clss
     * @param id
     * @param <T>
     * @return
     * @throws AppException
     */
     <T> T get(Class<T> clss, Long id) throws AppException ;

    <T> T update(T obj) throws AppException ;
    /**
     * hibernate session 软删除对象
     * @param obj
     * @param <T>
     * @return
     * @throws AppException
     */
     <T> T delObj(T obj) throws AppException;
    /**
     * hibernate session 删除对象
     * @param obj
     * @param <T>
     * @return
     * @throws AppException
     */
     <T> T delObject(T obj) throws AppException;
}
