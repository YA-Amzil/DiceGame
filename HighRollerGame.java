import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;

public class HighRollerGame {

    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        DiceSet diceSet = null;
        AtomicReference<DiceSet> diceSetRef = new AtomicReference<DiceSet>(diceSet);
        var highest = 0;
        boolean playing = true;
        System.out.println("Welcome " + Die.SIX_SIDED_DIE_EMOJI.repeat(5));
        while (playing) {
            System.out.println();
            try {
                System.out.println("Enter a command (h for help): ");
                var command = console.nextLine();
                if (command.matches("h(elp)?")) {
                    showHelp();
                } else if (command.matches("q(uit)?")) {
                    System.out.println("I'm glad you played today. You look great!");
                    console.close();
                    playing = false;
                } else if (command.matches("use\\s+\\d\\d?\\s+\\d\\d?")) {
                    var tokens = command.split("\\s+");
                    var sides = Integer.parseInt(tokens[2].trim());
                    var number = Integer.parseInt(tokens[1].trim());
                    if (diceSet == null)
                        generateDiceSet(diceSetRef, highest, sides, number);
                    else {
                        if (diceSet.getSidesOnEachDie() != sides && diceSet.getNumberOfDices() != number)
                            generateDiceSet(diceSetRef, highest, sides, number);
                        else
                            System.out.println("already using that dice set");
                    }
                    diceSet = diceSetRef.get();
                } else if (command.matches("roll\\s+all")) {
                    if (diceSet == null) {
                        throw new IllegalStateException("You don't have any dice yet");
                    }
                    diceSet.roll();
                    highest = Math.max(highest, diceSet.sum());
                    System.out.println(diceSet);
                } else if (command.matches("roll\\s+\\d+")) {
                    if (diceSet == null) {
                        throw new IllegalStateException("You don't have any dice yet");
                    }
                    diceSet.rollIndividual(Integer.parseInt(command.substring(4).trim()));
                    highest = Math.max(highest, diceSet.sum());
                    System.out.println(diceSet);
                } else if (command.matches("high(est)?")) {
                    if (highest == 0) {
                        System.out.println("You haven't rolled any dice yet");
                    } else {
                        highest = Math.max(highest, diceSet.sum());
                        System.out.println("The highest roll so far is " + highest);
                    }
                } else {
                    System.out.println("I don't understand");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void showHelp() {
        System.out.println("h or help   : Prints this message");
        System.out.println("q or quit   : Quits the program");
        System.out.println("use <s> <n> : Creates a new dice set with n dice of s sided each");
        System.out.println("roll all    : Rolls all the dice in the current DiceSet");
        System.out.println("roll <i>    : Rolls the ith die in the current DiceSet");
        System.out.println("high or highest   : Prints the highest roll so far");
    }

    private static void generateDiceSet(AtomicReference<DiceSet> diceSetRef, int highest, int sides, int number) {
        diceSetRef.set(new DiceSet(sides, number));
        highest = Math.max(highest, diceSetRef.get().sum());
        System.out.println("You are now using a " + diceSetRef.get().getDescriptor());
        System.out.println(diceSetRef.get());
    }
}
