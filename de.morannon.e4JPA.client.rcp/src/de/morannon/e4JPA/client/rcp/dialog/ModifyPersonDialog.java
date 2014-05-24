package de.morannon.e4JPA.client.rcp.dialog;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

import de.morannon.e4JPA.client.rcp.serviceretrieval.Services;
import de.morannon.e4JPA.domain.person.Person;
import de.morannon.e4jpa.database.PersonDBService;

public class ModifyPersonDialog extends AbstractPersonDialog
{
    private Person personToModify;

    public ModifyPersonDialog( Shell parentShell, Person personToModify )
    {
        super( parentShell );
        this.personToModify = personToModify;
    }

    @Override
    public void create()
    {
        super.create();
        setTitle( "Modify person" );
        setMessage( "Enter person details", IMessageProvider.INFORMATION );
    }

    @Override
    protected Control createDialogArea( Composite parent )
    {
        Control createDialogArea = super.createDialogArea( parent );

        firstNameText.setText( personToModify.getFirstname() );
        lastNameText.setText( personToModify.getLastname() );
        GregorianCalendar dateOfBirth = personToModify.getDateOfBirth();
        dateOfBirthControl.setDate( dateOfBirth.get( Calendar.YEAR ), dateOfBirth.get( Calendar.MONTH ),
            dateOfBirth.get( Calendar.DAY_OF_MONTH ) );

        return createDialogArea;
    }
    
    @Override
    protected void okPressed()
    {
        Person person = getPersonFromFields();
        person.setId( personToModify.getId() );
        PersonDBService personService = Services.getPersonService();
        personService.modifyPerson( person );
        super.okPressed();
    }
}
