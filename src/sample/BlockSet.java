package sample;

import java.io.Serializable;

public class BlockSet implements Serializable {

    private Block one;
    private Block two;
    private Block three;
    private Block four;
    private Block five;
    private Block six;
    private String image;

    public Block getOne() {
        return one;
    }

    public void setOne(Block one) {
        this.one = one;
    }

    public Block getTwo() {
        return two;
    }

    public void setTwo(Block two) {
        this.two = two;
    }

    public Block getThree() {
        return three;
    }

    public void setThree(Block three) {
        this.three = three;
    }

    public Block getFour() {
        return four;
    }

    public void setFour(Block four) {
        this.four = four;
    }

    public Block getFive() {
        return five;
    }

    public void setFive(Block five) {
        this.five = five;
    }

    public Block getSix() {
        return six;
    }

    public void setSix(Block six) {
        this.six = six;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
