package com.spryntas.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spryntas.dao.HolidayDao;
import com.spryntas.domain.Holiday;
import com.spryntas.exception.BadRequestException;
import com.spryntas.service.HolidayService;

@Service
public class HolidayServiceImpl implements HolidayService {
	@Autowired
	private HolidayDao holidayDao;

	@Override
	public List<Holiday> retrieveByEvent(String eventName) {
		return holidayDao.retrieveByEvent(eventName);
	}

	@Override
	public Holiday retrieveASingleData(String eventName) {
		return holidayDao.retrieveASingleData(eventName);
	}
	@Override
	public Holiday addHoliday(Holiday holidays) {
		if (holidays.getEventName() == null)
			throw new BadRequestException("Event Name is empty");
		if (holidays.getHolidayDate() == null)
			throw new BadRequestException("Holiday Date is empty");
		if (holidays.getLocality() == null)
			throw new BadRequestException("Locality is empty");

		return holidayDao.addHoliday(holidays);
	}

	@Override
	public void updateHoliday(Integer holidayId, Holiday holidays) {
		if (holidays.getEventName() == null)
			throw new BadRequestException("Event Name is empty");
		if (holidays.getHolidayDate() == null)
			throw new BadRequestException("Holiday Date is empty");
		if (holidays.getLocality() == null)
			throw new BadRequestException("Locality is empty");
		holidayDao.updateHoliday(holidayId, holidays);
	}

	@Override
	public void deleteHolidayrecord(Integer holidayId) {
		holidayDao.deleteHolidayrecord(holidayId);
	}
}
