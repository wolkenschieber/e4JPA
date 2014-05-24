package de.morannon.e4JPA.client.rcp.parts.toolitem;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.widgets.Display;

import de.morannon.e4JPA.client.rcp.dialog.AddPersonDialog;

public class AddPerson
{
    @Execute
    public void execute()
    {
        Dialog dialog = new AddPersonDialog( Display.getDefault().getActiveShell() );
        dialog.open();
    }
}
