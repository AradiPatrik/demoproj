public enum Gender {
    MALE("Male"), FEMALE("Female");

    private final String stringValue;

    Gender(String stringValue) {
        this.stringValue = stringValue;
    }

    public String getStringValue() {
        return stringValue;
    }
}
