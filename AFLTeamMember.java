public class AFLTeamMember {
    private String name;
    private String position;

    public AFLTeamMember(String n, String p) {
        this.name = n;
        if (isPositionValid(p)) {
            this.position = p;
        } else {
            throw new IllegalArgumentException("Invalid position: " + p);
        }
    }

    // Getters
    public String getName() {
        return this.name;
    }

    public String getPosition() {
        return this.position;
    }

    // No Setter/Mutator because the attributes are fixed

    public boolean isPositionValid(String p1) {
        String[] validPositions = { "FB", "HB", "C", "HF", "FF", "FOL", "IC", "COACH" };
        for (String position : validPositions) {
            if (p1.equalsIgnoreCase(position)) {
                return true; // Position is valid
            }
        }
        // Handle the case where the position is not valid
        return false; // Position not valid
    }

    public String toString() {
        return getName() + ", " + getPosition();
    }
}
