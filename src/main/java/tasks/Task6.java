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

    Map<Integer, String> names = new HashMap<>();
    for (Person pers : persons) {
      Integer id = pers.getId();
      String name = pers.getFirstName();
      names.put(id, name);
    }
    // нужна мапа на основе листа area
    Map<Integer, String> area_map = new HashMap<>();
    for (Area are : areas) {
      Integer id = are.getId();
      String region = are.getName();
      area_map.put(id, region);
    }
    Set<String> result = new HashSet();
    for (Map.Entry<Integer, Set<Integer>> entry : personAreaIds.entrySet()) {
      Integer person_id = entry.getKey();
      Set<Integer> person_areas = entry.getValue();
      String person_name = names.get(person_id); // имя человека
      for (Integer city_id : person_areas) {
        String city_name = area_map.get(city_id);
        String pers_res = person_name + " - " + city_name;
        result.add(pers_res);
      }
    }
    return result;
  }
}
