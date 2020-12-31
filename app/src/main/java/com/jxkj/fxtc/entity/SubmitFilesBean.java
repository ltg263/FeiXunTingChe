package com.jxkj.fxtc.entity;

public class SubmitFilesBean {

    /**
     * fileName : 86043AB73E7C7010BE5276E9B56BA0C9.jpg
     * localPhysical : E:/tomcat_static/gtbl/upload/
     * size : 84580
     * url : https://www.nbyjdz.com/gtbl/upload/86043AB73E7C7010BE5276E9B56BA0C9.jpg
     */

    private String fileName;
    private String localPhysical;
    private String size;
    private String url;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getLocalPhysical() {
        return localPhysical;
    }

    public void setLocalPhysical(String localPhysical) {
        this.localPhysical = localPhysical;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
