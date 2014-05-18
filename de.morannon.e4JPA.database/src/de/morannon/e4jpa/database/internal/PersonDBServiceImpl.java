package de.morannon.e4jpa.database.internal;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.eclipse.persistence.config.PersistenceUnitProperties;

import de.morannon.e4JPA.domain.person.Person;
import de.morannon.e4jpa.database.PersonDBService;

public class PersonDBServiceImpl implements PersonDBService
{
    // TODO: activate and deactivate: init and destroy entity manager

    private EntityManagerFactory emf;
    private EntityManager em;

    @Override
    public void addPerson( Person person )
    {
        em.getTransaction().begin();
        em.persist( person );
        em.getTransaction().commit();
    }

    @Override
    public void modifyPerson( Person person )
    {
        addPerson( person );
    }

    @Override
    public void removePersion( Person person )
    {
        em.getTransaction().begin();
        Person find = em.find( Person.class, person.getId() );
        em.remove( find );
        em.getTransaction().commit();
    }

    @Override
    public List<Person> getPersons()
    {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Person> cq = cb.createQuery( Person.class );
        Root<Person> rootEntry = cq.from( Person.class );
        CriteriaQuery<Person> all = cq.select( rootEntry );
        TypedQuery<Person> allQuery = em.createQuery( all );

        List<Person> resultList = allQuery.getResultList();
        return resultList;
    }


    @SuppressWarnings("unchecked")
    protected void activate()
    {
        @SuppressWarnings("rawtypes")
        Map map = new HashMap();
        map.put( PersistenceUnitProperties.CLASSLOADER, getClass().getClassLoader() );

        org.eclipse.persistence.jpa.PersistenceProvider persistenceProvider = new org.eclipse.persistence.jpa.PersistenceProvider();
        emf = persistenceProvider.createEntityManagerFactory( "de.morannon.e4JPA", map );
        em = emf.createEntityManager();
    }

    protected void deactivate()
    {
        em.close();
        emf.close();
        em = null;
        emf = null;
    }
}
