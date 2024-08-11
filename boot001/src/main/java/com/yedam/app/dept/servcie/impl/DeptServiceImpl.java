package com.yedam.app.dept.servcie.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.yedam.app.dept.mapper.DeptMapper;
import com.yedam.app.dept.service.DeptService;
import com.yedam.app.dept.service.DeptVO;

@Service
public class DeptServiceImpl implements DeptService{
	private DeptMapper deptMapper;
	
	public DeptServiceImpl(DeptMapper deptMapper) {
		this.deptMapper = deptMapper;
	}
	@Override
	public List<DeptVO> deptList() {
		return deptMapper.deptSelectList();
	}
	@Override
	public DeptVO deptInfo(DeptVO deptVO) {
		return deptMapper.deptSelectInfo(deptVO);
	}
	@Override
	public int deptInsert(DeptVO deptVO) {
		int result = deptMapper.insertDept(deptVO);
		return result == 1 ? deptVO.getDepartmentId() : -1;
	}
	@Override
	public Map<String, Object> deptUpdate(DeptVO deptVO) {
		Map<String, Object> map = new HashMap<>();
		boolean isSuccessed = false;
		int result = deptMapper.updateDept(deptVO.getDepartmentId(), deptVO);
		if(result == 1) {
			isSuccessed = true;
		}
		map.put("result", isSuccessed);
		map.put("target", deptVO);
		return map;
	}
	@Override
	public Map<String, Object> deptDelete(int deptId) {
		Map<String, Object> map = new HashMap<>();
		int result = deptMapper.deleteDept(deptId);
		if(result==1) {
			map.put("departmentId", deptId);
		}
		return map;
	}
}
