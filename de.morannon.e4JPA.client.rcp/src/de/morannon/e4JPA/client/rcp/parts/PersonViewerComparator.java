package de.morannon.e4JPA.client.rcp.parts;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.swt.SWT;

import de.morannon.e4JPA.domain.person.Person;

public class PersonViewerComparator extends ViewerComparator
{
    private static final int DESCENDING = 1;
    private static final int ASCENDING = 0;

    private PersonViewerSortableColumns propertyIndex;
    private int direction;

    public PersonViewerComparator()
    {
        this.propertyIndex = PersonViewerSortableColumns.id;
        direction = ASCENDING;
    }

    public int getDirection()
    {
        return direction == 1 ? SWT.DOWN : SWT.UP;
    }

    public void setColumn( PersonViewerSortableColumns sortableColumn )
    {
        if( sortableColumn == this.propertyIndex )
            direction = 1 - direction;
        else
        {
            this.propertyIndex = sortableColumn;
            direction = DESCENDING;
        }
    }

    @Override
    public int compare( Viewer viewer, Object e1, Object e2 )
    {
        Person p1 = (Person)e1;
        Person p2 = (Person)e2;
        int rc = 0;
        switch( propertyIndex )
        {
            case id:
                rc = Integer.compare( p1.getId(), p2.getId() );
                break;
            case firstname:
                rc = p1.getFirstname().compareTo( p2.getFirstname() );
                break;
            case lastname:
                rc = p1.getLastname().compareTo( p2.getLastname() );
                break;
            case dateOfBirth:
                rc = p1.getDateOfBirth().compareTo( p2.getDateOfBirth() );
                break;
            default:
                rc = 0;
                break;
        }

        if( direction == DESCENDING )
        {
            rc = -rc;
        }
        return rc;
    }

}
