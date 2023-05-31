import scala.concurrent.Future
import scala.util.{Try, Success, Failure, Random}
// Execution Context handles thread allocation of futures
import scala.concurrent.ExecutionContext.Implicits.global

case class Profile(id: String, name: String):
  def poke(anotherProfile: Profile): Unit =
    println(s"${this.name} poking ${anotherProfile.name}")

object SocialNetwork {
  // database of profiles
  val names = Map(
    "fb.id.1-zuck" -> "Mark",
    "fb.id.2-bill" -> "Bill",
    "fb.id.0-dummy" -> "Dummy"
    )

  val friends = Map(
    "fb.id.1-zuck" -> "fb.id.2-bill"
    )

  val random = new Random()

  // API
  def fetchProfile(id: String): Future[Profile] = Future {
    Thread.sleep(random.nextInt(300))
    Profile(id, names(id))
  }
  
  def fetchBestFriend(profile: Profile): Future[Profile] = Future {
    Thread.sleep(random.nextInt(400))
    val bfId = friends(profile.id)
    Profile(bfId, names(bfId))
  }

}

@main def m() =
  val mark = SocialNetwork.fetchProfile("fb.id.1-zuck")
  mark.onComplete {
    case Success(markProfile) => {
      val bill = SocialNetwork.fetchBestFriend(markProfile)
      bill.onComplete {
        case Success(billProfile) => markProfile.poke(billProfile)
        case Failure(e) => e.printStackTrace()
      }
    }
    case Failure(e) => e.printStackTrace()
  }


  // Functional composition
  val nameOnTheWall: Future[String] = mark.map(profile => profile.name)
  val marksBestFriend: Future[Profile] = mark.flatMap { profile =>
    SocialNetwork.fetchBestFriend(profile)
  }
  val zucksBestFriendRestricted = marksBestFriend.filter(_.name.startsWith("Z"))

  // for-comprehensions
  for {
    mark <- SocialNetwork.fetchProfile("fb.id.1-zuck")
    bill <- SocialNetwork.fetchBestFriend(mark)
  } mark.poke(bill)

  // fallbacks - recover, recoverWith, fallBackTo
  val aProfileNoMatterWhat = SocialNetwork.fetchProfile("unknown id").recover {
    case e: Throwable => Profile("fb.id.0-dummy", "Forever alone")
  }
  
  val aFetchedProfileNoMatterWhat = SocialNetwork.fetchProfile("unknown id").recoverWith {
    case e: Throwable => SocialNetwork.fetchProfile("fb.id.0-dummy")
  }

  val fallBackResult = SocialNetwork.fetchProfile("unknown id").fallBackTo(SocialNetwork.fetchProfile("fb.id.0-dummy"))

  Thread.sleep(2000)

