package benoProjet;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import benoProjet.Resource.UserResource;

@ApplicationPath("/")
public class ApplicationConfig extends Application{
    @Override
    public Set<Class<?>> getClasses() {
        
        Set<Class<?>> resources = new java.util.HashSet<>();
        
        System.out.println("REST configuration starting: getClasses()");            
        
        //features
        //this will register Jackson JSON providers
        resources.add(org.glassfish.jersey.jackson.JacksonFeature.class);
        //we could also use this:
        //resources.add(com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider.class);
        
        //instead let's do it manually:
          resources.add(UserResource.class);
//        resources.add(AnnouncementResource.class);
//        resources.add(SavesearchResource.class);
//        resources.add(FavorisResource.class);
        //==> we could also choose packages, see below getProperties()
        
        System.out.println("REST configuration ended successfully.");
        
        return resources;
    }
    
    @Override
    public Set<Object> getSingletons() {
        return Collections.emptySet();
    }
    
    @Override
    public Map<String, Object> getProperties() {
        Map<String, Object> properties = new HashMap<>();
        //see https://jersey.java.net/nonav/documentation/latest/user-guide.html#d0e9020 for details
        properties.put("jersey.config.server.wadl.disableWadl", true);
        return properties;
    }    
}