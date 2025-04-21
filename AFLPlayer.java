public class AFLPlayer extends AFLTeamMember {
    private int number;
    private boolean isCaptain;

    // Constructor
    public AFLPlayer(int number, String name, String position, boolean isCaptain) {
        super(name, position);
        if (isNumberValid(number)) {
            this.number = number;
        } else {
            throw new IllegalArgumentException("Invalid player number: " + number);
        }
        this.isCaptain = isCaptain;
    }

    // Getters
    public int getNumber() {
        return this.number;
    }

    public boolean get_IsCaptain() {
        return this.isCaptain;
    }

    public boolean isNumberValid(int num) {
        if (num > 0) {
            return true;
        } else {
            return false;
        }
    }

    public void isCaptain(boolean isCap) {
        if (true) {
            this.isCaptain = isCap;
        }
    }

    @Override
    public String toString() {
        if (isCaptain == true) {
            return "[" + getNumber() + "] " + getName() + ", " + getPosition() + " (c)";
        } else {
            return "[" + getNumber() + "] " + getName() + ", " + getPosition();
        }
    }

}
