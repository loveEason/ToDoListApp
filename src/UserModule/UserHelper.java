package UserModule;


/**
* UserModule/UserHelper.java .
* 由IDL-to-Java 编译器 (可移植), 版本 "3.2"生成
* 从/home/huxijie/IntelliJIDEAProjects/ToDoListApp/src/idl/user.idl
* 2017年5月4日 星期四 上午10时16分09秒 CST
*/

abstract public class UserHelper
{
  private static String  _id = "IDL:UserModule/User:1.0";

  public static void insert (org.omg.CORBA.Any a, User that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static User extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_interface_tc (UserHelper.id (), "User");
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static User read (org.omg.CORBA.portable.InputStream istream)
  {
    return narrow (istream.read_Object (_UserStub.class));
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, User value)
  {
    ostream.write_Object ((org.omg.CORBA.Object) value);
  }

  public static User narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof User)
      return (User)obj;
    else if (!obj._is_a (id ()))
      throw new org.omg.CORBA.BAD_PARAM ();
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      _UserStub stub = new _UserStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

  public static User unchecked_narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof User)
      return (User)obj;
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      _UserStub stub = new _UserStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

}
