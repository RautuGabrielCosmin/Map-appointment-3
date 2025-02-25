package domain;

// generic superclass - we can create how many particular classes we want and replace ID with String,Int etc...
public interface Identifiable<ID> {
    public ID getId();
    public void setId(ID id);
}
