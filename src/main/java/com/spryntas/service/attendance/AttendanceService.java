package com.spryntas.service.attendance;

import java.util.List;

import com.spryntas.model.Attendance;

public interface AttendanceService {

	Attendance getSingleAttendance(Attendance attendance);

	Attendance insertAttendance(Attendance attendance);

	Attendance updateAttendance(Attendance attendance);

	List<Attendance> listOfAttendanceForSingleEmployee(String empId, Integer year);

	List<Attendance> overAllAttendanceList(String month, Integer year);

}
