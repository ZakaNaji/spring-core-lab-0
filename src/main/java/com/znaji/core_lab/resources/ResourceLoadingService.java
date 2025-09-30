package com.znaji.core_lab.resources;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

@Component
public class ResourceLoadingService implements ResourceLoaderAware {

        private ResourceLoader resourceLoader;

        @Override
        public void setResourceLoader(ResourceLoader resourceLoader) {
                this.resourceLoader = resourceLoader;
        }

        public String readResource(String location) throws IOException {
                Resource resource = resourceLoader.getResource(location);
                // TODO: add error handling for missing resources and differentiate classpath vs filesystem.
                return StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);
        }
}
