package tasks;

import common.Person;
import common.PersonService;

import java.util.*;

/*
Задача 1
Метод на входе принимает List<Integer> id людей, ходит за ними в сервис
(он выдает несортированный Set<Person>, внутренняя работа сервиса неизвестна)
нужно их отсортировать в том же порядке, что и переданные id.
Оценить асимпотику работы
 */
public class Task1 {

  private final PersonService personService;

  public Task1(PersonService personService) {
    this.personService = personService;
  }

  public List<Person> findOrderedPersons(List<Integer> personIds) {

    Set<Person> persons = personService.findPersons(personIds);
    if (persons.isEmpty()) {
      return new ArrayList<>();
    }
    Map<Integer, Person> personsMap = new HashMap<>(); // ключ - id, значение - Person
    Iterator<Person> iter = persons.iterator(); // проход по элементам изначального множества
    while (iter.hasNext()) {
      Person person1 = iter.next();
      Integer id = person1.getId();
      personsMap.put(id, person1);
    }
    LinkedHashSet<Person> new_persons = new LinkedHashSet<>(); // множество с порядком
    for (int i = 0; i < personIds.size(); i++) {
      Integer cur_id = personIds.get(i); // получили id из person_id
      Person cur_person = personsMap.get(cur_id); // текущий пользователь
      if (!(new_persons.contains(cur_person))) { // если пользователя нет, то добавляем его в наше итоговое множество
        new_persons.add(cur_person);
      }
    }

    /** асимптотическая сложность:
          время О(N)
          память: дополнительный Map + LinkedHashSet
      */
    return new_persons.stream().toList();
  }
}
