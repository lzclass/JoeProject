package com.wiwj.maigcer.model.bean;

import java.io.File;
import java.io.Serializable;

/**
 * 下载文件的实体
 */
public class FileEntity implements Serializable {
    private String name;
    private File file;

    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}
