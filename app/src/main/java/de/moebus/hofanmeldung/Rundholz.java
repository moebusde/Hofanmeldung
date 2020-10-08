package de.moebus.hofanmeldung;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "roundwood")
public class Rundholz {
    @PrimaryKey
    private int id;

    @ColumnInfo (name = "worknumber")
    private String worknumber;

    @ColumnInfo (name = "partienumber")
    private String partienumber;

    @ColumnInfo (name = "car")
    private String car;

    @ColumnInfo (name = "crane")
    private boolean crane;

    @ColumnInfo (name = "woodType")
    private String woodType;

    @ColumnInfo (name = "woodLength")
    private float woodLength;

    @ColumnInfo (name = "numberLogs")
    private int numberLogs;

    public Rundholz() {}

    public Rundholz(int id) {
        this.id = id;
    }

    public Rundholz(int id, String worknumber, String partienumber, String car, boolean crane, String woodType, float woodLength, int numberLogs) {
        this.id = id;
        this.worknumber = worknumber;
        this.partienumber = partienumber;
        this.car = car;
        this.crane = crane;
        this.woodType = woodType;
        this.woodLength = woodLength;
        this.numberLogs = numberLogs;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWorknumber() {
        return worknumber;
    }

    public void setWorknumber(String worknumber) {
        this.worknumber = worknumber;
    }

    public String getPartienumber() {
        return partienumber;
    }

    public void setPartienumber(String partienumber) {
        this.partienumber = partienumber;
    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }

    public boolean isCrane() {
        return crane;
    }

    public void setCrane(boolean crane) {
        this.crane = crane;
    }

    public String getWoodType() {
        return woodType;
    }

    public void setWoodType(String woodType) {
        this.woodType = woodType;
    }

    public float getWoodLength() {
        return woodLength;
    }

    public void setWoodLength(float woodLength) {
        this.woodLength = woodLength;
    }

    public int getNumberLogs() {
        return numberLogs;
    }

    public void setNumberLogs(int numberLogs) {
        this.numberLogs = numberLogs;
    }
}
