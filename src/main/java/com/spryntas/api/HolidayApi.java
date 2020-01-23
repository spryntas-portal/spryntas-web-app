package com.spryntas.api;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spryntas.domain.Holiday;
import com.spryntas.service.HolidayService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/holiday")
@Api(value = "holiday")
public class HolidayApi {
	private static final Logger LOGGER = LogManager.getLogger(HolidayApi.class);
	@Autowired
	private HolidayService holidayService;

	@GetMapping
	@ApiOperation(value = "get the holiday details")

	public List<Holiday> retrieveByEvent(@RequestParam(value = "eventname", required = false) String eventName) {
		List<Holiday> holidaydetails = null;
		holidaydetails = holidayService.retrieveByEvent(eventName);
		LOGGER.info("retrieve the holidays datas");
		return holidaydetails;
	}
	@GetMapping("/name")
	@ApiOperation(value = "get the holiday details by event names")

	public Holiday retrieveASingleData(@RequestParam(value = "eventname", required = false) String eventName) {
		Holiday holidaydetails = null;
		holidaydetails = holidayService.retrieveASingleData(eventName);
		LOGGER.info("retrieve the holidays datas");
		return holidaydetails;
	}

	@PostMapping
	@ApiOperation(value = "insert the holiday details")
	public Holiday addHoliday(@RequestBody Holiday holidays) {
		LOGGER.info("insert the holidays details");

		return holidayService.addHoliday(holidays);

	}

	@PutMapping
	@ApiOperation(value = "update the holiday details")
	public void updateHoliday(@RequestParam(value = "id", defaultValue = "true") Integer holidayId,
			@RequestBody Holiday holidays) {
		LOGGER.info("update the holiday details");

		holidayService.updateHoliday(holidayId, holidays);
	}

	@DeleteMapping
	@ApiOperation(value = "delete the holiday details")
	@RequestMapping("/{id}")
	public void deleteHolidayrecord(@PathVariable("id") Integer holidayId) {
		LOGGER.info("delete the holidays details");

		holidayService.deleteHolidayrecord(holidayId);
	}

}
