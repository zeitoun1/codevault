package domain;

public class CodeSnippet {
    private String code;
    private String tag;
    private String description;
    boolean modified;

    public CodeSnippet(String code, String tag, String description, boolean modified) {
        this.code = code;
        this.tag = tag;
        this.description = description;
        this.modified = modified;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
