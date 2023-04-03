package goodee.gdj58.restapi.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor // Person()
@AllArgsConstructor // Person(int no, String name)
public class Person {
	public int no;
	public String name;
}