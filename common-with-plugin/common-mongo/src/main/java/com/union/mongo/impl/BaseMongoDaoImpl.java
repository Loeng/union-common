package com.union.mongo.impl;

import com.github.pagehelper.PageInfo;
import com.mongodb.WriteResult;
import com.union.mongo.BaseMongoDao;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * mongoDB 操作基础实现类
 *
 * @author caizhaoming
 * @create 2018-08-27 14:36
 **/
@Repository
public class BaseMongoDaoImpl<T> implements BaseMongoDao<T> {

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 创建一个 Class 的对象来获取泛型的 Class
     */
    private Class<T> clz;

    public Class<T> getClz() {
        if (clz == null) {
            clz = ((Class<T>) (((ParameterizedType) (this.getClass ().getGenericSuperclass ())).getActualTypeArguments ()[0]));
        }
        return clz;
    }


    @Override
    public boolean removeById(String id) {
        Criteria criteria = Criteria.where ("_id").is (new ObjectId (id));
        Query query = new Query (criteria);
        WriteResult writeResult = this.mongoTemplate.remove (query, getClz ());
        return writeResult.getN () > 0 ? true : false;
    }

    @Override
    public T getById(String id) {
        Criteria criteria = Criteria.where ("_id").is (new ObjectId (id));
        Query query = new Query (criteria);
        return this.mongoTemplate.findOne (query, getClz ());
    }

    @Override
    public void insert(T o) {
        this.mongoTemplate.insert (o);
    }

    @Override
    public boolean updateById(Update update, String id) {
        Criteria criteria = Criteria.where ("_id").is (new ObjectId (id));
        Query query = new Query (criteria);
        WriteResult writeResult = this.mongoTemplate.updateFirst (query, update, getClz ());
        return writeResult.getN () > 0 ? true : false;
    }

    @Override
    public PageInfo<T> findByPageForFront(Integer pageNo, Integer pageSize, Query query) {
        pageNo = pageNo == null ? 1 : pageNo;
        pageSize = pageSize == null ? 10 : pageSize;
        long count = this.mongoTemplate.count (query, getClz ());
        query.skip ((pageNo - 1) * pageSize).limit (pageSize);
        List<T> list = this.mongoTemplate.find (query, getClz ());
        int pages = (int) (((count - 1) / pageSize) + 1);
        PageInfo<T> page = gettPageInfo (pageNo, pageSize, count, list, pages);
        return page;
    }

    @Override
    public PageInfo<T> findByPageForFront(Integer pageNo, Integer pageSize, Query query, String collectionName) {
        pageNo = pageNo == null ? 1 : pageNo;
        pageSize = pageSize == null ? 10 : pageSize;
        long count = this.mongoTemplate.count (query, getClz (), collectionName);
        query.skip ((pageNo - 1) * pageSize).limit (pageSize);
        List<T> list = this.mongoTemplate.find (query, getClz (), collectionName);
        int pages = (int) (((count - 1) / pageSize) + 1);
        PageInfo<T> page = gettPageInfo (pageNo, pageSize, count, list, pages);
        return page;
    }

    private PageInfo<T> gettPageInfo(Integer pageNo, Integer pageSize, long count, List<T> list, int pages) {
        PageInfo<T> page = new PageInfo ();
        page.setList (list);
        page.setPages (pages);
        page.setTotal (count);
        page.setIsFirstPage(pageNo == 1 ? true : false);
        page.setIsLastPage(pageNo == pages ? true : false);
        page.setHasNextPage(pageNo < pages ? true : false);
        page.setPageNum(pageNo);
        page.setPageSize(pageSize);
        page.setPrePage(pageNo > 1 ? pageNo - 1 : 1);
        page.setNextPage(pageNo < pages ? pageNo + 1 : pages);
        return page;
    }
}
