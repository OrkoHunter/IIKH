import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Recipes implements Serializable {

    public void addNew(Recipe r) {
        list.add(r);
    }

    public int size() {
        return list.size();
    }

    public void add(String name, String ingredients, String procedure) {
        int index = list.size() + 1;

        Recipe r = new Recipe();
        r.name = name;
        r.ingredients = ingredients;
        r.procedure = procedure;
        this.addNew(r);
    }

    public void edit(int i, String name, String ingredients, String procedure) {
        Recipe r = new Recipe();

        r.name = name;
        r.ingredients = ingredients;
        r.procedure = procedure;
        list.set(i, r);
    }

    public ArrayList<Recipe> list = new ArrayList<Recipe>();
}

class Recipe implements Serializable {
    public String name = new String();
    public String ingredients = new String();
    public String procedure = new String();
}
