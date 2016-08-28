import scala.language.dynamics

trait LoggerT extends Dynamic {
  //  def selectDynamic(names: Any*) = names
  def selectDynamic(names: Any) = names

  /*
  First: you can have multiple var args:
http://stackoverflow.com/questions/6803211/whats-the-difference-between-multiple-parameters-lists-and-multiple-parameters
def foo(as: Int*)(bs: Int*)(cs: Int*) = as.sum * bs.sum * cs.sum
   */

  // def updateDynamic(name: String)(value: Any) {
  //   println(s"You have just updated property '$name' with value: $value")
  // }


}

class MyLoggerC extends Dynamic{
  //  def applyDynamic(names:String*)(values:Int*) = {s"This is the message I want to do it dynamically:\nParameter Names:${names}\nParameter values:${values}"}
//  def applyDynamic(method: String)(values: Int*) = {
//    s"This is the message I want to do it dynamically:\nParameter Names:${method}\nParameter values:${values}"
//  }

  def applyDynamicNamed(method: String)(pair: (String, Any)) = {
    (pair._1, pair._2)
  }

  def applyDynamic(methodName: String)(args: Any*) {
    println(
      s"""|  methodName in C: $methodName,
          |args: ${args.mkString(",")}""".stripMargin)
  }
  /*
  Switching to this method
   */
//  def applyDynamic(methodName: String)(args: Any*) {
//    println(
//      s"""|  methodName in B: $methodName,
//          |args: ${args.mkString(",")}""".stripMargin)
//  }
}

class MyLoggerB extends Dynamic with LoggerT {
  def applyDynamicNamed(method: String)(pair: (String, Any)) = {
    s"This is the message I want to do it dynamically:\nMethod Name:${method}\nlogged value: -------xml------:\nAttribute:${pair._1}\nInt:${pair._2}"
  }
  def applyDynamic(methodName: String)(args: Any*) {
    //s"""|  CHANGED!!!!!!TO: $methodName,
//    s"""|  methodName in B: $methodName,
    println(
      s"""|  methodName in B: $methodName,
          |args: ${args.mkString(",")}""".stripMargin)
  }
}

class MyLoggerBB {
  def BBhellow(methodName: String)(args: Any*) {
    //s"""|  CHANGED!!!!!!TO: $methodName,
    //s"""|  methodName in B: $methodName,
    println(
      s"""|  methodName in BB: $methodName,
          |args: ${args.mkString(",")}""".stripMargin)
  }
}

object test extends App {

  val d = new MyLoggerC
  //  println(d.anyfield.size)
  println(d.log(1, 1))
  println(d.set(a = 1))
  println(d.log(a = "Eric"))

  //http://hacking-scala.org/post/49051516694/introduction-to-type-dynamic
  val myloggerB = new MyLoggerB
  println(myloggerB.log(XML = 1234567890))
  println(myloggerB.log("B","BB"))

//  val top = new Top
//  println("\n" + top.aa)
//  println("\n" + top.log("ni hao", "!"))


}


