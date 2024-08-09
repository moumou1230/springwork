package com.yedam.app.aop.config;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.yedam.app.emp.service.EmpVO;

@Aspect // AOP의 설정
@Component
public class CommonAspect {
	//포인트컷 : 조인포인트 중에서 Advice(횡단관심)이 적용될 메소드 필터
	@Pointcut("within(com.yedam.app.emp.service.impl.*)")//within 하위에 존재하는 모든 비즈니스 메소드를 총칭하겠다.(advice로 적용될 대상으로 보겟다.)
	public void empPointCut() {}//자기위에 적용되어있는데 @PointCut의 결과를 불러온다.
	
	//Weaving : 포인트 컷 + 타이밍 + Advice(횡단관심)
	@Before("empPointCut()") //위빙이 일어날 시점
	public void beforeAdvice(JoinPoint joinPoint) {
		String sinagerStr = joinPoint.getSignature().toString();
		Object[] args = joinPoint.getArgs();//매개변수는 넘어갈 수있는 형태가 많아서 Object 로 받는다.
		System.err.println("#####실행 : " + sinagerStr);
		
		for(Object arg : args) {
			System.err.println("매개변수 : ");
			if(arg instanceof Integer) {
				System.err.println((Integer)arg);
			}else if(arg instanceof EmpVO) {
				System.err.println((EmpVO)arg);

			}
		}
	}
	
	@Around("empPointCut()")
	public Object executeTime(ProceedingJoinPoint joinPoint) throws Throwable {//ProceedingJoinPoint 실행중인
		String signaterStr = joinPoint.getSignature().toString();
		System.err.println("=== 시작 : " +signaterStr);
		//공통기능
		System.err.println("=== 핵심 기능 전 실행 : " + System.currentTimeMillis());
		
		try {
			//비지니스 메소드를 실행
			Object obj = joinPoint.proceed();//비즈니스 메소드를 실행할때 실행 시간 체크할때 사용하기도함
			return obj;
		} finally {
			//공통 기능
			System.err.println("=== 핵심 기능 후 실행 : " + System.currentTimeMillis());
			System.err.println("=== 끝 : " +signaterStr);
		}
	}
}
