package net.degols.example.election

import akka.actor.Actor
import net.degols.filesgate.libs.election.{ConfigurationService, ElectionService, ElectionWrapper}
import org.slf4j.LoggerFactory
import javax.inject.{Inject, Singleton}

@Singleton
class Worker @Inject()(electionService: ElectionService, configurationService: ConfigurationService) extends ElectionWrapper(electionService, configurationService){
  private val logger = LoggerFactory.getLogger(getClass)

  override def receive: Receive = {
    case message =>
      logger.debug(s"Received unknown message: $message")
  }
}
