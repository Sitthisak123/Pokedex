package cs.sitthisak.pokedex.model;

public class Pokemon {
    private String name;
    private String height;
    private String wieght;
    private String picUrl;

    public Pokemon() {
    }

    public Pokemon(String name, String height, String wieght, String picUrl) {
        this.name = name;
        this.height = height;
        this.wieght = wieght;
        this.picUrl = picUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWieght() {
        return wieght;
    }

    public void setWieght(String wieght) {
        this.wieght = wieght;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }
}
