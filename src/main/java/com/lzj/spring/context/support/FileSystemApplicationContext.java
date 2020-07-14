package com.lzj.spring.context.support;
import com.lzj.spring.core.io.FileSystemResource;
import com.lzj.spring.core.io.Resource;

public class FileSystemApplicationContext extends AbstractApplicationContext {


    public FileSystemApplicationContext(String configFile) {
        super(configFile);
    }

    @Override
    public Resource getResourceByPath(String configFile) {
        return new FileSystemResource(configFile);
    }
}
