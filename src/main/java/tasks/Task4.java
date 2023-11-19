package tasks;

import common.ApiPersonDto;
import common.Person;
import common.PersonConverter;
import java.util.ArrayList;
import java.util.List;

/*
Задача 4
Список персон класса Person необходимо сконвертировать в список ApiPersonDto
(предположим, что это некоторый внешний формат)
Конвертер для одной персоны - personConverter.convert()
FYI - DTO = Data Transfer Object - распространенный паттерн, можно погуглить
 */
public class Task4 {

  private final PersonConverter personConverter;

  public Task4(PersonConverter personConverter) {
    this.personConverter = personConverter;
  }

  public List<ApiPersonDto> convert(List<Person> persons) {
    if (persons.size() == 0) {
      return  new ArrayList<>();
    }
    List<ApiPersonDto> api_person_dto = new ArrayList<>();
    for (int i = 0; i < persons.size(); i++) {
      Person person = persons.get(i);

      ApiPersonDto api_person = personConverter.convert(person);
      api_person_dto.add(api_person);
    }
    return api_person_dto;
  }
}
