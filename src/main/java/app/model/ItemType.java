package app.model;

import app.model.base.CoreEntity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "item_types")
public class ItemType extends CoreEntity {

    @NotEmpty
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ItemType)) return false;

        ItemType type = (ItemType) o;

        return name.equals(type.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
