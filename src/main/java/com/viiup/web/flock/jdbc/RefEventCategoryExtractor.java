package com.viiup.web.flock.jdbc;

import com.viiup.web.flock.models.RefEventCategoryModel;
import com.viiup.web.flock.models.RefStateModel;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by AbdullahMoyeen on 1/27/2016.
 */
public class RefEventCategoryExtractor implements ResultSetExtractor<RefEventCategoryModel> {

    public RefEventCategoryModel extractData(ResultSet resultSet) throws SQLException, DataAccessException {

        RefEventCategoryModel refEventCategory = new RefEventCategoryModel();

        refEventCategory.setEventCategory(resultSet.getString(1));

        return refEventCategory;
    }
}
