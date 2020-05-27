package com.spryntas.service.attendance;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysql.cj.util.StringUtils;
import com.spryntas.exception.BadRequestException;
import com.spryntas.model.Attendance;

@Service
public class AttendanceServiceImpl implements AttendanceService {

	@Autowired
	private AttendanceDao attendanceDao;
	
	@Override
	public Attendance insertAttendance(Attendance attendance) {
		if(attendance.getEmpId()==null) {
			 throw new BadRequestException("EmployeeId is null");
		}
		if(attendance.getMonth()==null) {
			 throw new BadRequestException("month is null");
		}
		if(attendance.getYear()==null) {
			 throw new BadRequestException("year is null");
		}
          return attendanceDao.insertAttendance(attendance);
	}
	
	@Override
	public Attendance getSingleAttendance(Attendance attendance) {
		if(attendance.getEmpId()==null) {
			 throw new BadRequestException("EmployeeId is null");
		}
		if(attendance.getMonth()==null) {
			 throw new BadRequestException("month is null");
		}
		if(attendance.getYear()==null) {
			 throw new BadRequestException("year is null");
		}
		return attendanceDao.getSingleAttendance(attendance.getEmpId(),attendance.getMonth(), attendance.getYear());
	}
	
	@Override
	public Attendance updateAttendance(Attendance attendance) {
		if(attendance.getEmpId()==null) {
			 throw new BadRequestException("EmployeeId is null");
		}
		if(attendance.getMonth()==null) {
			 throw new BadRequestException("month is null");
		}
		if(attendance.getYear()==null) {
			 throw new BadRequestException("year is null");
		}
		Attendance oldAttendance=attendanceDao.getSingleAttendance(attendance.getEmpId(),attendance.getMonth(), attendance.getYear());
	
		attendance.setCount((oldAttendance.getCount()+1));
           attendanceDao.updateAttendance(attendance);
           return attendanceDao.getSingleAttendance(attendance.getEmpId(),attendance.getMonth(), attendance.getYear());
	
	}
	
	@Override
	public List<Attendance> listOfAttendanceForSingleEmployee(String empId,Integer year){
		if(empId==null) {
			 throw new BadRequestException("EmployeeId is null");
		}		
		return attendanceDao.listOfAttendanceForSingleEmployee(empId,year);
	}
	
	@Override
	public List<Attendance> overAllAttendanceList(String month,Integer year){
		if(year==null) {
			 throw new BadRequestException("year is null");
		}		
		return attendanceDao.overAllAttendanceList(month,year);
	}
}
