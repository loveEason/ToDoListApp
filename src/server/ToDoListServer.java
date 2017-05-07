package server;

import CreatorModule.Creator;
import CreatorModule.CreatorHelper;
import CreatorModule.CreatorImpl;
import UserModule.User;
import UserModule.UserHelper;
import UserModule.UserImpl;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

import java.util.Properties;

/**
 * Created by huxijie on 17-5-3.
 * 基于服务器框架,编写服务器对象
 */
public class ToDoListServer {
    private ORB orb;
    private POA rootPOA;
    private org.omg.CORBA.Object obj;
    private CreatorImpl creatorImpl;
    private UserImpl userImpl;
    private org.omg.CORBA.Object ref;
    private Creator creatorhref;
    private User userhref;
    private org.omg.CORBA.Object objRef;
    private NamingContextExt ncRef;

    public static void main(String[] args) {
        ToDoListServer toDoListServer = new ToDoListServer();
        toDoListServer.init();
    }

    //初始化,注册Creator到服务中
    private void init() {
        try {
            String[] args = {};
            Properties properties = new Properties();

            properties.put("org.omg.CORBA.ORBInitialHost", "127.0.0.1");  //指定ORB的ip地址
            properties.put("org.omg.CORBA.ORBInitialPort", "8080");       //指定ORB的端口

            //创建一个ORB实例
            orb = ORB.init(args, properties);

            //拿到根POA的引用,并激活POAManager,相当于启动了server
            obj = orb.resolve_initial_references("RootPOA");
            rootPOA = POAHelper.narrow(obj);
            rootPOA.the_POAManager().activate();

            //创建一个CreatorImpl实例
            creatorImpl = new CreatorImpl();
            creatorImpl.setToDoListServer(this);

            //从服务中得到对象的引用,并注册到服务中
            ref = rootPOA.servant_to_reference(creatorImpl);
            creatorhref = CreatorHelper.narrow(ref);

            //得到一个根命名的上下文
            objRef = orb.resolve_initial_references("NameService");
            ncRef = NamingContextExtHelper.narrow(objRef);

            //在命名上下文中绑定这个对象
            String name = "Creator";
            NameComponent path[] = ncRef.to_name(name);
            ncRef.rebind(path, creatorhref);

            System.out.println("server.ToDoListServer is ready and waiting....");

            //启动线程服务,等待客户端调用
            orb.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //对用户名进行注册服务
    public void registerUserName(String name) {
        try {
            //创建一个UserImpl实例
            userImpl = new UserImpl(name);
            userImpl.setORB(orb);

            //从服务中得到对象的引用,并注册到服务中
            ref = rootPOA.servant_to_reference(userImpl);
            userhref = UserHelper.narrow(ref);

            //在命名上下文中绑定这个对象
            NameComponent path[] = ncRef.to_name(name);
            ncRef.rebind(path, userhref);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
