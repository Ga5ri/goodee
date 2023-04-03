package goodee.gdj58.restapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import goodee.gdj58.restapi.mapper.StudentMapper;

@Service
public class StudentService {
	@Autowired StudentMapper studentMapper;
	public String getStudentId(String studentId) {
		// 사용가능한 ID면 "YES" 아니면 "NO"반환
		String resultStr = "NO";
		if(studentMapper.selectStudentId(studentId) == null) {
			resultStr = "YES";
		}
		return resultStr;
	}
}