import com.opensymphony.xwork2.ActionSupport;

public class StrutsTest extends ActionSupport {
    public String save(){
        System.out.println("测试");
        return SUCCESS;
    }
}
