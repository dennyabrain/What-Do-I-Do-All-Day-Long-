package space.dennymades.whatdoido;

/**
 * Created by abrain on 7/4/16.
 */
public class Item {
    private int item_id;
    private String item;
    private int order;

    public void setItem_id(int item_id){
        this.item_id=item_id;
    }

    public int getItem_id(){
        return this.item_id;
    }

    public void setItem(String item){
        this.item = item;
    }

    public String getItem(){
        return this.item;
    }

    public void setOrder(int order){
        this.order = order;
    }

    public int getOrder(){
        return this.order;
    }
}
