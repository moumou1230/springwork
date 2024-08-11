package com.yedam.app.dept.service;

import java.util.List;
import java.util.Map;

public interface DeptService {
	public List<DeptVO> deptList();
	public DeptVO deptInfo(DeptVO deptVO);
	public int deptInsert(DeptVO deptVO);
	public Map<String, Object> deptUpdate(DeptVO deptVO);
	public Map<String, Object> deptDelete(int deptId);
}
