package com.viiup.web.flock.jdbc;

import com.viiup.web.flock.models.RefStateModel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by AbdullahMoyeen on 1/27/2016.
 */
public class RefStateRowMapper implements RowMapper<RefStateModel> {
    @Override
    public RefStateModel mapRow(ResultSet resultSet, int line) throws SQLException {
        RefStateExtractor refStateExtractor = new RefStateExtractor();
        return refStateExtractor.extractData(resultSet);
    }
}
