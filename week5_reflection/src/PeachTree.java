public class PeachTree implements Tree{
    Integer numBranches;
    String location;
    Boolean bearsFruit;

//    public PeachTree(){};
    public PeachTree(Integer numBranches, String location){
        this.numBranches = numBranches;
        this.location = location;
        this.bearsFruit = true;

    }
    @Override
    public Integer getNumBranches() {
        return numBranches;
    }

    @Override
    public boolean bearsFruit() {
        return bearsFruit;
    }
}
