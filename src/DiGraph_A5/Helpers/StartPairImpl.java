package DiGraph_A5.Helpers;

public class StartPairImpl implements IStartPair {
	
    private String theValue;
    private long priority;

    public StartPairImpl(String givenVal, long dist) {
    	theValue = givenVal;
        priority = dist;
    }

    public String getValue() {
        return theValue;
    }

    public long getPriority() {
        return priority;
    }
}