package CreatorModule;

/**
* CreatorModule/CreatorHolder.java .
* 由IDL-to-Java 编译器 (可移植), 版本 "3.2"生成
* 从/home/huxijie/IntelliJIDEAProjects/ToDoListApp/src/creator.idl
* 2017年5月2日 星期二 下午11时10分01秒 CST
*/

public final class CreatorHolder implements org.omg.CORBA.portable.Streamable
{
  public CreatorModule.Creator value = null;

  public CreatorHolder ()
  {
  }

  public CreatorHolder (CreatorModule.Creator initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = CreatorModule.CreatorHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    CreatorModule.CreatorHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return CreatorModule.CreatorHelper.type ();
  }

}
