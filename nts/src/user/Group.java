package user;

public class Group implements Comparable<Group> {
    private String name;
    private String description;

    /**
     * Group constructor (minimal)
     * @param name
     * @param description
     */
    public Group(String name, String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * Accessor for the name attribute
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Accessor for the description attribute
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Group && ((Group) o).getName() == name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int compareTo(Group g) {
        return name.compareToIgnoreCase(g.getName());
    }
}