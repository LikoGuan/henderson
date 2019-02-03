package com.henderson.web;

//import com.roskill.common.model.Employee;
//import com.roskill.common.service.DemoDubboService;

import com.henderson.service.dao.EmployeeDao;
import com.henderson.service.model.Employee;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by likoguan on 11/03/18.
 */
@Controller
public class MainController {
//    @Autowired
//    private DemoDubboService demoDubboService;
    @Autowired
    private EmployeeDao employeeDao;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @ResponseBody
    public String hello() {
        Employee employee = employeeDao.getById(1L);
        return "hello";
    }

    private Employee getEmployee() {
        try {
        /*
		 * 1.加载mybatis的配置文件，初始化mybatis，创建出SqlSessionFactory，是创建SqlSession的工厂
		 * 这里只是为了演示的需要，SqlSessionFactory临时创建出来，在实际的使用中，SqlSessionFactory只需要创建一次，当作单例来使用
		 */
            InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
            SqlSessionFactory factory = builder.build(inputStream);

            //2. 用SqlSession的工厂SqlSessionFactory创建一个SqlSession，进行数据库操作
            SqlSession sqlSession = factory.openSession();

            Configuration configuration = sqlSession.getConfiguration();

            //3.1使用SqlSession查询
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("id", 1L);
            List<Employee> result = sqlSession.selectList("com.henderson.service.dao.EmployeeDao.getById", params);

            //3.2mytatis生成动态代理，即EmployeeDao的实现类
            EmployeeDao employeeDao1 = sqlSession.getMapper(EmployeeDao.class);
            Employee employee1 = employeeDao1.getById(1L);
            EmployeeDao employeeDao2 = sqlSession.getMapper(EmployeeDao.class);
            Employee employee2 = employeeDao2.getById(1L);
        } catch (IOException ex) {
            System.out.println(ex.getCause());
        }

        return null;
    }
}


/*
spring-dubbo-consumer.xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:dubbo="http://code.alibabatech.com/schema/dubbo"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd
       http://code.alibabatech.com/schema/dubbo
       http://code.alibabatech.com/schema/dubbo/dubbo.xsd">

    <!-- 消费方应用名，用于计算依赖关系，不是匹配条件，不要与提供方一样 -->
    <dubbo:application name="henderson"  />

    <dubbo:registry protocol="zookeeper" address="127.0.0.1:2181" />

    <!-- 生成远程服务代理，可以和本地bean一样使用demoService -->
    <dubbo:reference id="demoDubboService" interface="com.guan.common.DemoDubboService" />

</beans>
*/
