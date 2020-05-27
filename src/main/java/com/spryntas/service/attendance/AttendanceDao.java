package com.spryntas.service.attendance;

import java.util.List;

import com.spryntas.model.Attendance;

public interface AttendanceDao {

	Attendance insertAttendance(Attendance attendance);

	Attendance getSingleAttendance(String emp_id, String month, int year);

	void updateAttendance(Attendance attendance);

	List<Attendance> listOfAttendanceForSingleEmployee(String empId, Integer year);

	List<Attendance> overAllAttendanceList(String month, Integer year);


}
