package lambdastreams;

public class ModifiableNamePartCounter {
    private int twoNames;
    private int threeNames;

    public ModifiableNamePartCounter(int twoNames, int threeNames) {
        this.twoNames = twoNames;
        this.threeNames = threeNames;
    }

    public ModifiableNamePartCounter() {
        this.twoNames = 0;
        this.threeNames = 0;
    }

    public void add(Employee e){         //add next Emp
        if(e.getName().split(" ").length == 2){
            this.twoNames += 1;
        } else {        //3 parts
            this.threeNames += 1;
        }
    }
//    public OwnNamePartCounter add(Employee e){    //what for return ??
//        if(e.getName().split(" ").length == 2){
//            this.twoNames += 1;
//            return this;
//        } else {        //3 parts
//            this.threeNames += 1;
//            return this;
//        }
//    }

    public void addAll(ModifiableNamePartCounter otherNPC){        //add the other threads
        this.twoNames += otherNPC.getTwoNames();
        this.threeNames += otherNPC.getThreeNames();
    }


    public int getTwoNames() {
        return twoNames;
    }

    public int getThreeNames() {
        return threeNames;
    }

}
