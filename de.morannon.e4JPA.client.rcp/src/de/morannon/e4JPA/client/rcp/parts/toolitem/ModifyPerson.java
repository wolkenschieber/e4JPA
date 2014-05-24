package de.morannon.e4JPA.client.rcp.parts.toolitem;

import javax.inject.Inject;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.widgets.Display;

import de.morannon.e4JPA.client.rcp.dialog.ModifyPersonDialog;
import de.morannon.e4JPA.client.rcp.parts.PersonPart;
import de.morannon.e4JPA.domain.person.Person;

public class ModifyPerson
{
    @Inject
    private ESelectionService selectionService;

    @Execute
    public void execute()
    {
        Person selectedPerson = (Person)selectionService.getSelection( PersonPart.ID );
        if( selectedPerson != null )
        {
            Dialog dialog = new ModifyPersonDialog( Display.getDefault().getActiveShell(), selectedPerson );
            dialog.open();
        }
    }

}
