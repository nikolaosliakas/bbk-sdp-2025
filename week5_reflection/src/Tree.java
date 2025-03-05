public interface Tree {

    /** Returns number of branches*/
    Integer getNumBranches();

    /** Returns if tree bears fruit*/
    boolean bearsFruit();

    default Integer getNumOfSeeds(){
        return 0;
    };
}
