/**
  * Created by BeNiceToFamily on 28/08/16.
  */
object testSpawn extends App{

//  println ("Starting....")
//
//  import scala.concurrent.ops._
//  spawn {
//    Thread sleep(10000L)
//  }
//  println ("Finished")


  // the for {} construct lets us execute multiple futures in parallel
  // to serially execute futures in specific orders, we use `andThen`
  // andThen ensures execution orders in what would otherwise be random

  import scala.concurrent.future
  import scala.concurrent.ExecutionContext.Implicits.global

  val whamBamThankYouMaam = future {
    Thread.sleep(500)
    Console.println("Wham!")
    Thread.sleep(10000)
    Console.println("Wham finished!")
  } andThen {
    case _ => {
      Thread.sleep(500)
      Console.println("Bam!")
    }
  } andThen {
    case _ => {
      Thread.sleep(500)
      Console.println("Thank you ma'am!")
    }
  }

  Console.println("Will you score?")
  while (!whamBamThankYouMaam.isCompleted){
    println(whamBamThankYouMaam.isCompleted)
    Thread sleep 1000L
  }
//  Thread.sleep(2000)
  println(whamBamThankYouMaam.isCompleted)



}
