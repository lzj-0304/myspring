package com.lzj.spring.core.io;

import java.io.IOException;
import java.io.InputStream;

public interface Resource {
    // 获取文件字节流
    public InputStream getInputStream() throws IOException;
    // 获取文件描述
    public String getDescription();
}
