package de.morannon.e4JPA.domain.person;

import java.text.DateFormat;
import java.util.GregorianCalendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Person
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String firstname;
    private String lastname;

    @Temporal(TemporalType.DATE)
    private GregorianCalendar dateOfBirth;

    public Person()
    {
    }

    public Person( String firstname, String lastname, GregorianCalendar dateOfBirth )
    {
        this.firstname = firstname;
        this.lastname = lastname;
        this.dateOfBirth = dateOfBirth;
    }

    public int getId()
    {
        return id;
    }
    
    public void setId( int id )
    {
        this.id = id;
    }
    
    public String getFirstname()
    {
        return firstname;
    }

    public void setFirstname( String firstname )
    {
        this.firstname = firstname;
    }

    public String getLastname()
    {
        return lastname;
    }

    public void setLastname( String lastname )
    {
        this.lastname = lastname;
    }

    public GregorianCalendar getDateOfBirth()
    {
        return dateOfBirth;
    }

    public void setDateOfBirth( GregorianCalendar dateOfBirth )
    {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString()
    {
        final String separator = ", ";
        StringBuilder sb = new StringBuilder();
        sb.append( "id: " + getId() ).append( separator );
        sb.append( "firstname: " + getFirstname() ).append( separator );
        sb.append( "lastname: " + getLastname() ).append( separator );
        sb.append( "dateOfBirth: " + DateFormat.getDateInstance().format( getDateOfBirth().getTime() ) );
        return sb.toString();
    }
}
