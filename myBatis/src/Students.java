import java.util.Date;

public class Students {
    private int id;
    private Character name;
    private Date birth;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Character getName() {
        return name;
    }

    public void setName(Character name) {
        this.name = name;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String toString(){
        return "id:"+id+" name:"+name+" birth:"+birth;
    }
}
