package com.simplepersoncrud.testdata;

import com.natpryce.makeiteasy.Instantiator;
import com.natpryce.makeiteasy.Property;
import com.natpryce.makeiteasy.PropertyLookup;
import com.simplepersoncrud.domain.Person;

import static com.natpryce.makeiteasy.Property.newProperty;

public class PersonMaker {

    public static final Property<Person,Long> id = newProperty();
    public static final Property<Person, String> name= newProperty();

    public static final Instantiator<Person> Person = new Instantiator<Person>() {
        public Person instantiate(PropertyLookup<Person> lookup) {
            Person person = new Person(lookup.valueOf(name, "Sudarshan"));
            return person;
        }
    };


}
