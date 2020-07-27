package by.mmkle.spring_thymeleaf.controller;

import by.mmkle.spring_thymeleaf.bean.Person;
import by.mmkle.spring_thymeleaf.form.PersonForm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PersonController {
    private static List<Person> personList = new ArrayList<>();

    static {
        personList.add(
                Person.builder()
                    .firstName("Maxim")
                    .lastName("Kleschev")
                    .build()
        );
    }

    @Value("${welcome.message}")
    private String welcomeMessage;

    @Value("${error.message}")
    private String errorMessage;

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String index(Model model){
        model.addAttribute("message", welcomeMessage);
        return "index";
    }

    @RequestMapping(value = "/personList", method = RequestMethod.GET)
    public String getAllPersons(Model model){
        model.addAttribute("personList", personList);
        return "personList";
    }

    @RequestMapping(value = "/addPerson", method = RequestMethod.GET)
    public String showPersonAddPage(Model model){
        PersonForm personForm = new PersonForm();
        model.addAttribute("personForm", personForm);
        return "addPerson";
    }

    @RequestMapping(value = "/addPerson", method = RequestMethod.POST)
    public String savePerson(Model model, @ModelAttribute("personForm") PersonForm personForm){
        String firstName = personForm.getFirstName();
        String lastName = personForm.getLastName();
        if (firstName.equals("")) {
            model.addAttribute("errorMessage", errorMessage);
            return "addPerson";
        }
        personList.add(Person.builder()
                .firstName(firstName)
                .lastName(lastName)
                .build()
        );
        return "redirect:/personList";
    }
}
