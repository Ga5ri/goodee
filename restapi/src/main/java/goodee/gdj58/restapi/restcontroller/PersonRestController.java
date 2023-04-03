package goodee.gdj58.restapi.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import goodee.gdj58.restapi.service.PersonService;

@RestController
@CrossOrigin
public class PersonRestController {
	@Autowired PersonService personService;
	
	// RestController의 리턴타입은 모델객체 -> RestController 애노테이션에 의해서 JSON문자열 변경
	@GetMapping("/personList")
	public List personList() {
		return personService.getPersonList(); // 리턴값이 RestController 애노테이션에 의해서 JSON문자열 변경
	}
}