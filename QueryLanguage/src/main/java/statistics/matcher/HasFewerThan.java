
package statistics.matcher;

import statistics.Player;

public class HasFewerThan implements Matcher {

    private final int value;
    private final String fieldName;

    public HasFewerThan(int value, String category) {
        this.value = value;
        this.fieldName = category;
    }

    @Override
    public boolean matches(Player p) {
        int playersValue = getCategoryValue(p);
        return playersValue < value;
    }

    private int getCategoryValue(Player player) {
        try {
            return (Integer) MatcherUtils.getFieldValue(player, fieldName);
        } catch (ReflectiveOperationException ex) {
            ex.printStackTrace();
            throw new IllegalStateException(
                "Player does not have field " + fieldName
            );
        }
    }
}
