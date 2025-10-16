package org.mobicents.gmlc.slee;

/**
 * HTTP Request Types (GET or MLP)
 */
public enum HttpRequestType {
    REST("rest"),
    MLP("mlp"),
    UNSUPPORTED("404");

    private final String path;

    HttpRequestType(String path) {
        this.path = path;
    }

    public String getPath() {
        return String.format("/gmlc/%s", path);
    }

    public static HttpRequestType fromPath(String path) {
        for (HttpRequestType type : values()) {
            if (path.equals(type.getPath())) {
                return type;
            }
        }
        return UNSUPPORTED;
    }
}