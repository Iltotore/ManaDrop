package fr.il_totore.manadrop.lint;

public interface IssueType {

    Severity getSeverity();

    String getDescription();

    enum Severity {
        ERROR, WARNING
    }

    enum Spigot implements IssueType {
        BLOCK_MATERIAL(Severity.ERROR, "Attempted to set Block type with a non-block Material");

        private Severity severity;
        private String description;

        Spigot(Severity severity, String description) {
            this.severity = severity;
            this.description = description;
        }

        @Override
        public Severity getSeverity() {
            return severity;
        }

        @Override
        public String getDescription() {
            return description;
        }
    }
}