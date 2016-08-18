package controllers;

import play.mvc.Result;
import play.mvc.Security;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */

@Security.Authenticated(Secure.class)
public class HomeController extends BaseController {
	
    public Result index() {
        return ok(views.html.mainHome.render(webJarAssets));
    }

}
