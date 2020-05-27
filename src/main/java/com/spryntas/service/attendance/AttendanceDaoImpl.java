package com.spryntas.service.attendance;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.spryntas.model.Attendance;

import lombok.extern.slf4j.Slf4j;

@Repository(value = "attendaceDao")
@Slf4j
public class AttendanceDaoImpl implements AttendanceDao {

	@Autowired
	private JdbcTemplate jdbctemplate;

	@Override
	public Attendance insertAttendance(Attendance attendance) {
		log.info("creating a attendance by given attendance info");
		String sql = "insert into Attendance (emp_id,count,month,year,created) value(?,?,?,?,now())";
		try {
			jdbctemplate.update(sql, new Object[] { attendance.getEmpId(), attendance.getCount(), attendance.getMonth(),
					attendance.getYear() });
		} catch (Exception ex) {
			log.error("error occured in inserting attendance details");

		}
		return getSingleAttendance(attendance.getEmpId(), attendance.getMonth(), attendance.getYear());
	}

	@Override
	public Attendance getSingleAttendance(String emp_id, String month, int year) {
		Attendance attendance = null;
		log.info("getting the single attendance by given empid,month");
		StringBuilder sql = new StringBuilder();
		sql.append("select attendance_id,emp_id,count,month,year,created,updated from Attendance ");
		if (emp_id != null && month != null && year != 0) {
			sql.append(" where emp_id=? and month =? and year=?");
		}
		try {
			attendance = jdbctemplate.queryForObject(sql.toString(), new Object[] { emp_id, month, year },
					new BeanPropertyRowMapper<Attendance>(Attendance.class));

		} catch (DataAccessException e) {
			log.error("error occured in Retriving single attendance  details based on given empid,month");
		}
		return attendance;
	}

	@Override
	public void updateAttendance(Attendance attendance) {
		log.info("updating a attendance by given attendance info");
		String sql = "update Attendance set  count=?,updated =now()  where emp_id=? and month=? and year=?";
		try {
			jdbctemplate.update(sql, new Object[] { attendance.getCount(), attendance.getEmpId(), attendance.getMonth(),
					attendance.getYear() });
		} catch (Exception ex) {
			log.error("error occured in updating attendance details");

		}
	}

	@Override
	public List<Attendance> listOfAttendanceForSingleEmployee(String empId, Integer year) {
		List<Attendance> attendance = null;
		log.info("retriving the overall attendance by given empid ");
		String sql = "select attendance_id,emp_id,count,month,year,created,updated from Attendance where emp_id=?";
		if (year != null) {
			String additonal = " and year=?";
			sql = sql + additonal;
		}
		if (year != null) {
			try {
				attendance = jdbctemplate.query(sql, new Object[] { empId, year },
						new BeanPropertyRowMapper<Attendance>(Attendance.class));
			} catch (Exception ex) {
				log.error("error occured in adding date details");
			}
		} else {
			try {
				attendance = jdbctemplate.query(sql, new Object[] { empId },
						new BeanPropertyRowMapper<Attendance>(Attendance.class));
			} catch (Exception ex) {
				log.error("error occured in list of attendance based on employeeeId details");
			}
		}
		return attendance;
	}
	
	
	@Override
	public List<Attendance> overAllAttendanceList(String month, Integer year) {
		List<Attendance> attendance = null;
		log.info("retriving the overall attendance by given month,year ");
		String sql = "select attendance_id,emp_id,count,month,year,created,updated from Attendance where year=?";
		if (month != null) {
			String additonal = " and month=?";
			sql = sql + additonal;
		}
		if (month != null) {
			try {
				attendance = jdbctemplate.query(sql, new Object[] {  year,month },
						new BeanPropertyRowMapper<Attendance>(Attendance.class));
			} catch (Exception ex) {
				log.error("error occured in retrieving overall attendance details");
			}
		} else {
			try {
				attendance = jdbctemplate.query(sql, new Object[] { year },
						new BeanPropertyRowMapper<Attendance>(Attendance.class));
			} catch (Exception ex) {
				log.error("error occured in retrieving overall attendance details");
			}
		}
		return attendance;
	}

}


