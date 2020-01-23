package com.spryntas.service;

import java.util.List;

import com.spryntas.domain.Holiday;

public interface HolidayService {

	List<Holiday> retrieveByEvent(String eventName);
	Holiday retrieveASingleData(String eventName);
	Holiday addHoliday(Holiday holidays);

	void updateHoliday(Integer holidayId, Holiday holidays);

	void deleteHolidayrecord(Integer holidayId);

}
