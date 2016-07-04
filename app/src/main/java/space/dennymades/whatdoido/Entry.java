package space.dennymades.whatdoido;

/**
 * Created by abrain on 7/4/16.
 */
public class Entry {
    private String date;
    private int item_id;

    public void setDate(String date){
        this.date = date;
    }

    public String getDate(){
        return this.date;
    }

    public void setItemId(int item_id){
        this.item_id=item_id;
    }

    public int getItem_id(){
        return this.item_id;
    }
}
