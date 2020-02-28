package org.ayst.blank.person.interfaces;

import lombok.RequiredArgsConstructor;
import org.ayst.blank.person.application.PersonService;
import org.ayst.blank.person.domain.Person;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.net.URI;

@RestController
@RequestMapping("/person/")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    @PostMapping
    public ResponseEntity createPerson(@RequestBody final Person person) {
        String personId = this.personService.createPerson(person);

        return ResponseEntity.created(URI.create("/person/"+ personId)).build();
    }

    @GetMapping("{id}")
    public Person getPerson(@PathVariable final String id) {
        return this.personService.getPerson(id).orElseThrow(EntityNotFoundException::new);
    }
}
