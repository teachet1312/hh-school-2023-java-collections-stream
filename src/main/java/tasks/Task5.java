package tasks;

import common.ApiPersonDto;
import common.Person;
import common.PersonConverter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/*
Задача 5
Расширим предыдущую задачу
Есть список персон, и словарь сопоставляющий id каждой персоны и id региона
Необходимо выдать список персон ApiPersonDto, с правильно проставленными areaId
Конвертер одной персоны дополнен!
 */
public class Task5 {

  private final PersonConverter personConverter;

  public Task5(PersonConverter personConverter) {
    this.personConverter = personConverter;
  }

  public List<ApiPersonDto> convert(List<Person> persons, Map<Integer, Integer> personAreaIds) {
    if (persons.size() == 0 || personAreaIds.isEmpty()) {
      return new ArrayList<>();
    }
    List<ApiPersonDto> api_person_dto = new ArrayList<>();
    for (int i = 0; i < persons.size(); i++) {
      Person person = persons.get(i);
      Integer person_id = person.getId();
      Integer area_id = personAreaIds.get(person_id);
      ApiPersonDto api_person = personConverter.convert(person, area_id);
      api_person_dto.add(api_person);
    }
    return api_person_dto;
  }
}
