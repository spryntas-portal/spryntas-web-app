package com.spryntas.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spryntas.model.Attendance;
import com.spryntas.service.attendance.AttendanceService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/attendance")
@Api(value="attendance")
@Slf4j
public class AttendanceApi {

	@Autowired
	private AttendanceService attendanceservice;
	
	@GetMapping()
	@ApiOperation(value="Get attendance info by empid",response=Attendance.class)
	public Attendance getAttendanceInfo(@RequestBody Attendance attendance) {
		log.info("retrieve the attendance based on the empid");		
		return attendanceservice.getSingleAttendance(attendance);
	}
	
	@PostMapping()
	@ApiOperation(value="inserting the employee attendance",response=Attendance.class)
	public Attendance insertAttendace(@RequestBody Attendance attendance) {
		log.info("inserting the attendance");
		return attendanceservice.insertAttendance(attendance);
	}
	
	@PutMapping()
	@ApiOperation(value="updating the employee attendance",response=Attendance.class)
	public Attendance updateAttendance(@RequestBody Attendance attendance) {
		log.info("updating the attendance");
		return attendanceservice.updateAttendance(attendance);
	}
	
	@GetMapping("/list/empId/{empId}")
	@ApiOperation(value="retriving the list of attendannce for particular employee",response=Attendance.class)
	public List<Attendance> listOfAttendanceForSingleEmployee(@PathVariable("empId") String empId,@RequestParam(value = "year", required = false) Integer year){
		log.info("retriving the list of attendance data for the particular employee");
		return attendanceservice.listOfAttendanceForSingleEmployee(empId, year);
	}
	
	@GetMapping("/list/alls")
	@ApiOperation(value="retriving the list of attendannce for particular employee",response=Attendance.class)
	public List<Attendance> overAllAttendanceList(@RequestParam(value="month",required=false) String month,@RequestParam(value = "year", required = false) Integer year){
		log.info("retriving the list of attendance data for the particular employee");
		return attendanceservice.overAllAttendanceList(month, year);
	}
	
}
