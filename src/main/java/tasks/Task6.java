package tasks;

import common.Area;
import common.Person;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/*
Имеются
- коллекция персон Collection<Person>
- словарь Map<Integer, Set<Integer>>, сопоставляющий каждой персоне множество id регионов
- коллекция всех регионов Collection<Area>
На выходе хочется получить множество строк вида "Имя - регион". Если у персон регионов несколько, таких строк так же будет несколько
 */
public class Task6 {

  public static Set<String> getPersonDescriptions(Collection<Person> persons,
                                                  Map<Integer, Set<Integer>> personAreaIds,
                                                  Collection<Area> areas) {
    if (persons.isEmpty() || personAreaIds.isEmpty() || areas.isEmpty()) {
      return new HashSet<>();
    }
    // сперва список список Area преобразуем в hashMap  для быстрого поиска по региону
    Map<Integer, String> area_map = areas.stream()
            .collect(Collectors.toMap(Area::getId, Area::getName));
    Map<Integer, String> names = persons.stream() // мапа айди-имя
            .collect(Collectors.toMap(Person::getId, Person::getSecondName));
    HashSet<String> result = new HashSet<>();
    for (Map.Entry<Integer,Set<Integer>> entry: personAreaIds.entrySet()) {
      Integer person_id = entry.getKey();
      Set<Integer> all_areas_for_person = entry.getValue();
      String person_name = names.get(person_id);
      Iterator iter = all_areas_for_person.iterator();
      while (iter.hasNext()) {
        String res_ = person_name + " " + area_map.get(iter.next());
        result.add(res_);
      }
    }

    return new HashSet<>();
  }
}
