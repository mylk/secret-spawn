package io.github.mylk.secret.spawn.model;

public class Secret {
    private String title;
    private String contentPlain;
    private String contentTransformed;
    private String url;

    public String getTitle() {
        return title;
    }

    public Secret setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getContentPlain() {
        return contentPlain;
    }

    public Secret setContentPlain(String contentPlain) {
        this.contentPlain = contentPlain;
        return this;
    }

    public String getContentTransformed() {
        return contentTransformed;
    }

    public Secret setContentTransformed(String contentTransformed) {
        this.contentTransformed = contentTransformed;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public Secret setUrl(String url) {
        this.url = url;
        return this;
    }
}
