package com.epam.rd.autocode;

import com.epam.rd.autocode.domain.Employee;
import com.epam.rd.autocode.domain.FullName;
import com.epam.rd.autocode.domain.Position;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicReference;


public class RowMapperFactory {

    public RowMapper<Employee> employeeRowMapper() {
        return resultSet -> {
            BigInteger id;
            FullName fullName;
            LocalDate hired;
            Position position;
            BigDecimal salary;
            try {
                position = (Position.valueOf(resultSet.getString("POSITION")));
                id = new BigInteger(resultSet.getString("id"));
                String firstName = resultSet.getString("FIRSTNAME");
                String lastName = resultSet.getString("LASTNAME");
                String middleName = resultSet.getString("MIDDLENAME");
                fullName = new FullName(firstName, lastName, middleName);
                Date hiredate = resultSet.getDate("HIREDATE");
                hired = hiredate.toLocalDate();
                salary = resultSet.getBigDecimal("SALARY");
                Employee employee = new Employee(id, fullName, position, hired, salary);
                return employee;
            } catch (SQLException ex) {
                throw  new RuntimeException(ex);
            }
        };

    }
}
