public class NavigationItem {
    private String name;
    private String glyphName;


    public NavigationItem(String name, String glyphName) {
        this.name = name;
        this.glyphName = glyphName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGlyphName() {
        return glyphName;
    }

    public void setGlyphName(String glyphName) {
        this.glyphName = glyphName;
    }
}
