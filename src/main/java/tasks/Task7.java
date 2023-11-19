package tasks;

import common.Company;
import common.Vacancy;

import java.util.*;

/*
Из коллекции компаний необходимо получить всевозможные различные названия вакансий
 */
public class Task7 {

  public static Set<String> vacancyNames(Collection<Company> companies) {
    Set<String> vacancy_names = new HashSet<>();

    for (Company comp_cur : companies) {

      Set<Vacancy> vacancies_of_company = comp_cur.getVacancies();

      for (Vacancy vac : vacancies_of_company) {
        vacancy_names.add(vac.getTitle());
      }
    }
    return vacancy_names;
  }

}
