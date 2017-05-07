package CreatorModule;

import UserModule.UserImpl;
import model.Person;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;
import server.ToDoListServer;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


/**
 * Created by huxijie on 17-5-3.
 * 实现用户管理creator.idl中定义的接口
 */
public class CreatorImpl extends CreatorPOA {
    private Map<String, Person> personsMap;
    private String name = null;
    private ORB orb;
    private ToDoListServer toDoListServer;
    private UserImpl userImpl;

    public CreatorImpl() {
        init();
    }

    //初始化
    private void init() {
        //从文件中读取用户列表,转化为HashMap
        try {
            FileInputStream fin = new FileInputStream("person.file");
            ObjectInputStream oin = new ObjectInputStream(fin);
            try {
                Object object = oin.readObject();
                personsMap = (HashMap<String, Person>) object;
            } catch (ClassNotFoundException e) {
                System.out.println("object cast error");
                personsMap = new HashMap<String, Person>();
            }
            oin.close();
            fin.close();
        } catch (Exception e) {
            personsMap = new HashMap<String, Person>();
        }
    }

    //将用户表保存到本地文件中
    private void saveData() {
        try {
            FileOutputStream fout = new FileOutputStream("person.file");
            ObjectOutputStream oout = new ObjectOutputStream(fout);
            oout.writeObject(personsMap);
            oout.flush();
            fout.flush();
            oout.close();
            fout.close();
        } catch (Exception e) {
            System.out.println("save error.");
            e.printStackTrace();
        }
    }

    public void setORB(ORB orb) {
        this.orb = orb;
    }

    public void setToDoListServer(ToDoListServer toDoListServer) {
        this.toDoListServer = toDoListServer;
    }

    //对用户名进行注册服务
    private void registerService(String name) {
        toDoListServer.registerUserName(name);
    }

    @Override
    public boolean login(String name, String password) {
        Person p = personsMap.get(name);
        if (p != null && p.getPassword().equals(password)) {
            this.name = name;

            //登录成功后对用户名进行注册服务
            registerService(name);

            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean register(String name, String password) {
        Person person = personsMap.get(name);
        if (person != null) {   //表中用户名为name的已存在
            return false;
        } else {
            personsMap.put(name, new Person(name, password));
            saveData();
            return true;
        }
    }
}
