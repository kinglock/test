import java.io.File
import java.net.{URL, URLClassLoader}

object testJarLoader extends App{
  var D = new MyLoggerB
  println(D.log(11111,11111))


  var jarFilePath = "/Users/BeNiceToFamily/IdeaProjects/test/out/artifacts/test_jar/test.jar"
  var jarFilePath1 = "/Users/BeNiceToFamily/IdeaProjects/test/out/artifacts/test1/test1.jar"
  var jarFilePath2 = "/Users/BeNiceToFamily/IdeaProjects/test/out/artifacts/test2/test2.jar"


  println("--------------- After reloading ------------------")

  // complex way of customizing URLClassLoader
  val classReload = new TestClassLoader().loadClass("MyLoggerBB2")
  val newInstance = classReload.newInstance
  println(s"Is the new instance an instance of [MyLoggerBB]:${newInstance.isInstanceOf[MyLoggerBB]}")
  println(s"But the classname is still called : ${newInstance.getClass.getCanonicalName}")
  println("Changed Hashcode:",classReload.hashCode())
  import scala.reflect._
  val ct = classTag[scala.collection.Seq[Any] ] // http://docs.scala-lang.org/overviews/reflection/typetags-manifests.html
  println(classReload.getMethod("BBhellow", new String().getClass, ct.runtimeClass).invoke(newInstance,"methodname",Seq(11111,11111)))

  // simple way of using URLClassLoader
  val D3  = URLClassLoader.newInstance(Array(new URL(s"jar:file:$jarFilePath2!/"))).loadClass("MyLoggerBB2")
  println(D3.getMethod("BBhellow", new String().getClass, ct.runtimeClass).invoke(D3.newInstance,"methodname",Seq(11111,11111)))


}


class TestClassLoader extends ClassLoader {

  var jarFilePath = "/Users/BeNiceToFamily/IdeaProjects/test/out/artifacts/test_jar/test.jar"
  var jarFilePath1 = "/Users/BeNiceToFamily/IdeaProjects/test/out/artifacts/test1/test1.jar"
  var jarFilePath2 = "/Users/BeNiceToFamily/IdeaProjects/test/out/artifacts/test2/test2.jar"


  override def loadClass(name: String): Class[_] = {
//    if (!name.equals("MyLoggerBB")) {
    try{
      return getClass.getClassLoader.loadClass(name)
    } catch {
      case e1: ClassNotFoundException => {
        try {
          //        val input = new FileInputStream("/Users/BeNiceToFamily/Downloads/test1/MyLoggerBB.class");
          //        val buf = new Array[Byte](10000)
          //        val len = input.read(buf)
          //        return defineClass(name, buf, 0, len);
          return new URLClassLoader(Array[URL](new File(jarFilePath2).toURI.toURL), getClass.getClassLoader).loadClass(name)
        } catch {
          case e:ClassNotFoundException => throw e
        }

      }
      case unknown: Exception => throw unknown
    }


  }
}
