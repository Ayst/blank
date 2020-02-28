package org.ayst.blank.person.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ayst.blank.person.domain.Person;
import org.ayst.blank.person.domain.PersonRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;
    private final RestTemplate restTemplate;

    public Optional<Person> getPerson(final String id) {
        String catFact = this.restTemplate.getForObject("https://cat-fact.herokuapp.com/facts/58e008ad0aac31001185ed0c", String.class);
        log.info("CatFact : " + catFact);
        return this.personRepository.findById(id);
    }

    public String createPerson(final Person person) {
        person.setId(UUID.randomUUID().toString());
        this.personRepository.save(person);

        log.info("Create a new Person : " + person);

        return person.getId();
    }
}
