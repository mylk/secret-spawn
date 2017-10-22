package io.github.mylk.secret.spawn.model;

public class Settings {
    private Integer length;
    private String source;
    private String format;
    private String url;
    private Boolean isTooRandom;

    public Integer getLength() {
        return length;
    }

    public Settings setLength(Integer length) {
        this.length = length;
        return this;
    }

    public String getSource() {
        return source;
    }

    public Settings setSource(String source) {
        this.source = source;
        return this;
    }

    public String getFormat() {
        return format;
    }

    public Settings setFormat(String format) {
        this.format = format;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public Settings setUrl(String url) {
        this.url = url;
        return this;
    }

    public Boolean isTooRandom() {
        return isTooRandom;
    }

    public Settings setIsTooRandom(Boolean isTooRandom) {
        this.isTooRandom = isTooRandom;
        return this;
    }
}
