import com.google.inject.AbstractModule
import net.degols.example.election.Worker
import play.api.libs.concurrent.AkkaGuiceSupport

class Module extends AbstractModule with AkkaGuiceSupport {

  override def configure() = {

    bindActor[Worker]("worker")
  }

}
