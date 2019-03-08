import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

public class Students {
    @NotEmpty(message = "姓名不能为空")
    private String name;

    @NotNull(message = "ID不能为空")
    private int id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
