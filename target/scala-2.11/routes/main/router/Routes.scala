
// @GENERATOR:play-routes-compiler
// @SOURCE:/home/synerzip/Personal/poc/sale-purchase-service/conf/routes
// @DATE:Thu Mar 17 17:14:12 IST 2016

package router

import play.core.routing._
import play.core.routing.HandlerInvokerFactory._
import play.core.j._

import play.api.mvc._

import _root_.controllers.Assets.Asset

class Routes(
  override val errorHandler: play.api.http.HttpErrorHandler, 
  // @LINE:6
  HomeController_1: controllers.HomeController,
  // @LINE:8
  CountController_0: controllers.CountController,
  // @LINE:11
  ApplicationController_4: controllers.ApplicationController,
  // @LINE:15
  AsyncController_2: controllers.AsyncController,
  // @LINE:18
  Assets_3: controllers.Assets,
  val prefix: String
) extends GeneratedRouter {

   @javax.inject.Inject()
   def this(errorHandler: play.api.http.HttpErrorHandler,
    // @LINE:6
    HomeController_1: controllers.HomeController,
    // @LINE:8
    CountController_0: controllers.CountController,
    // @LINE:11
    ApplicationController_4: controllers.ApplicationController,
    // @LINE:15
    AsyncController_2: controllers.AsyncController,
    // @LINE:18
    Assets_3: controllers.Assets
  ) = this(errorHandler, HomeController_1, CountController_0, ApplicationController_4, AsyncController_2, Assets_3, "/")

  import ReverseRouteContext.empty

  def withPrefix(prefix: String): Routes = {
    router.RoutesPrefix.setPrefix(prefix)
    new Routes(errorHandler, HomeController_1, CountController_0, ApplicationController_4, AsyncController_2, Assets_3, prefix)
  }

  private[this] val defaultPrefix: String = {
    if (this.prefix.endsWith("/")) "" else "/"
  }

  def documentation = List(
    ("""GET""", this.prefix, """controllers.HomeController.index"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """count""", """controllers.CountController.count"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """registerCustomer""", """controllers.ApplicationController.registerCustomer"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """registerSupplier""", """controllers.ApplicationController.registerSupplier"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """message""", """controllers.AsyncController.message"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """assets/""" + "$" + """file<.+>""", """controllers.Assets.versioned(path:String = "/public", file:Asset)"""),
    Nil
  ).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
    case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
    case l => s ++ l.asInstanceOf[List[(String,String,String)]]
  }}


  // @LINE:6
  private[this] lazy val controllers_HomeController_index0_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix)))
  )
  private[this] lazy val controllers_HomeController_index0_invoker = createInvoker(
    HomeController_1.index,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "index",
      Nil,
      "GET",
      """ An example controller showing a sample home page""",
      this.prefix + """"""
    )
  )

  // @LINE:8
  private[this] lazy val controllers_CountController_count1_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("count")))
  )
  private[this] lazy val controllers_CountController_count1_invoker = createInvoker(
    CountController_0.count,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.CountController",
      "count",
      Nil,
      "GET",
      """ An example controller showing how to use dependency injection""",
      this.prefix + """count"""
    )
  )

  // @LINE:11
  private[this] lazy val controllers_ApplicationController_registerCustomer2_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("registerCustomer")))
  )
  private[this] lazy val controllers_ApplicationController_registerCustomer2_invoker = createInvoker(
    ApplicationController_4.registerCustomer,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.ApplicationController",
      "registerCustomer",
      Nil,
      "POST",
      """Application requests""",
      this.prefix + """registerCustomer"""
    )
  )

  // @LINE:12
  private[this] lazy val controllers_ApplicationController_registerSupplier3_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("registerSupplier")))
  )
  private[this] lazy val controllers_ApplicationController_registerSupplier3_invoker = createInvoker(
    ApplicationController_4.registerSupplier,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.ApplicationController",
      "registerSupplier",
      Nil,
      "POST",
      """""",
      this.prefix + """registerSupplier"""
    )
  )

  // @LINE:15
  private[this] lazy val controllers_AsyncController_message4_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("message")))
  )
  private[this] lazy val controllers_AsyncController_message4_invoker = createInvoker(
    AsyncController_2.message,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.AsyncController",
      "message",
      Nil,
      "GET",
      """ An example controller showing how to write asynchronous code""",
      this.prefix + """message"""
    )
  )

  // @LINE:18
  private[this] lazy val controllers_Assets_versioned5_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("assets/"), DynamicPart("file", """.+""",false)))
  )
  private[this] lazy val controllers_Assets_versioned5_invoker = createInvoker(
    Assets_3.versioned(fakeValue[String], fakeValue[Asset]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Assets",
      "versioned",
      Seq(classOf[String], classOf[Asset]),
      "GET",
      """ Map static resources from the /public folder to the /assets URL path""",
      this.prefix + """assets/""" + "$" + """file<.+>"""
    )
  )


  def routes: PartialFunction[RequestHeader, Handler] = {
  
    // @LINE:6
    case controllers_HomeController_index0_route(params) =>
      call { 
        controllers_HomeController_index0_invoker.call(HomeController_1.index)
      }
  
    // @LINE:8
    case controllers_CountController_count1_route(params) =>
      call { 
        controllers_CountController_count1_invoker.call(CountController_0.count)
      }
  
    // @LINE:11
    case controllers_ApplicationController_registerCustomer2_route(params) =>
      call { 
        controllers_ApplicationController_registerCustomer2_invoker.call(ApplicationController_4.registerCustomer)
      }
  
    // @LINE:12
    case controllers_ApplicationController_registerSupplier3_route(params) =>
      call { 
        controllers_ApplicationController_registerSupplier3_invoker.call(ApplicationController_4.registerSupplier)
      }
  
    // @LINE:15
    case controllers_AsyncController_message4_route(params) =>
      call { 
        controllers_AsyncController_message4_invoker.call(AsyncController_2.message)
      }
  
    // @LINE:18
    case controllers_Assets_versioned5_route(params) =>
      call(Param[String]("path", Right("/public")), params.fromPath[Asset]("file", None)) { (path, file) =>
        controllers_Assets_versioned5_invoker.call(Assets_3.versioned(path, file))
      }
  }
}
