package goodee.gdj58.restapi.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StudentMapper {
	// 사용가능한 아이디면 null, 불가하면 아이디
	public String selectStudentId(String studentId);
}