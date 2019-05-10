package com.llm.Dao;

import com.llm.common.Sex;
import com.llm.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Collections;
import java.util.List;

/**
 * @description:
 * @author: luolm
 * @createTime： 2019/5/10
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class UserRepositoryImpl implements UserCustomRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Page<User> search(Integer age, String addressCode, Sex sex, Pageable pageRequest) {
        String querySql = "select t ";
        String countSql = "select count(t) ";
        StringBuffer stringBuffer = new StringBuffer(" from User t where 1=1");
        if (null != age) {
            stringBuffer.append(" and t.age=:age");
        }
        if (null != addressCode) {
            stringBuffer.append(" and t.addressCode=:addressCode");
        }
        if (null != sex) {
            stringBuffer.append(" and t.sex=:sex");
        }
        querySql+=stringBuffer.toString();
        countSql+=stringBuffer.toString();
        Query query = em.createQuery(querySql);
        Query countQuery = em.createQuery(countSql);
        if (null != age) {
            query.setParameter("age",age);
            countQuery.setParameter("age",age);
        }
        if (null != addressCode) {
            query.setParameter("addressCode",addressCode);
            countQuery.setParameter("addressCode",addressCode);
        }
        if (null != sex) {
            query.setParameter("sex",sex);
            countQuery.setParameter("sex",sex);
        }

        Page<User> page=(pageRequest ==null)?new PageImpl<User>(query.getResultList()):this.readPage(query,countQuery, pageRequest);
        return page;
    }
    private Page<User> readPage(Query dataQuery,Query countQuery,Pageable pageable){
        dataQuery.setFirstResult(pageable.getOffset());
        dataQuery.setMaxResults(pageable.getPageSize());
        long total=(Long) countQuery.getSingleResult();
        List<User> content=total>(long) pageable.getOffset()?dataQuery.getResultList(): Collections.emptyList();

        return new PageImpl<User>(content,pageable,total);
    }
}
