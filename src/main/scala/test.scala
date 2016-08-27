import scala.language.dynamics
class MyLoggerA extends Dynamic{
  def selectDynamic(names: String*) = names
  /*
  First: you can have multiple var args:
http://stackoverflow.com/questions/6803211/whats-the-difference-between-multiple-parameters-lists-and-multiple-parameters
def foo(as: Int*)(bs: Int*)(cs: Int*) = as.sum * bs.sum * cs.sum
   */


//  def applyDynamic(names:String*)(values:Int*) = {s"This is the message I want to do it dynamically:\nParameter Names:${names}\nParameter values:${values}"}
  def applyDynamic(method:String)(values:Int*) = {s"This is the message I want to do it dynamically:\nParameter Names:${method}\nParameter values:${values}"}
 def applyDynamicNamed(method: String)(pair:(String, Any)) = {(pair._1,pair._2)}

}

object myloggerB extends Dynamic{
  def applyDynamicNamed(method: String)(pair:(String, Any)) = {s"This is the message I want to do it dynamically:\nMethod Name:${method}\nlogged value: -------xml------:\nAttribute:${pair._1}\nInt:${pair._2}"}
}



object test extends App{

  val d = new MyLoggerA
  println(d.anyfield.size)
  println(d.method(0,1))
  println(d.set(a=1))
  println(d.log(a="Eric"))

//http://hacking-scala.org/post/49051516694/introduction-to-type-dynamic
  println(myloggerB.log(XML= 1234567890))


}


