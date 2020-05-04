package parser.model;

public abstract class ParsableFactory<P extends Parsable> {

    public abstract P createParsedObject(String[] valuesByColumnsOrder);

}
