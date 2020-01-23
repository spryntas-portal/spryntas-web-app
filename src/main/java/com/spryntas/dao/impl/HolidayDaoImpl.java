package com.spryntas.dao.impl;

import java.sql.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.spryntas.dao.HolidayDao;
import com.spryntas.domain.Holiday;

@Repository(value="HolidayDao")
public class HolidayDaoImpl implements HolidayDao {

	private static final Logger LOGGER = LogManager.getLogger(HolidayDaoImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	public NamedParameterJdbcTemplate parameterJdbcTemplate;


	@Override
	public List<Holiday> retrieveByEvent(String eventName) {
		List<Holiday> holiday = null;
	
		StringBuilder Holiday_sql = new StringBuilder();
		
		Holiday_sql.append(" select holiday_id,holiday_date,event_name,locality,created,updated,deleted from holiday");

		if (eventName != null) {
			
			Holiday_sql.append(" where event_name =?;");

			try {
				holiday = jdbcTemplate.query(Holiday_sql.toString(), new Object[] { eventName },
						new BeanPropertyRowMapper<Holiday>(Holiday.class));
				
//				holiday = parameterJdbcTemplate.query(Holiday_sql.toString(), map.addValue("name", eventName),
//				new BeanPropertyRowMapper<Holiday>(Holiday.class));
				
			} catch (Exception e) {
				LOGGER.error("error occured from Retriving date details");
			}
		} else
			try {
				holiday = jdbcTemplate.query(Holiday_sql.toString(), new BeanPropertyRowMapper<Holiday>(Holiday.class));
			} catch (DataAccessException e) {
				LOGGER.error("error occured from Retriving date details");
			}

		return holiday;
	}
	@Override
	public Holiday retrieveASingleData(String eventName) {
		Holiday holiday = null;
		StringBuilder Holiday_sql = new StringBuilder();
		
		Holiday_sql.append(" select holiday_id,holiday_date,event_name,locality,created,updated,deleted from holiday");

		if (eventName != null) {
			
			Holiday_sql.append(" where event_name like '%:name%';");
			
			System.out.println(Holiday_sql.toString());
			try {
//				holiday = jdbcTemplate.query(Holiday_sql.toString(), new Object[] { eventName },
//						new BeanPropertyRowMapper<Holiday>(Holiday.class));
				
				holiday = jdbcTemplate.queryForObject(Holiday_sql.toString(), new Object[] { eventName},
						new BeanPropertyRowMapper<Holiday>(Holiday.class));
			} catch (Exception e) {
				LOGGER.error("error occured from Retriving date details");
			}}
			return holiday;
			}

	@Override
	public Holiday retrieveASingleData(Date holidayDate) {
		Holiday holiday = null;

		StringBuilder Holiday_sql = new StringBuilder();
		Holiday_sql.append(" select holiday_id,holiday_date,event_name,locality,created,updated,deleted from holiday");

		if (holidayDate != null) {
			
			Holiday_sql.append(" where holiday_date=? and deleted is null");

			try {
				holiday = jdbcTemplate.queryForObject(Holiday_sql.toString(), new Object[] { holidayDate },
						new BeanPropertyRowMapper<Holiday>(Holiday.class));
			} catch (DataAccessException e) {
				LOGGER.error("error occured in Retriving single data date details");
			}
		}

		return holiday;
	}

	@Override
	public Holiday addHoliday(Holiday holidays) {

		String insert_sql = " insert into holiday (holiday_date,event_name,locality,created) value(?,?,?,Now())";
		try {
			jdbcTemplate.update(insert_sql,
					new Object[] { holidays.getHolidayDate(), holidays.getEventName(), holidays.getLocality() });
		} catch (Exception ex) {
			LOGGER.error("error occured in adding date details");

		}
		return retrieveASingleData(holidays.getHolidayDate()) != null ? retrieveASingleData(holidays.getHolidayDate())
				: null;
	}

	@Override
	public void updateHoliday(Integer holidayId, Holiday holidays) {
		String update_sql = " update holiday set event_name=?,holiday_date=?,locality=?,updated=now() where holiday_id=?";
		try {
			jdbcTemplate.update(update_sql, new Object[] { holidays.getEventName(), holidays.getHolidayDate(),
					holidays.getLocality(), holidayId });
		} catch (DataAccessException e) {
			LOGGER.error("error occured in updating date details");

		}
	}

	@Override
	public void deleteHolidayrecord(Integer holidayId) {
		String delete_sql = " update holiday set deleted=now() where holiday_id=?";
		try {
			jdbcTemplate.update(delete_sql, new Object[] { holidayId });
		} catch (DataAccessException e) {
			LOGGER.error("error occured in delete date details");

		}
	}
}
