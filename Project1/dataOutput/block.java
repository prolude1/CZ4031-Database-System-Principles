package Project1.dataOutput;

public class block {
    private int max_num_records;
    public int current_num_records;
    private entry[] all_records;

    public block(int size){
        this.max_num_records=size/entry.get_storage();
        this.current_num_records=0;
        this.all_records=new entry[this.max_num_records];
    }


}
