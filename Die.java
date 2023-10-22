public class Die implements Rollable {
    static String SIX_SIDED_DIE_EMOJI = "\uD83C\uDFB2";
    private int sides;
    private int value;
    StringBuilder stringBuilder;

    public Die(int sides) {
        this.sides = sides;
    }

    @Override
    public void roll() {
        value = (int) (Math.random() * sides) + 1;
    }

    public int getSides() {
        return sides;
    }

    public int getValue() {
        return value;
    }

    public String ToString() {
        stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        stringBuilder.append(value);
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
