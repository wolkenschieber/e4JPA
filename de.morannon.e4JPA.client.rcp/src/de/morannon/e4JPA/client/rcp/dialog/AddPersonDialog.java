package de.morannon.e4JPA.client.rcp.dialog;

import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.swt.widgets.Shell;

import de.morannon.e4JPA.client.rcp.serviceretrieval.Services;
import de.morannon.e4JPA.domain.person.Person;
import de.morannon.e4jpa.database.PersonDBService;

public class AddPersonDialog extends AbstractPersonDialog
{

    public AddPersonDialog( Shell parentShell )
    {
        super( parentShell );
    }

    @Override
    public void create()
    {
        super.create();
        setTitle( "Add person" );
        setMessage( "Enter person details", IMessageProvider.INFORMATION );
    }
    
    @Override
    protected void okPressed()
    {
        Person person = getPersonFromFields();
        PersonDBService personService = Services.getPersonService();
        personService.addPerson( person );
        super.okPressed();
    }
}
