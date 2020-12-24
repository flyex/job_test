package testLdap;

import javax.naming.AuthenticationException;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.*;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;
import java.util.Hashtable;

public class TestLdap {

    public static void main(String[] args) throws Exception {


        Hashtable<String, String> env = new Hashtable<>();

        env.put(Context.SECURITY_AUTHENTICATION,"simple");

        env.put(Context.SECURITY_PRINCIPAL,"datayes\\svc-admin");

        env.put(Context.SECURITY_CREDENTIALS,"Aa778899");

        env.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.ldap.LdapCtxFactory"); // LDAP工厂类

        env.put("com.sun.jndi.ldap.connect.timeout", "3000");//连接超时设置为3秒

        env.put(Context.PROVIDER_URL, "ldap://10.20.202.41:389");// 默认端口389

        LdapContext ctx = new InitialLdapContext(env, null);

        // 域节点
        String searchBase = "CN=Users,DC=datayes,DC=com";
        // LDAP搜索过滤器类
        String searchFilter = "description=Data.User";   //description=Data.User
        // 搜索控制器
        SearchControls searchCtls = new SearchControls();
        // 创建搜索控制器
        searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE);
        String returnedAtts[] = { "name","employeeID","manager" };// 定制返回属性

        searchCtls.setReturningAttributes(returnedAtts); // 设置返回属性集

        NamingEnumeration<SearchResult> answer = ctx.search(searchBase, searchFilter, searchCtls);

        int res = 0;
        String str = null;

        while (answer.hasMoreElements()){
            SearchResult sr = answer.next();
            System.out.println("----------------------------");
            System.out.print("name:"+sr.getName());

            Attributes Attrs = sr.getAttributes();
            if (Attrs != null){
                //迭代属性
                for (NamingEnumeration ne = Attrs.getAll(); ne.hasMore();){

                    Attribute Attr = (Attribute) ne.next();// 得到下一个属性

                    System.out.println(" AttributeID=属性名："+ Attr.getID().toString());
                    // 读取属性值
                    for (NamingEnumeration e = Attr.getAll(); e.hasMore(); ) {
                        str =  e.next().toString();
                        System.out.println("    AttributeValues=属性值：" + str);
                    }
                    System.out.println("    ---------------");

                }
            }

            res++;
        }

        System.out.println("总量:"+ res);


    }
}
