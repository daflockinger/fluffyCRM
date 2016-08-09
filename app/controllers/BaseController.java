package controllers;

import com.google.inject.Inject;

import play.data.FormFactory;
import play.mvc.Controller;

public class BaseController extends Controller{
	@Inject
	protected FormFactory formFactory;
	@Inject
	protected WebJarAssets webJarAssets;
}
