package de.morannon.e4JPA.client.rcp.parts;

import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

import de.morannon.e4JPA.client.rcp.serviceretrieval.Services;
import de.morannon.e4JPA.domain.person.Person;
import de.morannon.e4jpa.database.PersonDBService;

public class PersonsContentProvider implements IStructuredContentProvider
{
    @Override
    public Object[] getElements( Object arg0 )
    {
        PersonDBService personService = Services.getPersonService();
        List<Person> persons = personService.getPersons();

        Person[] personArray = new Person[persons.size()];
        personArray = persons.toArray( personArray );
        return personArray;

    }

    @Override
    public void inputChanged( Viewer arg0, Object arg1, Object arg2 )
    {
        // do nothing
    }

    @Override
    public void dispose()
    {
        // do nothing
    }
}
