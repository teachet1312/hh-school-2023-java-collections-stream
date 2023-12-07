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

    return persons.stream()
            .map(Person::getFirstName)
            .skip(1)
            .collect(Collectors.toList());
  }

  //ну и различные имена тоже хочется
  public Set<String> getDifferentNames(List<Person> persons) {
    return getNames(persons).stream().collect(Collectors.toSet());
  }

  //Для фронтов выдадим полное имя, а то сами не могут
  // так как проверка на null встречается частенько, то мы сделаем функцию

  public String convertPersonToString(Person person) {

    return Stream.of(person.getFirstName(), person.getMiddleName(),person.getSecondName())
            .filter(Objects::nonNull)
            .collect(Collectors.joining(" "));



  }

  // словарь id персоны -> ее имя
  public Map<Integer, String> getPersonNames(Collection<Person> persons) {

    return persons.stream()
            .collect(Collectors.toMap(
                    Person::getId,
                    this::convertPersonToString, (p1, p2) -> p1));
  }

  // есть ли совпадающие в двух коллекциях персоны?
  public boolean hasSamePersons(Collection<Person> persons1, Collection<Person> persons2) {


    Set<Person> persons1_new = new HashSet<>(persons1);

    //привел  к хэшсету, чтоб искал быстрее, тут по идее должна получиться сложность О(N),
    // где N - кол-во элементов в persons1
    // можно было бы, конечно, заморочиться и искать по наименьшему по длине
    for (Person person: persons2) {
      if (persons1_new.contains(person)) {
        return true;
      }
    }
    return false;
  }

  //...
  public long countEven(Stream<Integer> numbers) {

    return numbers.filter(num -> num % 2 == 0).count();
  }
}
