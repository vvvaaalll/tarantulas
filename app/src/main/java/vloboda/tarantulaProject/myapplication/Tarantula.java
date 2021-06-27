package vloboda.tarantulaProject.myapplication;

public class Tarantula {
    String species, name, origin, owner, imgURL;

    long temper, venom, hairs;

    public Tarantula(){

    }

    public Tarantula(String species, String name, String origin, String owner, String imgURL, long temper, long venom, long hairs) {
        this.species = species;
        this.name = name;
        this.origin = origin;
        this.owner = owner;
        this.imgURL = imgURL;
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

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
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
}
