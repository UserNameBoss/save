package com.kg.konggang_guide.other.utils;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.kg.konggang_guide.other.bean.AddressToBean;

import java.sql.SQLException;
import java.util.List;

/**
 * @author wuwang
 * @Description
 * @email 1558183202@qq.com
 * @date 2017/8/16
 */

public class AddressToDao {
    private Dao<AddressToBean, Integer> articleDaoOpe;
    private DatabaseHelper helper;

    @SuppressWarnings("unchecked")
    public AddressToDao(Context context)
        {
            try
            {
                helper = DatabaseHelper.getHelper(context);
                articleDaoOpe = helper.getDao(AddressToBean.class);
            } catch (android.database.SQLException e)
            {
                e.printStackTrace();
            }
        }

        /**
         * 添加一个Article
         * @param article
         */
    public void add(AddressToBean article)
    {
        try
        {
            articleDaoOpe.create(article);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 通过Id得到一个Article
     * @param id
     * @return
     */
    @SuppressWarnings("unchecked")
    public AddressToBean getArticleWithUser(int id)
    {
        AddressToBean article = null;
        try
        {
            article = articleDaoOpe.queryForId(id);
           // helper.getDao(AddressToBean.class).refresh(article.);

        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return article;
    }

    /**
     * 通过Id得到一篇文章
     * @param id
     * @return
     */
    public AddressToBean get(int id)
    {
        AddressToBean article = null;
        try
        {
            article = articleDaoOpe.queryForId(id);

        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return article;
    }

    /**
     * 通过UserId获取所有的文章
     * @param userId
     * @return
     */
    public List<AddressToBean> listByUserId(int userId)
    {
        try
        {
            return articleDaoOpe.queryBuilder().where().eq("user_id", userId)
                    .query();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
