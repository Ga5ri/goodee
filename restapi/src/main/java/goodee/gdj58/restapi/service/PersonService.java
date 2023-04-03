package goodee.gdj58.restapi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import goodee.gdj58.restapi.vo.Person;

@Service
@Transactional
public class PersonService {
	public List<Person> getPersonList() {
		// Maperr or Repository Layer에서
		List<Person> list = new ArrayList<Person>();
		list.add(new Person(1, "홍길동"));
		list.add(new Person(2, "마이클"));
		return list;
	}
}