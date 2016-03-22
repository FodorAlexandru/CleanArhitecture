package common.base;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;

/**
 * Created by shade_000 on 3/20/2016.
 */
public class ModelBase implements Serializable{
    //region Fields
    @DatabaseField(generatedId = true,columnName = "_id")
    private int id;
    //endregion

    //region Get Methods

    public int getId() {
        return id;
    }

    //endregion

    //region Set Methods

    public void setId(int id) {
        this.id = id;
    }

    //endregion

    //region Overrides

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ModelBase)) return false;

        ModelBase modelBase = (ModelBase) o;

        return id == modelBase.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    //endregion
}
