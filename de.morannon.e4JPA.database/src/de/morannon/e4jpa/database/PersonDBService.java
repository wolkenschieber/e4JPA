package de.morannon.e4jpa.database;

import java.util.List;

import de.morannon.e4JPA.domain.person.Person;

public interface PersonDBService
{
    void addPerson( Person person );

    void modifyPerson( Person person );

    void removePersion( Person person );

    List<Person> getPersons();

    void registerPersonObserver( IPersonDBChangeObserver observer );

    void unregisterPersonObserver( IPersonDBChangeObserver observer );
}
