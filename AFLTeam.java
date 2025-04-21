public class AFLTeam {
    private String teamName;
    AFLTeamMember coach;
    AFLPlayer[] lineUp;

    // constructor
    public AFLTeam(String teamName, AFLTeamMember coach, AFLPlayer[] lineUp) {
        this.teamName = teamName;

        if (isThereACoach(coach)) {
            this.coach = coach;
        } else {
            throw new IllegalArgumentException("Coach missing!");
        }

        if (isLineUpComplete(lineUp)) {
            this.lineUp = lineUp;
        } else {
            throw new IllegalArgumentException("Lineup incomplete!");
        }
    }

    // Getters
    public String getTeamName() {
        return this.teamName;
    }

    public AFLTeamMember getCoach() {
        return this.coach;
    }

    public AFLPlayer[] getLineUp() {
        return this.lineUp;
    }

    public boolean isLineUpComplete(AFLPlayer[] lineUp) {
        if (lineUp.length >= 22) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isThereACoach(AFLTeamMember coach) {
        if (coach.getPosition().equalsIgnoreCase("COACH")) {
            return true;
        } else {
            return false;
        }
    }

    public String toString() {
        String result = getTeamName() + "\n" + getCoach() + "\n";
        for (AFLPlayer player : getLineUp()) {
            result += player + "\n";
        }
        return result;
    }
}
