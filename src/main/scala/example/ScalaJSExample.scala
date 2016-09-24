package example
import scala.scalajs.js.annotation.JSExport
import org.scalajs.dom
import org.scalajs.dom.html
import org.scalajs.dom.html.{LI, UList}

import scalatags.JsDom.TypedTag
import scalatags.JsDom.all._


object DB {

  sealed trait Status
  case object Landing extends Status
  case object Starting extends Status
  case class Flying(action: Action) extends Status
  case class Standing(action: Action) extends Status

  sealed trait Action
  case object Pecking extends Action //dziobać
  case object Shitting extends Action



  case class Pigeon(name: String, age: Int, status: Status)
  type PigeonId = Int

  var pigeons = Map[PigeonId, Pigeon](
    1 -> Pigeon("Lucy", 1, Landing),
    2 -> Pigeon("Bob", 2, Flying(Shitting)),
    3 -> Pigeon("Carmen", 2, Starting),
    4 -> Pigeon("Graham", 2, Standing(Pecking)),
    5 -> Pigeon("All", 2, Landing)
  )
}

@JSExport
object ScalaJSExample {

  @JSExport
  def main(pigeonsDiv: html.Element): Unit = {

    val showAlertButton = button(
      onclick := (() => dom.window.alert("helou")),
      "show alert"
    ).render

    val pigeonsList: UList = ul(DB.pigeons.map { case (id, pigeon) => li(pigeon.name) }.toSeq: _*).render

    pigeonsDiv.innerHTML = ""
    pigeonsDiv.appendChild(pigeonsList)
    pigeonsDiv.appendChild(pigeonsList)

    pigeonsDiv.appendChild(showAlertButton)
  }
}
