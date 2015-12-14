package common;

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
    public int GetPCpartener1(){
        return PC1;
    }
    public int GetPCpartener2(){
        return PC2;
    }
    public void SetPCpartener1(int PC1){
        this.PC1=PC1+this.PC1;
    }
    public void SetPCpartener2(int PC2){
        this.PC2=PC2+this.PC2;
    }

    public User getPartener1() {
        return partener1;
    }

    public User getPartener2() {
        return partener2;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Couple)) return false;

        Couple couple = (Couple) o;

        return (partener1.equals(couple.partener1) && partener2.equals(couple.partener2));

    }

    @Override
    public int hashCode() {
        return (partener1.hashCode()+partener2.hashCode());
    }

}
