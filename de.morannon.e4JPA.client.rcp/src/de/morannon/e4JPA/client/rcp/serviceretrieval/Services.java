package de.morannon.e4JPA.client.rcp.serviceretrieval;

import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;

import de.morannon.e4jpa.database.PersonDBService;

public class Services
{
    public static PersonDBService getPersonService()
    {
        BundleContext bundleContext = FrameworkUtil.getBundle( Services.class ).getBundleContext();

        ServiceReference<PersonDBService> reference = bundleContext.getServiceReference( PersonDBService.class );
        PersonDBService service = bundleContext.getService( reference );
        return service;
    }
}
