package tasks;

import common.Person;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    /**List<Person> person_all = new ArrayList<>(); какое-то не самое красивое решение
    for (Person pers : persons1) {
      person_all.add(pers);
    }
    for (Person pers1 : persons2) {
      person_all.add(pers1);
    }
     */
    /**List <Person> person_all = new ArrayList<>();
    person_all.addAll(persons2);
    person_all.addAll(persons1);
    */
    List<Person> person_all = Stream.concat(
            persons1.stream(),
            persons2.stream())
            .sorted(Comparator.comparing(Person::getCreatedAt))
            .limit(limit)
            .collect(Collectors.toList());

    return person_all;
  }
}
