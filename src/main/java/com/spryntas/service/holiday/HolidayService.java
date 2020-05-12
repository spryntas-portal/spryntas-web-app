package com.spryntas.service.holiday;

import java.util.List;

import com.spryntas.model.Holiday;

public interface HolidayService {

	List<Holiday> retrieveByEvent(String eventName);
	Holiday retrieveASingleData(String eventName);
	Holiday addHoliday(Holiday holidays);

	void updateHoliday(Integer holidayId, Holiday holidays);

	void deleteHolidayrecord(Integer holidayId);

}
