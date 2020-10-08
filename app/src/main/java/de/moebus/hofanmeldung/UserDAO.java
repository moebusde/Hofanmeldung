package de.moebus.hofanmeldung;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDAO {

    @Insert
    public void addUser(User user);

    @Query("SELECT * FROM users WHERE id = 1")
    public User getUser();

    @Delete
    public void deleteUser(User user);




    @Insert
    public void addRundholzAssignment(Rundholz rundholz);

    @Query("UPDATE roundwood SET worknumber=:worknumber, partienumber=:partienumber, car=:car, crane=:crane, woodType=:woodType, woodLength=:woodLength, numberLogs=:numberLogs WHERE id=1" )
    public void updateRundholzAssignment(String worknumber, String partienumber, String car, boolean crane, String woodType, float woodLength, int numberLogs);

    @Query("SELECT * FROM roundwood WHERE id = 1")
    public Rundholz getRundholzAssignment();

    @Query("DELETE FROM roundwood WHERE id = :id")
    public void deleteRundholzAssignment(int id);

    @Delete
    public void deleteRundholzAssignment(Rundholz rundholz);

}
