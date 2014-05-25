package de.morannon.e4JPA.client.rcp.parts;

import java.text.DateFormat;
import java.util.GregorianCalendar;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import de.morannon.e4JPA.client.rcp.serviceretrieval.Services;
import de.morannon.e4JPA.domain.person.Person;
import de.morannon.e4jpa.database.IPersonDBChangeObserver;

public class PersonPart
{
    public static final String ID = "de.morannon.e4jpa.client.rcp.part.persons";

    @Inject
    private ESelectionService selectionService;

    private TableViewer viewer;
    private PersonViewerComparator viewerComparator;

    @PostConstruct
    public void createControls( Composite parent )
    {
        viewer = new TableViewer( parent, SWT.SINGLE | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER );

        createColumns();
        addSelectionListener();
        addDatabaseObserver();

        viewer.setContentProvider( new PersonsContentProvider() );
        viewer.setInput( new Object() );
        viewerComparator = new PersonViewerComparator();
        viewer.setComparator( viewerComparator );

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
        column.addSelectionListener( getSelectionAdapter( column, PersonViewerSortableColumns.id ) );
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
        column.setText( "First name" );
        column.setAlignment( SWT.LEFT );
        column.addSelectionListener( getSelectionAdapter( column, PersonViewerSortableColumns.firstname ) );

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
        column.setText( "Last name" );
        column.setAlignment( SWT.LEFT );
        column.addSelectionListener( getSelectionAdapter( column, PersonViewerSortableColumns.lastname ) );
        
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
        column.setText( "Date of birth" );
        column.setAlignment( SWT.RIGHT );
        column.addSelectionListener( getSelectionAdapter( column, PersonViewerSortableColumns.dateOfBirth ) );

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

    private SelectionAdapter getSelectionAdapter( final TableColumn column,
        final PersonViewerSortableColumns sortableColumn )
    {
        SelectionAdapter selectionAdapter = new SelectionAdapter()
        {
            @Override
            public void widgetSelected( SelectionEvent e )
            {
                viewerComparator.setColumn( sortableColumn );
                
                int dir = viewerComparator.getDirection();
                viewer.getTable().setSortDirection( dir );
                viewer.refresh();
            }
        };
        return selectionAdapter;
    }

    private void addSelectionListener()
    {
        viewer.addSelectionChangedListener( new ISelectionChangedListener()
        {
            @Override
            public void selectionChanged( SelectionChangedEvent event )
            {
                IStructuredSelection selection = (IStructuredSelection)viewer.getSelection();
                selectionService.setSelection( selection.getFirstElement() );
            }
        } );
    }

    @Inject
    private void selectionChanged( @Optional @Named(IServiceConstants.ACTIVE_SELECTION) Person person )
    {
        //        System.out.println( "PersonPart.selectionChanged " + person );
    }

    private void addDatabaseObserver()
    {
        Services.getPersonService().registerPersonObserver( new IPersonDBChangeObserver()
        {
            @Override
            public void changed( String eventID, Person affectedPerson )
            {
                refresh();
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