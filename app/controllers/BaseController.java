package controllers;

import com.google.inject.Inject;

import play.data.FormFactory;
import play.mvc.Controller;
import services.utils.JsonErrorHandler;

public class BaseController extends Controller{
	@Inject
	protected WebJarAssets webJarAssets;
	
	@Inject
	protected JsonErrorHandler jsonError;
}
