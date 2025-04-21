import java.util.*;
import java.io.*;

public class AFLMatch {

    private AFLTeam homeTeam;
    private AFLTeam awayTeam;

    public AFLMatch(AFLTeam hT, AFLTeam aT) {
        this.homeTeam = hT;
        this.awayTeam = aT;
    }

    // Getters
    public AFLTeam getHomeTeam() {
        return homeTeam;
    }

    public AFLTeam getAwayTeam() {
        return awayTeam;
    }

    private static AFLTeam loadLineUps(File file) throws IOException {
        Scanner myFile = new Scanner(file);

        String teamName = myFile.nextLine();
        String coachDetails = myFile.nextLine();

        // Extracting coach's name and position
        String[] coachArray = coachDetails.split(", ");
        String coachName = coachArray[0];
        String coachPosition = coachArray[1];

        // AFLTeamMember has a constructor that takes a name and position
        AFLTeamMember coach = new AFLTeamMember(coachName, coachPosition);

        // Read the array of players or line-up
        List<AFLPlayer> lineUpList = new ArrayList<>();

        while (myFile.hasNextLine()) {
            String playerDetails = myFile.nextLine();
            String[] playerArray = playerDetails.split(", ");

            // Ensure that there are at least 3 elements in the array
            if (playerArray.length >= 3) {
                // Extract player details from each line
                int playerNumber = Integer.parseInt(playerArray[0]);
                String playerName = playerArray[1];
                String playerPosition = playerArray[2];
                boolean isCaptain = false; // Default value

                // Check if the array has 4 elements and the fourth element contains "c"
                if (playerArray.length == 4 && playerArray[3].equals("c")) {
                    isCaptain = true;
                }
                AFLPlayer lineupPlayer = new AFLPlayer(playerNumber, playerName, playerPosition, isCaptain);

                lineUpList.add(lineupPlayer);

            } else {
                // Handle the case where the line doesn't have enough elements
                throw new IllegalArgumentException("Incomplete player details: " + playerDetails);
            }
        }

        // Convert the list to an array
        AFLPlayer[] lineUpArray = lineUpList.toArray(new AFLPlayer[0]);

        // Create an instance of AFLTeam with the updated constructor
        myFile.close();
        return new AFLTeam(teamName, coach, lineUpArray);
    }

    private static boolean isInputValid(String a1, String b1) {
        String[] validInputs1 = { "h", "a", "f" };
        String[] validInputs2 = { "g", "b", "f" };

        for (String input1 : validInputs1) {
            if (a1.equalsIgnoreCase(input1)) {
                return true; // input is valid
            }
        }

        for (String input2 : validInputs2) {
            if (b1.equalsIgnoreCase(input2)) {
                return true; // input is valid
            }
        }
        // Handle the case where the input is not valid
        throw new IllegalArgumentException("Invalid input!");
    }

    public static void main(String[] args) {
        try {
            File fileToOpen1 = new File(args[0]);
            File fileToOpen2 = new File(args[1]);

            AFLTeam h = loadLineUps(fileToOpen1);
            AFLTeam a = loadLineUps(fileToOpen2);

            System.out.println(h);
            System.out.println(a);

            int[][] scoreBoard = new int[2][5];
            int homeGoal = scoreBoard[0][0];
            int homeGoalCount = scoreBoard[0][1];
            int homeBehind = scoreBoard[0][2];
            int homeBehindCount = scoreBoard[0][3];
            int homeTotal = scoreBoard[0][4];

            int awayGoal = scoreBoard[1][0];
            int awayGoalCount = scoreBoard[1][1];
            int awayBehind = scoreBoard[1][2];
            int awayBehindCount = scoreBoard[1][3];
            int awayTotal = scoreBoard[1][4];

            System.out.println("THE GAME STARTS");
            System.out.println("Which team scored? h/a");

            Scanner keyboard = new Scanner(System.in);
            String input1 = keyboard.nextLine();
            System.out.println("Goal or behind? g/b");
            String input2 = keyboard.nextLine();
            isInputValid(input1, input2);

            while (!input1.equalsIgnoreCase("f")) {
                if (input1.equalsIgnoreCase("h")) {
                    if (input2.equalsIgnoreCase("g")) {
                        homeGoal = homeGoal + 6;
                        homeGoalCount++;
                    } else if (input2.equalsIgnoreCase("b")) {
                        homeBehind = homeBehind + 1;
                        homeBehindCount++;
                    }
                    homeTotal = homeGoal + homeBehind;
                } else {
                    if (input2.equalsIgnoreCase("g")) {
                        awayGoal = awayGoal + 6;
                        awayGoalCount++;
                    } else if (input2.equalsIgnoreCase("b")) {
                        awayBehind = awayBehind + 1;
                        awayBehindCount++;
                    }
                    awayTotal = awayGoal + awayBehind;
                }
                System.out.println("The current score is    " + homeGoalCount + "." + homeBehindCount + " (" + homeTotal
                        + ")    to      " + awayGoalCount + "." + awayBehindCount + "  (" + awayTotal + ").");
                System.out.println();
                System.out.println("Which team scored? h/a");
                input1 = keyboard.nextLine();
                isInputValid(input1, input2);
                if (!input1.equalsIgnoreCase("f")) {
                    System.out.println("Goal or behind? g/b");
                    input2 = keyboard.nextLine();
                    isInputValid(input1, input2);
                }

            }
            System.out.println();
            System.out.println("FULLTIME");

            if (homeTotal > awayTotal) {
                System.out
                        .println(h.getTeamName() + "    " + homeGoalCount + "." +
                                homeBehindCount + " ("
                                + homeTotal
                                + ")    defeated    " + a.getTeamName() + "     " + awayGoalCount + "."
                                + awayBehindCount + "  (" + awayTotal
                                + ").");
            } else {
                System.out
                        .println(a.getTeamName() + "    " + awayGoalCount + "." +
                                awayBehindCount + " ("
                                + awayTotal
                                + ")    defeated    " + h.getTeamName() + "     " + homeGoalCount + "."
                                + homeBehindCount + "  (" + homeTotal
                                + ").");

            }

        } catch (IOException e) {
            // Handle the exception, e.g., print an error message
            System.err.println("An IO error occurred: " + e.getMessage());
        }
    }
}
