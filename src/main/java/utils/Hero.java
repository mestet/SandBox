package utils;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Hero {

    private String name;

    private String base;
    private String movement;
    private String attack;
    private String defence;
    private String spellPower;
    private String knowledge;

    private String exp;
    private String level;



    public Hero(String name, String hexBase){
        this.name = name;
        this.base = hexBase;

        int base = Integer.parseInt(hexBase, 16);


        this.movement = Integer.toHexString(base + 292);;
        this.attack = Integer.toHexString(base + 76);
        this.defence = Integer.toHexString(base + 80);
        this.spellPower = Integer.toHexString(base + 84);
        this.knowledge = Integer.toHexString(base + 88);

        this.exp = Integer.toHexString(base + 100);
        this.level = Integer.toHexString(base + 104);
    }
}
