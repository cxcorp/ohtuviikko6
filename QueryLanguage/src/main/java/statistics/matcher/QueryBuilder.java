package statistics.matcher;

public class QueryBuilder {
    private Matcher matcher;

    public QueryBuilder() {
        this(new All());
    }

    private QueryBuilder(Matcher matcher) {
        this.matcher = matcher;
    }

    public Matcher build() {
        return matcher;
    }

    public QueryBuilder playsIn(String team) {
        return withNewAndMatcher(
            new PlaysIn(team)
        );
    }

    public QueryBuilder hasAtLeast(int value, String category) {
        return withNewAndMatcher(
            new HasAtLeast(value, category)
        );
    }

    public QueryBuilder hasFewerThan(int value, String category) {
        return withNewAndMatcher(
            new HasFewerThan(value, category)
        );
    }

    public QueryBuilder oneOf(Matcher... matchers) {
        return new QueryBuilder(
            new Or(matchers)
        );
    }

    private QueryBuilder withNewAndMatcher(Matcher newMatcher) {
        return new QueryBuilder(
            new And(matcher, newMatcher)
        );
    }
}
