package de.morannon.e4JPA.client.rcp.parts.toolitem;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.workbench.modeling.EPartService;

import de.morannon.e4JPA.client.rcp.serviceretrieval.Services;
import de.morannon.e4JPA.domain.person.Person;
import de.morannon.e4jpa.database.PersonDBService;

public class AddPerson
{
    private static final String[] firstnames =
    { "Horst", "Günter", "Edmund", "Max", "Franz Joseph", "Alfons", "Hans",
        "Hanns", "Wilhelm", "Fritz" };

    private static final String[] lastnames =
    { "Seehofer", "Beckstein", "Stoiber", "Streibl", "Strauß", "Goppel",
        "Ehard", "Seidel", "Hoegner", "Schäffer" };
     
    private Random random = new Random();

    @Execute
    public void execute( EPartService partService )
    {
        Person randomPerson = getRandomPerson();

        PersonDBService personService = Services.getPersonService();
        personService.addPerson( randomPerson );
    }

    private Person getRandomPerson()
    {
        String firstName = getName( firstnames );
        String lastName = getName( lastnames );

        int year = getRandomBetween( 1900, 2000 );
        int month = random.nextInt( Calendar.DECEMBER );
        int day = getRandomDay( year, month );

        GregorianCalendar dateOfBirth = new GregorianCalendar( year, month, day );

        Person person = new Person( firstName, lastName, dateOfBirth );
        return person;
    }

    private String getName( String[] nameArray )
    {
        int idx = random.nextInt( nameArray.length );
        return nameArray[idx];
    }

    private int getRandomBetween( int min, int max )
    {
        int randomNumber = random.nextInt( max - min + 1 ) + min;
        return randomNumber;
    }

    private int getRandomDay( int year, int month )
    {
        GregorianCalendar firstDayInMonth = new GregorianCalendar( year, month,
            1 );
        int actualMaximum = firstDayInMonth
            .getActualMaximum( Calendar.DAY_OF_MONTH );
        return random.nextInt( actualMaximum );
    }
}