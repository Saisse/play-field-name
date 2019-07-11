package controllers

import javax.inject.Inject
import play.api.data.Form
import play.api.mvc.{AbstractController, ControllerComponents}
import play.api.data.Forms._
import play.api.i18n.I18nSupport

class MainController @Inject() (components: ControllerComponents) extends AbstractController(components) with I18nSupport {

  private[this] val form = Form[String](single("testField" -> nonEmptyText))

  def index = Action { implicit request =>
    Ok(views.html.main(form))
  }

  def post = Action { implicit request =>
    form.bindFromRequest().fold({error =>
      println(error)
      Ok(views.html.main(error))
    }, { edit =>
      Ok(views.html.main(form))
    })
  }
}
