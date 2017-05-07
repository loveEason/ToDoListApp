package UserModule;

/**
* UserModule/UserHolder.java .
* 由IDL-to-Java 编译器 (可移植), 版本 "3.2"生成
* 从/home/huxijie/IntelliJIDEAProjects/ToDoListApp/src/idl/user.idl
* 2017年5月4日 星期四 上午10时16分09秒 CST
*/

public final class UserHolder implements org.omg.CORBA.portable.Streamable
{
  public User value = null;

  public UserHolder ()
  {
  }

  public UserHolder (User initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = UserHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    UserHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return UserHelper.type ();
  }

}
