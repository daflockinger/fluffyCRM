package controllers;

import com.google.inject.Inject;

import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */

@Security.Authenticated(Secure.class)
public class HomeController extends Controller {

    @Inject WebJarAssets webJarAssets;

	
    public Result index() {
        return ok(views.html.index.render(webJarAssets));
    }

}
