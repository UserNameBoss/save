package com.kg.konggang_guide.other.utils;

import android.content.Context;
import android.database.SQLException;

import com.j256.ormlite.dao.Dao;
import com.kg.konggang_guide.other.bean.AddressFromBean;

import java.util.List;

/**
 * @author wuwang
 * @Description
 * @email 1558183202@qq.com
 * @date 2017/8/16
 */

public class AddressFromDao {
    private Context context;
    private Dao<AddressFromBean, Integer> userDaoOpe;
    private DatabaseHelper helper;

    public AddressFromDao(Context context)
    {
        this.context = context;
        try
        {
            helper = DatabaseHelper.getHelper(context);
            userDaoOpe = helper.getDao(AddressFromBean.class);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 增加一个用户
     * @param user
     */
    public void add(AddressFromBean user)
    {
        try
        {
            userDaoOpe.create(user);
        } catch (SQLException e)
        {
            e.printStackTrace();
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }

    public List<AddressFromBean> listByUserId(int userId)
    {
        try
        {
            return userDaoOpe.queryBuilder().where().eq("user_id", userId)
                    .query();
        } catch (SQLException e)
        {
            e.printStackTrace();
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
