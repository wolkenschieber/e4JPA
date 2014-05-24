package de.morannon.e4jpa.database;

import de.morannon.e4JPA.domain.person.Person;

public interface IPersonDBChangeObserver
{
    void changed( String eventID, Person affectedPerson ); 
}
