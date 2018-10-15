package hu.csakegysor.imcsb.service.navigation;

public enum Destination {
    DASHBOARD("dashboard.fxml"),
    SEARCH("search.fxml");

    private final String path;

    Destination(final String path) {
        this.path = path;
    }

    public String getPath() {
        return this.path;
    }
}
