package fr.il_totore.manadrop.lint;

public class Issue {

    private IssueType type;
    private Class<?> clazz;

    private Issue(IssueType type, Class<?> clazz) {
        this.type = type;
        this.clazz = clazz;
    }

    public static Issue of(IssueType type, Class<?> clazz) {
        return new Issue(type, clazz);
    }
}
