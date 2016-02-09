package com.viiup.web.flock.providers;

import com.viiup.web.flock.jdbc.*;
import com.viiup.web.flock.models.*;
import com.viiup.web.flock.providers.interfaces.IEventProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by HP on 1/27/2016.
 */
@Service
public class SqlEventProvider implements IEventProvider {

    @Autowired
    DataSource dataSource;

    @Override
    public List<RefStateModel> getRefStateList(){

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        String sql = "SELECT state_code, state_name, concat(state_code, '-', state_name) AS state_code_and_name " +
                "  FROM t_ref_state " +
                " ORDER BY state_code";
        List refStateList = jdbcTemplate.query(sql, new RefStateRowMapper());

        return refStateList;
    }

    @Override
    public EventModel getEventByEventId(int eventId) {

        try{
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

            String sql = "SELECT event_id, " +
                    "group_id, " +
                    "event_name, " +
                    "event_description, " +
                    "event_start_datetime, " +
                    "event_end_datetime, " +
                    "event_address_line1, " +
                    "event_address_line2, " +
                    "event_city, " +
                    "event_state_code, " +
                    "event_postal_code, " +
                    "event_keywords, " +
                    "event_latitude, " +
                    "event_longitude, " +
                    "IFNULL(private_event_ind, 'N'), " +
                    "create_user, " +
                    "create_date, " +
                    "update_user, " +
                    "update_date " +
                    "FROM t_event " +
                    "WHERE event_id = ?";

            EventModel event = jdbcTemplate.queryForObject(sql, new Object[] {eventId}, new EventRowMapper());

            return event;

        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public void insertEvent(EventModel event) {

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        String sql = "INSERT INTO t_event " +
                "(group_id " +
                ",private_event_ind " +
                ",event_name " +
                ",event_description " +
                ",event_keywords " +
                ",event_start_datetime " +
                ",event_end_datetime " +
                ",event_address_line1 " +
                ",event_address_line2 " +
                ",event_city " +
                ",event_state_code " +
                ",event_postal_code " +
                ",event_latitude " +
                ",event_longitude) " +
                "VALUES (?, UPPER(?), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";

        jdbcTemplate.update(sql, new Object[]{event.getGroupId(), event.isPrivateEvent() ? "Y" : null, event.getEventName(),event.getEventDescription(),event.getEventKeywords(), event.getEventStartDatetime(), event.getEventEndDatetime(), event.getEventAddressLine1(), event.getEventAddressLine2(), event.getEventCity(), event.getEventStateCode(), event.getEventPostalCode(), event.getEventLatitude(), event.getEventLongitude()});
    }

    @Override
    public void updateEvent (final EventModel event) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        StringBuilder sql = new StringBuilder();

        sql.append( "UPDATE t_event\n ");
        sql.append(    "SET group_id = ?\n");
        sql.append("    ,update_user = ?\n");
        sql.append("    ,update_date = current_timestamp\n");
        sql.append("    ,private_event_ind = UPPER(?)\n");
        sql.append("    ,event_name = ?\n");
        sql.append("    ,event_description = ?\n");
        sql.append("    ,event_keywords = ?\n");
        sql.append("    ,event_start_datetime = ?\n");
        sql.append("    ,event_end_datetime = ?\n");
        sql.append("    ,event_address_line1 = ?\n");
        sql.append("    ,event_address_line2 = ?\n");
        sql.append("    ,event_city = ?\n");
        sql.append("    ,event_state_code = UPPER(?)\n");
        sql.append("    ,event_postal_code = UPPER(?)\n");
        sql.append("    ,event_latitude = ?\n");
        sql.append("    ,event_longitude = ?\n");
        sql.append("    WHERE event_id = ?\n");

        jdbcTemplate.update(sql.toString(), new Object[]{event.getGroupId(),"FLOCK_DEV_USER", event.isPrivateEvent() ? "Y" : null, event.getEventName(), event.getEventDescription(),
        event.getEventKeywords(), event.getEventStartDatetime(), event.getEventEndDatetime(), event.getEventAddressLine1(), event.getEventAddressLine2(),
                event.getEventCity(), event.getEventStateCode(),event.getEventPostalCode(), event.getEventLatitude(),event.getEventLongitude(), event.getEventId()});
    }
}
