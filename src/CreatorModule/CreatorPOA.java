package CreatorModule;


/**
* CreatorModule/CreatorPOA.java .
* 由IDL-to-Java 编译器 (可移植), 版本 "3.2"生成
* 从/home/huxijie/IntelliJIDEAProjects/ToDoListApp/src/creator.idl
* 2017年5月2日 星期二 下午11时10分01秒 CST
*/

public abstract class CreatorPOA extends org.omg.PortableServer.Servant
 implements CreatorModule.CreatorOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("login", new java.lang.Integer (0));
    _methods.put ("register", new java.lang.Integer (1));
  }

  public org.omg.CORBA.portable.OutputStream _invoke (String $method,
                                org.omg.CORBA.portable.InputStream in,
                                org.omg.CORBA.portable.ResponseHandler $rh)
  {
    org.omg.CORBA.portable.OutputStream out = null;
    java.lang.Integer __method = (java.lang.Integer)_methods.get ($method);
    if (__method == null)
      throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);

    switch (__method.intValue ())
    {
       case 0:  // CreatorModule/Creator/login
       {
         String name = in.read_string ();
         String password = in.read_string ();
         boolean $result = false;
         $result = this.login (name, password);
         out = $rh.createReply();
         out.write_boolean ($result);
         break;
       }

       case 1:  // CreatorModule/Creator/register
       {
         String name = in.read_string ();
         String password = in.read_string ();
         boolean $result = false;
         $result = this.register (name, password);
         out = $rh.createReply();
         out.write_boolean ($result);
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:CreatorModule/Creator:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public Creator _this() 
  {
    return CreatorHelper.narrow(
    super._this_object());
  }

  public Creator _this(org.omg.CORBA.ORB orb) 
  {
    return CreatorHelper.narrow(
    super._this_object(orb));
  }


} // class CreatorPOA
