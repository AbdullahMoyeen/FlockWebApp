package com.viiup.web.flock.jdbc;

import com.viiup.web.flock.models.RefEventCategoryModel;
import com.viiup.web.flock.models.RefStateModel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by AbdullahMoyeen on 1/27/2016.
 */
public class RefEventCategoryRowMapper implements RowMapper<RefEventCategoryModel> {
    @Override
    public RefEventCategoryModel mapRow(ResultSet resultSet, int line) throws SQLException {
        RefEventCategoryExtractor refEventCategoryExtractor = new RefEventCategoryExtractor();
        return refEventCategoryExtractor.extractData(resultSet);
    }
}
