package com.luke.web.repo.dao;

import com.luke.web.model.U_Staff;
import com.luke.web.model._M;
import com.luke.web.tool.Assertion;
import com.luke.web.tool.LK;
import com.luke.web.tool.LKMap;
import com.luke.web.tool.exception.AppException;
import com.luke.web.vo.Page;
import com.luke.web.vo.VORedisUser;
import com.luke.web.vo.login.VOoutInfo;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
public class DBDao {

    private static final Logger logger = LoggerFactory.getLogger(DBDao.class) ;

    @PersistenceContext(name="entityManagerFactory")
    private EntityManager em;

    EntityManager getSession(){
        return this.em ;
    }

    @Resource
    private JdbcTemplate jdbcTemplate ;

    private RedisTemplate redisTemplate;

    @Resource
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        RedisSerializer stringSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setValueSerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
        redisTemplate.setHashValueSerializer(stringSerializer);
        this.redisTemplate = redisTemplate;

    }

    Query setParams(final Query query , Object obj)throws AppException{
        try{
            if(obj==null) return query ;
            Map<String,Object> params = null ;
            if(!(obj instanceof Map)){
                params = LK.ObjToMap(obj,new LKMap<>().put1("srcUrl","srcUrl").put1("loginTuken","loginTuken").put1("loginComId","loginComId")) ;
            }
            if(obj instanceof Map){
                params = (Map<String,Object>)obj ;
            }
            final Map<String,Object> lmdParams = params ;
            /**使用lmd表达式来写参数，这个可以理解成一个没有方法名的方法，key是参数，{}里是方法体*/
            lmdParams.keySet().forEach(key->{
                if(lmdParams.get(key)!=null){
                    query.setParameter(key,lmdParams.get(key)) ;
                }

            });
            return query ;
        }catch (Throwable e){
            throw AppException.create(this.getClass(),e.getMessage()) ;
        }

    }

    public VORedisUser getRedisUser(String key) throws AppException{
        String strRedisUser = this.getRedisValue(key) ;
        VORedisUser voRedisUser = (VORedisUser) JSONObject.toBean(JSONObject.fromObject(strRedisUser), VORedisUser.class);
        return voRedisUser ;
    }

    /** *************************************redis***************************************************************************/

    /**
     * 以key 从redis里取值
     * @param key
     * @param <T>
     * @return
     * @throws AppException
     */
    public <T> T getRedisValue(String key) throws AppException{
        try{
            if(!this.redisTemplate.hasKey(key)) Assertion.Error("redis不存在"+key+"数据");
            return  (T)this.redisTemplate.opsForValue().get(key) ;
        }catch (Throwable e){
            throw AppException.create(this.getClass(),e.getMessage()) ;
        }

    }

    /**
     *  保存 key  与 val 到redis
     * @param key
     * @param val
     * @param <T>
     * @return
     * @throws AppException
     */
    public <T> T setRedisValue(String key,T val) throws AppException {
        try{
            if(this.redisTemplate.hasKey(key)) Assertion.Error("redis中存在"+key+"数据");
            this.redisTemplate.opsForValue().set(key,val);
            return val ;
        }catch (Throwable e){
            throw AppException.create(this.getClass(),e.getMessage()) ;
        }

    }

    /**
     * 删除指定 key
     * @param key
     * @return
     * @throws AppException
     */
    public Boolean delRedisValueByKey(String key) throws AppException{
        try{
            if(this.redisTemplate.hasKey(key)){
                this.redisTemplate.delete(key);
                return true ;
            }else{
                return true ;
            }
        }catch (Throwable e){
            throw AppException.create(this.getClass(),e.getMessage()) ;
        }

    }

    /**
     * 保存 key 与 val 到redis 并设置过期时间 时间为分钟
     * @param key
     * @param val
     * @param time
     * @param <T>
     * @return
     * @throws AppException
     */
    public <T> T setRedisValueAndEX(String key ,T val ,Long time) throws AppException{
        try{
            this.redisTemplate.opsForValue().set(key,val);
            this.redisTemplate.expire(key, time, TimeUnit.MINUTES) ;
            return val ;
        }catch (Throwable e){
            throw AppException.create(this.getClass(),e.getMessage()) ;
        }

    }
    /** *******************************************jdbc*********************************************************************/
    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public String jdbcTemplateUpdate(String sql,Object[] objs)throws AppException{
        try{
            logger.info("jdbc sql is ->:"+sql);
            for(Object obj :objs){
                logger.info("jdbc sql param is ->:"+obj.toString());
            };
            this.jdbcTemplate.update(sql,objs) ;
            return "success" ;
        }catch (Throwable e){
            throw AppException.create(this.getClass(),e.getMessage()) ;
        }

    }
    /** ****************************************************JPA************************************************************/
    /**
     * hibernate session save ;
     * @param obj
     * @param <T>
     * @return
     */
    public <T> T save(T obj) throws AppException{
        try{
            if(obj==null) Assertion.Error("保存对象为空");
            if(obj instanceof _M){
                _M m = (_M)obj ;
                m.setB_isDel(false);
                m.setB_wtime(new Date());
            }
            this.getSession().persist(obj);
            return obj ;
        }catch (Throwable e){
            throw AppException.create(this.getClass(),e.getMessage()) ;
        }

    }

    /**
     * hibernate ql　删除<br>
     * this.delete_ql("delete from TG_Price p where p.goods.id=:goodsId", vo) ; vo中要有goodsId属性并且有值
     * @param ql
     * @param param
     * @return
     * @throws AppException
     */
    public boolean delete_hql(String ql,Object param) throws AppException{
        try{
            if(LK.StrIsEmpty(ql) ) Assertion.Error("delete_ql语句为空");
            Query query = this.getSession().createQuery(ql) ;
            this.setParams(query, param).executeUpdate() ;
            return true ;
        }catch (Throwable e){
            throw AppException.create(this.getClass(),e.getMessage()) ;
        }

    }

    /**
     * this.delete_jdbc("delete from tg_price where goodsId=?",vo.getGoodsId()) ;
     * @param sql
     * @param params
     * @return
     * @throws AppException
     */
    public boolean delete_jdbc(String sql,Object... params) throws AppException{
        try{
            if(LK.StrIsEmpty(sql) ) Assertion.Error("delete_sql语句为空");
            logger.info("jdbcTemplate sql is =>" + sql);
            this.jdbcTemplate.update(sql, params) ;
            return true ;
        }catch (Throwable e){
            throw AppException.create(this.getClass(),e.getMessage()) ;
        }

    }

    /**
     * hibernate qh 更新
     * @param ql
     * @param param
     * @return
     * @throws AppException
     */
    public boolean update_ql(String ql,Object param) throws AppException{
        try{
            if(LK.StrIsEmpty(ql) ) Assertion.Error("dupdate_ql语句为空");
            Query query = this.getSession().createQuery(ql) ;
            this.setParams(query, param).executeUpdate() ;
            return true ;
        }catch (Throwable e){
            throw AppException.create(this.getClass(),e.getMessage()) ;
        }
    }




    /**
     * hibernate session 更新对象
     * @param obj
     * @param <T>
     * @return
     * @throws AppException
     */
    public <T> T updateObj(T obj) throws AppException{
        if(obj instanceof _M){
            _M m = (_M) obj ;
            this.getSession().merge(obj);
        }else{
            throw AppException.create("对象不是映射对象");
        }
        return obj ;
    }

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
    public <T,C> T updateObj(Class<T> clss ,Long id,C val) throws AppException{
        try{
            T obj = this.getSession().find(clss, id) ;
            BeanUtils.copyProperties(val, obj);
            this.getSession().merge(obj);
            return obj ;
        }catch (Throwable e){
            throw AppException.create(this.getClass(),e.getMessage()) ;
        }

    }



    /**
     * hibernate 批量保存
     * @param list
     * @param <T>
     * @return
     * @throws AppException
     */
    public <T> List<T> saveAll(List<T> list) throws AppException{
        try{
            Date now = new Date() ;
            for(int i = 0 ;i<list.size();i++){
                if(i!=0&&i%20==0){
                    this.getSession().flush();
                }
                if(list.get(i) instanceof _M){
                    _M m = (_M)list.get(i) ;
                    m.setB_wtime(now);
                    m.setB_isDel(false);
                }
                this.save(list.get(i)) ;
            }
            this.getSession().flush();
            return list ;
        }catch (Throwable e){
            throw AppException.create(this.getClass(),e.getMessage()) ;
        }

    }

    /**
     * 以条件查询唯一值,可以返回null 值
     * @param ql
     * @param param
     * @param <T>
     * @return
     * @throws AppException
     */
    public <T> T getUnique(String ql ,Object param) throws AppException{
        try{
            if(LK.StrIsEmpty(ql)) Assertion.Error("getUnique查询语句为空");
            Query query = this.getSession().createQuery(ql) ;
            return (T) this.setParams(query, param).getSingleResult() ;
        }catch (Throwable e){
            throw AppException.create(this.getClass(),e.getMessage()) ;
        }

    }

    /**
     * 以条件查询唯一值,可以返回null 值
     * @param ql
     * @param param
     * @param <T>
     * @return
     * @throws AppException
     */
    public <T> T getUnique(String ql ,Object param,Class<T> toBean) throws AppException{
        try{
            if(LK.StrIsEmpty(ql)) Assertion.Error("getUnique查询语句为空");
            Query query = this.getSession().createQuery(ql, toBean) ;
            return (T)this.setParams(query,param).getSingleResult() ;
        }catch (Throwable e){
            throw AppException.create(this.getClass(),e.getMessage()) ;
        }

//      return (T)this.getSession().createQuery(ql).setResultTransformer(Transformers.aliasToBean(toBean)).setProperties(param).uniqueResult() ;

    }
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
    public <T> List<T> find(String ql , Object param , Page page, Class<T> toBean, Boolean changeMap) throws AppException{
        try{
            if(LK.StrIsEmpty(ql)) Assertion.Error("find查询语句为空");
            Query query = null ;
            if(LK.ObjIsNull(toBean)){
                query = this.getSession().createQuery(ql) ;
            }else{
                query = this.getSession().createQuery(ql,toBean) ;
            }

            query = this.setParams(query,param) ;
            if(LK.ObjIsNotNull(page)){
                query.setMaxResults(page.getLimit()) ;
                query.setFirstResult(page.getStart()) ;
            }
            return query.getResultList() ;
        }catch (Throwable e){
            throw AppException.create(this.getClass(),e.getMessage()) ;
        }

    }

    /**
     * hibernate session ql查询 <br>  注意，toBean与changeMap都为空时，查询出映射类的列表 toBean与changeMap不能同时使用
     * @param ql
     * @param <T>
     * @return
     * @throws AppException
     */
    public <T> List<T> find(String ql) throws AppException{
        try{
            return this.find(ql, null, null, null, null) ;
        }catch (Throwable e){
            throw AppException.create(this.getClass(),e.getMessage()) ;
        }

    }

    /**
     * 查询数据
     * @param ql
     * @param toBean
     * @param <T>
     * @return
     * @throws AppException
     */
    public <T> List<T> find(String ql ,Class toBean ) throws AppException{
        try{
            return this.find(ql, null, null, toBean, null) ;
        }catch (Throwable e){
            throw AppException.create(this.getClass(),e.getMessage()) ;
        }

    }
    /**
     * hibernate session ql查询   <br>  注意 这是直接查询出映射类的列表
     * @param ql
     * @param param
     * @param <T>
     * @return
     * @throws AppException
     */
    public <T> List<T> find(String ql ,Object param ) throws AppException{
        try{
            return this.find(ql, param, null, null, null) ;
        }catch (Throwable e){
            throw AppException.create(this.getClass(),e.getMessage()) ;
        }

    }

    /**
     * hibernate session ql查询
     * @param ql
     * @param param
     * @param page
     * @param <T>
     * @return
     * @throws AppException
     */
    public <T> List<T> find(String ql ,Object param ,Page page) throws AppException{
        try{
            return this.find(ql, param, page, null, null) ;
        }catch (Throwable e){
            throw AppException.create(this.getClass(),e.getMessage()) ;
        }

    }

    /**
     * 可能返回null值
     * @param clss
     * @param id
     * @param <T>
     * @return
     * @throws AppException
     */
    public <T> T get(Class<T> clss, Long id) throws AppException {
        try{
            return this.getSession().find(clss, id) ;
        }catch (Throwable e){
            throw AppException.create(this.getClass(),e.getMessage()) ;
        }

    }

    public<T> T update(T obj) throws AppException {
        try{
            return this.updateObj(obj) ;
        }catch (Throwable e){
            throw AppException.create(this.getClass(),e.getMessage()) ;
        }

    }
    /**
     * hibernate session 软删除对象
     * @param obj
     * @param <T>
     * @return
     * @throws AppException
     */
    public <T> T delObj(T obj) throws AppException{
        try{
            if(obj instanceof _M){
                _M m = (_M) obj;
                m.setB_isDel(true);
                this.getSession().merge(obj);
            }else{
                throw AppException.create("删除对象不是映射对象");
            }
            return obj ;
        }catch (Throwable e){
            throw AppException.create(this.getClass(),e.getMessage()) ;
        }
    }
    /**
     * hibernate session 删除对象
     * @param obj
     * @param <T>
     * @return
     * @throws AppException
     */
    public <T> T delObject(T obj) throws AppException{
        try{
            this.getSession().remove(obj);
            return obj ;
        }catch (Throwable e){
            throw AppException.create(this.getClass(),e.getMessage()) ;
        }

    }

    public U_Staff getLoginUser(String token) throws AppException{
        String usreInfo = this.getRedisValue(token) ;
        if(LK.StrIsEmpty(usreInfo))
            throw AppException.create("DBDao","token 已过期") ;
        VOoutInfo outInfo = LK.StrJson2Obj(usreInfo,VOoutInfo.class) ;
        U_Staff user = this.get(U_Staff.class,outInfo.getStaffId()) ;
        if(LK.ObjIsNull(user))
            throw AppException.create("DBDao","非法用户") ;
        return user ;
    }

}
