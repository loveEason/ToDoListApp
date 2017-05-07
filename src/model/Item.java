package model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by huxijie on 17-5-3.
 * 事项类
 */
public class Item implements Serializable{
    private Date startTime;
    private Date endTime;
    private String label;

    public Item(Date startTime, Date endTime, String label) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.label = label;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public String getLabel() {
        return label;
    }
}
