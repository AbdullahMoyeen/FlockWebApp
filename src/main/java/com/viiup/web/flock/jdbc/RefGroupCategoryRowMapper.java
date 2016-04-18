package com.viiup.web.flock.jdbc;

import com.viiup.web.flock.models.RefEventCategoryModel;
import com.viiup.web.flock.models.RefGroupCategoryModel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by AbdullahMoyeen on 1/27/2016.
 */
public class RefGroupCategoryRowMapper implements RowMapper<RefGroupCategoryModel> {
    @Override
    public RefGroupCategoryModel mapRow(ResultSet resultSet, int line) throws SQLException {
        RefGroupCategoryExtractor refEventCategoryExtractor = new RefGroupCategoryExtractor();
        return refEventCategoryExtractor.extractData(resultSet);
    }
}
