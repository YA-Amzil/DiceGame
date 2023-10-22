import java.util.ArrayList;
import java.util.List;

public class DiceSet implements Rollable {

    private Die[] dice;
    private int numberOfDices;
    private int sidesOnEachDie;
    private StringBuilder stringBuilder;

    public DiceSet(int sidesOnEachDie, int numberOfDices) {
        this.numberOfDices = numberOfDices;
        this.sidesOnEachDie = sidesOnEachDie;
        dice = new Die[numberOfDices];
        for (int i = 0; i < numberOfDices; i++) {
            dice[i] = new Die(sidesOnEachDie);
        }
    }

    public String getDescriptor() {
        stringBuilder = new StringBuilder();
        stringBuilder.append(numberOfDices);
        stringBuilder.append("d");
        stringBuilder.append(sidesOnEachDie);
        return stringBuilder.toString();
    }

    public int getSidesOnEachDie() {
        return sidesOnEachDie;
    }

    public int getNumberOfDices() {
        return numberOfDices;
    }

    public int sum() {
        int sum = 0;
        for (Die die : dice) {
            sum += die.getValue();
        }
        return sum;
    }

    public void rollIndividual(int i) {
        dice[i].roll();
    }

    public int getIndividual(int i) {
        return dice[i].getValue();
    }

    public List<Integer> values() {
        List<Integer> values = new ArrayList<>();
        for (Die die : dice) {
            values.add(die.getValue());
        }
        return values;
    }

    public String toString() {
        stringBuilder = new StringBuilder();
        for (Die die : dice) {
            stringBuilder.append("[");
            stringBuilder.append(die.getValue());
            stringBuilder.append("]");
        }
        return stringBuilder.toString();
    }

    @Override
    public void roll() {
        for (Die die : dice) {
            die.roll();
        }
    }

}
