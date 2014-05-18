package de.morannon.e4JPA.client.rcp.parts;

import java.text.DateFormat;
import java.util.GregorianCalendar;

import javax.annotation.PostConstruct;

import org.eclipse.e4.ui.di.Focus;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import de.morannon.e4JPA.domain.person.Person;

public class PersonPart
{
    public static final String ID = "de.morannon.e4jpa.client.rcp.part.persons";

    private TableViewer viewer;

    @PostConstruct
    public void createControls( Composite parent )
    {
        viewer = new TableViewer( parent, SWT.SINGLE | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER );


        createColumns();
        
        viewer.setContentProvider( new PersonsContentProvider() );
        viewer.setInput( new Object() );

        Table table = viewer.getTable();
        table.setHeaderVisible( true );
        table.setLinesVisible( true );
    }

    private void createColumns()
    {
        createIDColumn();
        createFirstNameColumn();
        createLastNameColumn();
        createDateOfBirthColumn();
    }

    private void createIDColumn()
    {
        TableViewerColumn viewerColumn = new TableViewerColumn( viewer, SWT.NONE );
        TableColumn column = viewerColumn.getColumn();
        column.setWidth( 100 );
        column.setText( "ID" );
        column.setAlignment( SWT.RIGHT );
        viewerColumn.setLabelProvider( new ColumnLabelProvider()
        {
            @Override
            public String getText( Object element )
            {
                Person p = (Person)element;
                return Long.toString( p.getId() );
            }
        } );
    }

    private void createFirstNameColumn()
    {
        TableViewerColumn viewerColumn = new TableViewerColumn( viewer, SWT.NONE );
        TableColumn column = viewerColumn.getColumn();
        column.setWidth( 100 );
        column.setText( "FirstName" );
        column.setAlignment( SWT.LEFT );
        viewerColumn.setLabelProvider( new ColumnLabelProvider()
        {
            @Override
            public String getText( Object element )
            {
                Person p = (Person)element;
                return p.getFirstname();
            }
        } );
    }

    private void createLastNameColumn()
    {
        TableViewerColumn viewerColumn = new TableViewerColumn( viewer, SWT.NONE );
        TableColumn column = viewerColumn.getColumn();
        column.setWidth( 100 );
        column.setText( "LastName" );
        column.setAlignment( SWT.LEFT );
        viewerColumn.setLabelProvider( new ColumnLabelProvider()
        {
            @Override
            public String getText( Object element )
            {
                Person p = (Person)element;
                return p.getLastname();
            }
        } );
    }

    private void createDateOfBirthColumn()
    {
        TableViewerColumn viewerColumn = new TableViewerColumn( viewer, SWT.NONE );
        TableColumn column = viewerColumn.getColumn();
        column.setWidth( 100 );
        column.setText( "Age" );
        column.setAlignment( SWT.RIGHT );
        viewerColumn.setLabelProvider( new ColumnLabelProvider()
        {
            @Override
            public String getText( Object element )
            {
                Person p = (Person)element;
                GregorianCalendar dateOfBirth = p.getDateOfBirth();
                DateFormat df = DateFormat.getDateInstance();
                String formatted = df.format( dateOfBirth.getTime() );
                return formatted;
            }
        } );
    }

    @Focus
    public void onFocus()
    {
        viewer.getControl().setFocus();
    }

    public void refresh()
    {
        viewer.setInput( new Object() );
    }
}