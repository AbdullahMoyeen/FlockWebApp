package com.viiup.web.flock.jdbc;

import com.viiup.web.flock.models.RefEventCategoryModel;
import com.viiup.web.flock.models.RefGroupCategoryModel;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by AbdullahMoyeen on 1/27/2016.
 */
public class RefGroupCategoryExtractor implements ResultSetExtractor<RefGroupCategoryModel> {

    public RefGroupCategoryModel extractData(ResultSet resultSet) throws SQLException, DataAccessException {

        RefGroupCategoryModel refGroupCategory = new RefGroupCategoryModel();

        refGroupCategory.setGroupCategory(resultSet.getString(1));

        return refGroupCategory;
    }
}
