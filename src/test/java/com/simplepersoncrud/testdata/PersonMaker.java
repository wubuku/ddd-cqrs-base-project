package com.simplepersoncrud.testdata;

import com.natpryce.makeiteasy.Instantiator;
import com.natpryce.makeiteasy.Property;
import com.natpryce.makeiteasy.PropertyLookup;
import com.simplepersoncrud.domain.Person;
import com.simplepersoncrud.domain.PersonId;
import org.nthdimenzion.object.utils.IIdGenerator;
import org.nthdimenzion.object.utils.InMemoryIdGenerator;

import static com.natpryce.makeiteasy.Property.newProperty;

public class PersonMaker {

    public static final Property<Person,PersonId> personId = newProperty();
    public static final Property<Person, String> name= newProperty();

    public static final Instantiator<Person> Person = new Instantiator<Person>() {
        public Person instantiate(PropertyLookup<Person> lookup) {
            IIdGenerator idGenerator = new InMemoryIdGenerator();
            Person person = new Person(lookup.valueOf(personId, new PersonId(idGenerator.nextId())),lookup.valueOf(name, "Sudarshan"));
            return person;
        }
    };


}
