package com.yedam.app.emp.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class EmpVO {
	//employee_id => employeeId( application.properties 에서 mybatis.configuration.map-underscore-to-camel-case=true 이설정때문에)
	private Integer employeeId; //int로 하면 null로 넘어올때 처리가 안된다.
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	@DateTimeFormat(pattern = "yyyy-MM-dd") //출력이랑은 상관없다.
	private Date hireDate;
	private String jobId;
	private double salary;
	private double commissionPct;
	private Integer managerId;
	private Integer departmentId;
}
	
