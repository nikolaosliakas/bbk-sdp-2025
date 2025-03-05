public class LemonTree implements Tree{
    Integer numBranches;

    // Default constructor;
    public LemonTree(){};
    public LemonTree(Integer numBranches){
        this.numBranches = numBranches;
    }

    @Override
    public Integer getNumBranches() {
        return numBranches;
    }

    @Override
    public boolean bearsFruit() {
        return true;
    }
}
