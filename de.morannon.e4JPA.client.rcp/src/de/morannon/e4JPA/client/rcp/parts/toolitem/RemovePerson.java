package de.morannon.e4JPA.client.rcp.parts.toolitem;

import javax.inject.Inject;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import de.morannon.e4JPA.client.rcp.parts.PersonPart;
import de.morannon.e4JPA.client.rcp.serviceretrieval.Services;
import de.morannon.e4JPA.domain.person.Person;
import de.morannon.e4jpa.database.PersonDBService;

public class RemovePerson
{
    @Inject
    private ESelectionService selectionService;

    @Execute
    public void execute()
    {
        Person selectedPerson = (Person)selectionService.getSelection( PersonPart.ID );
        if( selectedPerson != null )
        {
            Shell shell = Display.getCurrent().getActiveShell();
            boolean confirmed = MessageDialog.openConfirm( shell, "Confirm delete", "Do you want to delete: "
                + selectedPerson.getFirstname() + " " + selectedPerson.getLastname() );
            if( confirmed )
                delete( selectedPerson );
        }
    }

    private void delete( Person person )
    {
        PersonDBService personService = Services.getPersonService();
        personService.removePersion( person );
    }
}