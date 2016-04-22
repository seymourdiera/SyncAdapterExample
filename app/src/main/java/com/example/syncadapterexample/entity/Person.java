package com.example.syncadapterexample.entity;

import com.example.syncadapterexample.data.MyDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by mac on 4/21/16.
 */
@Table(database = MyDatabase.class)
public class Person extends BaseModel {

    @Column(name = "_id")
    @PrimaryKey(autoincrement = true)
    private int id;

    @Column
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
