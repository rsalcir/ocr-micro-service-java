package ocr.base;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;

import java.util.List;

public abstract class ApiTest extends JerseyTest {

    @Override
    public ResourceConfig configure() {
        ResourceConfig resourceConfig = new ResourceConfig();
        ResourcesRegister().forEach(resourceConfig::register);
        return resourceConfig;
    }

    protected abstract List<Object> ResourcesRegister();
}
