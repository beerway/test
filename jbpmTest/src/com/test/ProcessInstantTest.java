package com.test;

import static org.junit.Assert.*;

import java.util.List;

import org.jbpm.api.Configuration;
import org.jbpm.api.ExecutionService;
import org.jbpm.api.ProcessEngine;
import org.jbpm.api.ProcessInstance;
import org.junit.Test;

public class ProcessInstantTest {


	
	ProcessEngine  processEngine;
	
	public ProcessInstantTest(){
		processEngine = Configuration.getProcessEngine();
	}

	//流程的发布
	@Test
	public void setUp() {
		processEngine.getRepositoryService().createDeployment().addResourceFromClasspath("HelloWorld.jpdl.xml").deploy();
	}
	
	//测试流程实例
	
	public void testProcessInstance(){
		//用来控制流程实例的服务 ExecutionService
		ExecutionService executionService = processEngine.getExecutionService();
		//启动流程实例
		ProcessInstance pi = executionService.startProcessInstanceByKey("HelloWorld");
		
	
		System.out.println(pi);
		System.out.println(pi.isEnded());
		
		//让其自行继续执行
		pi = executionService.signalExecutionById(pi.getId());
		System.out.println(pi.isEnded());
	}
	
	
	public void testProcessInstanceEnd(){
		//用来控制流程实例的服务 ExecutionService
				ExecutionService executionService = processEngine.getExecutionService();
				//启动流程实例
				ProcessInstance pi = executionService.startProcessInstanceByKey("HelloWorld");
			//直接从数据库中删除流程实例
		executionService.deleteProcessInstanceCascade(pi.getId());
	}
	@Test
	public void testProcessInstanceList(){
		
		//用来控制流程实例的服务 ExecutionService
		ExecutionService executionService = processEngine.getExecutionService();
		
		//启动流程实例
		ProcessInstance pi = executionService.startProcessInstanceByKey("HelloWorld");
		ProcessInstance pi2 = executionService.startProcessInstanceByKey("HelloWorld");
		//获取所有的流程定义
		List<ProcessInstance> list = executionService.createProcessInstanceQuery().list();
		for (ProcessInstance object : list) {
			System.out.println(object.getId());
		}
	}
}
