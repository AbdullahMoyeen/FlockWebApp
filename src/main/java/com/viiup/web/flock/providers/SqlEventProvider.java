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
    public List<RefEventCategoryModel> getRefEventCategoryList(){

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        String sql = "SELECT event_category " +
                "  FROM t_ref_event_category " +
                " ORDER BY event_category";
        List refEventCategoryList = jdbcTemplate.query(sql, new RefEventCategoryRowMapper());

        return refEventCategoryList;
    }

    @Override
    public EventModel getEventByEventId(int eventId) {

        try{
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

            String sql = "SELECT e.event_id, " +
                    "e.group_id, " +
                    "e.event_name, " +
                    "e.event_description, " +
                    "e.event_start_datetime, " +
                    "e.event_end_datetime, " +
                    "e.event_address_line1, " +
                    "e.event_address_line2, " +
                    "e.event_city, " +
                    "e.event_state_code, " +
                    "e.event_postal_code, " +
                    "e.event_keywords, " +
                    "e.event_latitude, " +
                    "e.event_longitude, " +
                    "IFNULL(e.private_event_ind, 'N'), " +
                    "e.create_user, " +
                    "e.create_date, " +
                    "e.update_user, " +
                    "e.update_date, " +
                    "e.event_category, " +
                    "IFNULL(eac.attendeeCount, 0) As attendeeCount, " +
                    "g.group_name " +
                    "FROM t_event e " +
                    "JOIN t_group g ON  e.group_id = g.group_id " +
                    "LEFT OUTER JOIN (SELECT eurc.event_id, SUM(CASE eurc.rsvp_type_code " +
                    "WHEN 'Y' THEN 1 " +
                    "ELSE 0 " +
                    "END) AS attendeeCount " +
                    "FROM t_event_user_rsvp eurc ) eac ON e.event_id = eac.event_id " +
                    "WHERE e.event_id = ? ";

            EventModel event = jdbcTemplate.queryForObject(sql, new Object[] {eventId}, new EventRowMapper());

            return event;

        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<UserEventModel> getEventsByUserId(int userId) {

        try{
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
            StringBuilder sql = new StringBuilder();

            sql.append("  SELECT eu.event_id\n");
            sql.append("        ,eu.group_id\n");
            sql.append("        ,eu.event_name\n");
            sql.append("        ,eu.event_description\n");
            sql.append("        ,eu.event_start_datetime\n");
            sql.append("        ,eu.event_end_datetime\n");
            sql.append("        ,eu.event_address_line1\n");
            sql.append("        ,eu.event_address_line2\n");
            sql.append("        ,eu.event_city\n");
            sql.append("        ,eu.event_state_code\n");
            sql.append("        ,eu.event_postal_code\n");
            sql.append("        ,eu.event_keywords\n");
            sql.append("        ,eu.event_latitude\n");
            sql.append("        ,eu.event_longitude\n");
            sql.append("        ,eu.private_event_ind\n");
            sql.append("        ,eu.create_user\n");
            sql.append("        ,eu.create_date\n");
            sql.append("        ,eu.update_user\n");
            sql.append("        ,eu.update_date\n");
            sql.append("        ,eu.event_category\n");
            sql.append("        ,eu.attendee_count\n");
            sql.append("        ,eu.group_name\n");
            sql.append("        ,eu.user_id\n");
            sql.append("        ,eu.rsvp_type_code\n");
            sql.append("    FROM (SELECT e.event_id\n");
            sql.append("                ,e.group_id\n");
            sql.append("                ,e.event_name\n");
            sql.append("                ,e.event_description\n");
            sql.append("                ,e.event_start_datetime\n");
            sql.append("                ,e.event_end_datetime\n");
            sql.append("                ,e.event_address_line1\n");
            sql.append("                ,e.event_address_line2\n");
            sql.append("                ,e.event_city\n");
            sql.append("                ,e.event_state_code\n");
            sql.append("                ,e.event_postal_code\n");
            sql.append("                ,e.event_keywords\n");
            sql.append("                ,e.event_latitude\n");
            sql.append("                ,e.event_longitude\n");
            sql.append("                ,IFNULL(e.private_event_ind, 'N') AS private_event_ind\n");
            sql.append("                ,e.create_user\n");
            sql.append("                ,e.create_date\n");
            sql.append("                ,e.update_user\n");
            sql.append("                ,e.update_date\n");
            sql.append("                ,e.event_category\n");
            sql.append("                ,IFNULL(eac.attendee_count, 0) AS attendee_count\n");
            sql.append("                ,g.group_name\n");
            sql.append("                ,gu.user_id user_id\n");
            sql.append("                ,IFNULL(eur.rsvp_type_code,'N') AS rsvp_type_code\n");
            sql.append("            FROM t_event e\n");
            sql.append("            JOIN t_group g ON e.group_id = g.group_id\n");
            sql.append("            JOIN t_group_user gu ON gu.group_id = e.group_id\n");
            sql.append("            LEFT OUTER JOIN (SELECT eurc.event_id\n");
            sql.append("                                   ,SUM(CASE eurc.rsvp_type_code\n");
            sql.append("                                          WHEN 'Y' THEN 1\n");
            sql.append("                                          ELSE 0 \n");
            sql.append("                                        END) AS attendee_count\n");
            sql.append("                               FROM t_event_user_rsvp eurc\n");
            sql.append("                              GROUP BY eurc.event_id) eac ON e.event_id = eac.event_id\n");
            sql.append("            LEFT OUTER JOIN t_event_user_rsvp eur ON eur.event_id = e.event_id AND gu.user_id = eur.user_id\n");
            sql.append("           WHERE group_membership_status = 'A'\n");
            sql.append("             AND e.private_event_ind = 'Y'\n");
            sql.append("             AND gu.user_id = ?\n");
            sql.append("          UNION ALL\n");
            sql.append("          SELECT e.event_id\n");
            sql.append("                ,e.group_id\n");
            sql.append("                ,e.event_name\n");
            sql.append("                ,e.event_description\n");
            sql.append("                ,e.event_start_datetime\n");
            sql.append("                ,e.event_end_datetime\n");
            sql.append("                ,e.event_address_line1\n");
            sql.append("                ,e.event_address_line2\n");
            sql.append("                ,e.event_city\n");
            sql.append("                ,e.event_state_code\n");
            sql.append("                ,e.event_postal_code\n");
            sql.append("                ,e.event_keywords\n");
            sql.append("                ,e.event_latitude\n");
            sql.append("                ,e.event_longitude\n");
            sql.append("                ,IFNULL(e.private_event_ind, 'N') AS private_event_ind\n");
            sql.append("                ,e.create_user\n");
            sql.append("                ,e.create_date\n");
            sql.append("                ,e.update_user\n");
            sql.append("                ,e.update_date\n");
            sql.append("                ,e.event_category\n");
            sql.append("                ,IFNULL(eac.attendee_count, 0) AS attendee_count\n");
            sql.append("                ,g.group_name\n");
            sql.append("                ,u.user_id\n");
            sql.append("                ,IFNULL(eur.rsvp_type_code,'N') As rsvp_type_code\n");
            sql.append("            FROM t_event e \n");
            sql.append("            JOIN t_group g ON e.group_id = g.group_id\n");
            sql.append("            JOIN t_user u ON u.user_id = ?\n");
            sql.append("            LEFT OUTER JOIN (SELECT eurc.event_id\n");
            sql.append("                                   ,SUM(CASE eurc.rsvp_type_code\n");
            sql.append("                                          WHEN 'Y' THEN 1\n");
            sql.append("                                          ELSE 0 \n");
            sql.append("                                        END) AS attendee_count\n");
            sql.append("                               FROM t_event_user_rsvp eurc\n");
            sql.append("                              GROUP BY eurc.event_id) eac ON e.event_id = eac.event_id\n");
            sql.append("            LEFT OUTER JOIN t_event_user_rsvp eur ON eur.event_id = e.event_id AND u.user_id = eur.user_id\n");
            sql.append("           WHERE IFNULL(e.private_event_ind, 'N') = 'N') eu\n");
            sql.append("   WHERE eu.event_end_datetime > current_timestamp\n");
            sql.append("ORDER BY eu.event_start_datetime\n");
            sql.append("        ,eu.event_name");

            List UserEventList = jdbcTemplate.query(sql.toString(), new Object[]{userId, userId}, new UserEventRowMapper());

            return UserEventList;

        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public void insertEventUserRsvp(EventUserRsvpModel eventUserRsvp) {

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        String sql = "INSERT INTO t_event_user_rsvp " +
                "(event_id " +
                ",user_id " +
                ",rsvp_type_code) " +
                "VALUES (?, ?, ?) ";

        jdbcTemplate.update(sql.toString(), new Object[]{eventUserRsvp.getEventId(), eventUserRsvp.getUserId(), eventUserRsvp.getRsvpTypeCode()});
    }

    @Override
    public void deleteEventUserRsvp(int userId, int eventId) {

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        String sql = "DELETE FROM t_event_user_rsvp " +
                "WHERE " +
                "user_id = ? " +
                "AND event_id = ? " ;
        jdbcTemplate.update(sql.toString(), new Object[]{userId, eventId});
    }

    @Override
    public Boolean isEventUserRsvpExist(int userId, int eventId) {

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        String sql = "SELECT COUNT(*) FROM t_event_user_rsvp " +
                "WHERE " +
                "user_id = ? " +
                "AND event_id = ? " ;
        int x = jdbcTemplate.queryForObject(sql.toString(), new Object[]{userId, eventId}, Integer.class);

        if (x > 0) {
            return true;
        } else {
            return false;
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
                ",event_longitude" +
                ",event_category) " +
                "VALUES (?, UPPER(?), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";

        jdbcTemplate.update(sql, new Object[]{event.getGroupId(), event.getIsPrivateEvent() ? "Y" : null, event.getEventName(),event.getEventDescription(),event.getEventKeywords(), event.getEventStartDatetime(), event.getEventEndDatetime(), event.getEventAddressLine1(), event.getEventAddressLine2(), event.getEventCity(), event.getEventStateCode(), event.getEventPostalCode(), event.getEventLatitude(), event.getEventLongitude(),event.getEventCategory()});

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
        sql.append("    ,event_category = ?\n");
        sql.append("    WHERE event_id = ?\n");

        jdbcTemplate.update(sql.toString(), new Object[]{event.getGroupId(),"FLOCK_DEV_USER", event.getIsPrivateEvent() ? "Y" : null, event.getEventName(), event.getEventDescription(),
        event.getEventKeywords(), event.getEventStartDatetime(), event.getEventEndDatetime(), event.getEventAddressLine1(), event.getEventAddressLine2(),
                event.getEventCity(), event.getEventStateCode(),event.getEventPostalCode(), event.getEventLatitude(),event.getEventLongitude(), event.getEventCategory(),event.getEventId()});
    }
}
