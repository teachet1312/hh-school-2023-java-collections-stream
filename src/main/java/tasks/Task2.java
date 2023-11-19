package tasks;

import common.Person;

import java.util.*;
import java.util.stream.Collectors;

/*
Задача 2
На вход принимаются две коллекции объектов Person и величина limit
Необходимо объеденить обе коллекции
отсортировать персоны по дате создания и выдать первые limit штук.
 */
public class Task2 {

  public static List<Person> combineAndSortWithLimit(Collection<Person> persons1,
                                                     Collection<Person> persons2,
                                                     int limit) {
    if (persons1.isEmpty() && persons2.isEmpty()) {
      return new ArrayList<>();
    }
    List<Person> person_all = new ArrayList<>();
    for (Person pers : persons1) {
      person_all.add(pers);
    }
    for (Person pers1 : persons2) {
      person_all.add(pers1);
    }
    List<Person> persons_ = person_all.stream()
            .sorted(Comparator.comparing(Person::getCreatedAt))
            .limit(limit)
            .collect(Collectors.toList());
    /**List<Person> result = new ArrayList<>();
    for (int n = 0; n < limit; n++) {
      result.add(persons_.get(n));
    }
     */
    return persons_;
  }
}
