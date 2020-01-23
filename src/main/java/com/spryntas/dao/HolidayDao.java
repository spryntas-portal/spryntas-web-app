
package com.spryntas.dao;

import java.sql.Date;
import java.util.List;

import com.spryntas.domain.Holiday;

public interface HolidayDao {
	List<Holiday> retrieveByEvent(String event_name);

	Holiday retrieveASingleData(Date holidayDate);
	Holiday retrieveASingleData(String event_name);
	Holiday addHoliday(Holiday holidays);

	void updateHoliday(Integer holidayId, Holiday holidays);

	void deleteHolidayrecord(Integer holidayId);

}
