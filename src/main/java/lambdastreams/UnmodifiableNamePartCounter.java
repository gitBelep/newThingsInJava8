package lambdastreams;

public class UnmodifiableNamePartCounter {
    private int twoNames;
    private int threeNames;

    public UnmodifiableNamePartCounter(int twoNames, int threeNames) {
        this.twoNames = twoNames;
        this.threeNames = threeNames;
    }

// Methods & Constructor for the stream.reduce()
    public UnmodifiableNamePartCounter() {                              //ground: NamePartCounter(0,0)
    }

    public UnmodifiableNamePartCounter add(Employee e){                 //add next Emp
        if(e.getName().split(" ").length == 2){
            return new UnmodifiableNamePartCounter(this.twoNames + 1, this.threeNames);
        } else {        //3 parts
            return new UnmodifiableNamePartCounter(this.twoNames, this.threeNames + 1);
        }
    }

    public UnmodifiableNamePartCounter add(UnmodifiableNamePartCounter npc){    //add the other threads
        return new UnmodifiableNamePartCounter(this.twoNames + npc.getTwoNames(), this.threeNames + npc.getThreeNames());
    }


    public int getTwoNames() {
        return twoNames;
    }

    public int getThreeNames() {
        return threeNames;
    }

}
