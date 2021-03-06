package net.degols.example.election

import java.io.File

import akka.actor.{Actor, ActorSystem}
import net.degols.libs.election.{ConfigurationService, ElectionService, ElectionWrapper}
import org.slf4j.LoggerFactory
import javax.inject.{Inject, Singleton}
import com.typesafe.config.{Config, ConfigFactory}

@Singleton
class Worker @Inject()(electionService: ElectionService, configurationService: ConfigurationService, actorSystem: ActorSystem) extends ElectionWrapper(electionService, configurationService, actorSystem){
  private val logger = LoggerFactory.getLogger(getClass)
  override def receive: Receive = {
    case message =>
      logger.debug(s"[Worker] Received unknown message: $message")
  }
}
