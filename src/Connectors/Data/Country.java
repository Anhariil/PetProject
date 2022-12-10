package Connectors.Data;

public class Country{
    private final String alfaTwo;
    private final String alfaThree;
    private final String name;
    private final String nameBrief;

    public Country(String alfaTwo, String alfaThree, String name, String nameBrief){
        this.alfaTwo = alfaTwo;
        this.alfaThree = alfaThree;
        this.name = name;
        this.nameBrief = nameBrief;
    }

    public String getAlfaTwo(){
        return this.alfaTwo;
    }

    public String getAlfaThree(){
        return this.alfaThree;
    }

    public String getName(){
        return this.name;
    }

    public String getNameBrief(){
        return this.nameBrief;
    }

    @Override
    public String toString() {
        return "alfaTwo = " + this.alfaTwo + "; alfaThree = " + this.alfaThree +
                "; name = " + this.name + "; nameBrief = " + this.nameBrief;
    }
}
