 
package de.morannon.e4JPA.client.rcp.parts.toolitem;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.modeling.EPartService;

import de.morannon.e4JPA.client.rcp.parts.PersonPart;

public class Refresh {
	@Execute
	public void execute(EPartService partService) {
        MPart part = partService.findPart(PersonPart.ID);
        PersonPart dataPart = (PersonPart)part.getObject();
        dataPart.refresh();
	}
		
}