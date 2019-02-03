package com.henderson.service.dao;

import com.henderson.service.model.Employee;
import org.springframework.stereotype.Repository;

/**
 * Created by likoguan on 10/03/18.
 */
@Repository("employeeDao")
public interface EmployeeDao {
    Employee getById(Long id);

    int insert(Employee employee);
}
