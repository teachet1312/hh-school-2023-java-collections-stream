package tasks;

import common.Person;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
А теперь о горьком
Всем придется читать код
А некоторым придется читать код, написанный мною
Сочувствую им
Спасите будущих жертв, и исправьте здесь все, что вам не по душе!
P.S. функции тут разные и рабочие (наверное), но вот их понятность и эффективность страдает (аж пришлось писать комменты)
P.P.S Здесь ваши правки желательно прокомментировать (можно на гитхабе в пулл реквесте)
 */
public class Task8 {

  private long count;

  //Не хотим выдывать апи нашу фальшивую персону, поэтому конвертим начиная со второй
  public List<String> getNames(List<Person> persons) {
    if (persons.size() == 0) {
      return Collections.emptyList();
    }
    //persons.remove(0); удалять не обязательно
    return persons.stream()
            .map(Person::getFirstName)
            .skip(1)
            .collect(Collectors.toList());
  }

  //ну и различные имена тоже хочется
  public Set<String> getDifferentNames(List<Person> persons) {
    return getNames(persons).stream().distinct().collect(Collectors.toSet());
  }

  //Для фронтов выдадим полное имя, а то сами не могут
  // так как проверка на null встречается частенько, то мы сделаем функцию
  public String NotNull(String s) {
    String s1 =  s == null ? "" : s;
    return s1;
  }
  public String convertPersonToString(Person person) {
    String firstName = NotNull(person.getFirstName());
    String secondName = NotNull(person.getSecondName());
    String thirdName = NotNull(person.getMiddleName());
    String result = Stream.of(firstName, secondName, thirdName)
            .collect(Collectors.joining(" "));
    /**if (person.getSecondName() != null) {
      result += person.getSecondName();
    }

    if (person.getFirstName() != null) {
      result += " " + person.getFirstName();
    }

    if (person.getSecondName() != null) {
      result += " " + person.getSecondName();
    }
     */

    return result;
  }

  // словарь id персоны -> ее имя
  public Map<Integer, String> getPersonNames(Collection<Person> persons) {
    /** Map<Integer, String> map = new HashMap<>(1);
    for (Person person : persons) {
      if (!map.containsKey(person.getId())) {
        map.put(person.getId(), convertPersonToString(person));
      }
    }
     */
    Map<Integer, String> map = persons.stream()
            .collect(Collectors.toMap(
                    Person::getId,
                    x -> convertPersonToString(x)
            ));
    return map;
  }

  // есть ли совпадающие в двух коллекциях персоны?
  public boolean hasSamePersons(Collection<Person> persons1, Collection<Person> persons2) {

    /**
     boolean has = false;
     for (Person person1 : persons1) {
      for (Person person2 : persons2) {
        if (person1.equals(person2)) {
          has = true;
        }
      }
    }
     */
    Set<Person> persons1_set = persons1.stream().collect(Collectors.toCollection(HashSet::new));
    Set<Person> persons2_set = persons2.stream().collect(Collectors.toSet());
    for (Person pers: persons1_set) {
      if (persons2_set.contains(pers)) {
        return true;
      }
    }
    return false;
  }

  //...
  public long countEven(Stream<Integer> numbers) {
    count = 0;
    numbers.filter(num -> num % 2 == 0).forEach(num -> count++);
    // len([x for x in numbers if x % 2 == 0])
    return count;
  }
}
