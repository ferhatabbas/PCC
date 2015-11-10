package main.common;

import java.io.Serializable;

/**
 * Created by User on 2015-11-10.
 */
public class Couple implements Serializable {
    private static final long serialVersionUID=1L;
    private static final int STARTING_POINTS=100;

    private final User partener1;
    private final User partener2;
    private int PC1;
    private int PC2;

    public Couple(User partener1,User partener2) {
        this.partener1=partener1;
        this.partener2=partener2;
        PC1=PC2=STARTING_POINTS;
    }
}
