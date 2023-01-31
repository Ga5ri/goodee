package goodee.gdj58.online.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import goodee.gdj58.online.vo.Employee;

@Mapper
public interface EmployeeMapper {
	Employee login(Employee employee);
	int deleteEmployee(int empNo);
	int insertEmployee(Employee employee);
	// 쿼리 작성 방법 - @Select("")작성가능하나 마이바티스 이용해서 작성
	List<Employee> selectEmployeeList(Map<String, Object> paramMap);
}