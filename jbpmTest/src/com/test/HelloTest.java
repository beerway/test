package com.test;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.List;

import org.junit.Test;
import org.jbpm.api.*;
public class HelloTest {

	ProcessEngine processEngine;
	
	  public HelloTest() {
		// TODO Auto-generated constructor stub
	 
		processEngine = Configuration.getProcessEngine();//会自动读取jbpm.cfg.xml的配置文件。不需要再去找
		
	}
	
	@Test
	public void testDeploy() throws Exception{
		
		RepositoryService repositoryService = processEngine.getRepositoryService();
		String deploymeId = repositoryService.createDeployment().
		addResourceFromClasspath("HelloWorld.jpdl.xml").deploy();//流程定义的发布。
		
		//获取流程定义信息
		List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().list();
		for (ProcessDefinition processDefinition : list) {
			System.out.println(processDefinition.getId());//发布流程的定义
		}
		
		//删除流程
		repositoryService.deleteDeploymentCascade(deploymeId);
		System.out.println(repositoryService.createProcessDefinitionQuery().list().size());
	}

}
