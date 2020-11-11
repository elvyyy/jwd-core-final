package com.epam.jwd.core_final.exception;

public class UnknownEntityException extends RuntimeException {

    private final Object[] args;
    private final String entityName;

    public UnknownEntityException(String entityName) {
        super();
        this.entityName = entityName;
        this.args = null;
    }

    public UnknownEntityException(String entityName, Object[] args) {
        super();
        this.entityName = entityName;
        this.args = args;
    }

    @Override
    public String getMessage() {
        // todo
        // you should use entityName, args (if necessary)
        StringBuilder builder = new StringBuilder();
        builder.append("Entity " + entityName + " doesn't exist.");
        if (args != null) {
            for (Object arg : args) {
                builder.append(arg + ", ");
            }
        }
        return builder.toString();
    }
}
