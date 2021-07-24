package vloboda.tarantulaProject.myapplication;

public class Tarantula {
    String species;
    String name;
    String origin;
    String imgName;

    public String getTarantulaID() {
        return tarantulaID;
    }

    public void setTarantulaID(String tarantulaID) {
        this.tarantulaID = tarantulaID;
    }

    String tarantulaID;

    long temper, venom, hairs;

    public Tarantula(){

    }

    public Tarantula(String species, String name, String origin, String imgName, long temper, long venom, long hairs) {
        this.species = species;
        this.name = name;
        this.origin = origin;
        this.imgName = imgName;
        this.temper = temper;
        this.venom = venom;
        this.hairs = hairs;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public long getTemper() {
        return temper;
    }

    public void setTemper(long temper) {
        this.temper = temper;
    }

    public long getVenom() {
        return venom;
    }

    public void setVenom(long venom) {
        this.venom = venom;
    }

    public long getHairs() {
        return hairs;
    }

    public void setHairs(long hairs) {
        this.hairs = hairs;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }
}
