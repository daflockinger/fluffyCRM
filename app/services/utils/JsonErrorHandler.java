package services.utils;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.JsonNode;

import play.mvc.Result;
import play.mvc.Results;

public class JsonErrorHandler {

	private List<String> requiredFields;

	public boolean hasErrors(List<String> requiredFields, JsonNode json) {
		this.requiredFields = requiredFields;
		return json == null || StringUtils.isEmpty(getMissingFields(json));
	}

	public Result getErrorResult(List<String> requiredFields, JsonNode json) {
		this.requiredFields = requiredFields;

		if (json == null) {
			return Results.badRequest("Expecting Json data");
		} else {
			return Results.badRequest("Missing required fields: " + getMissingFields(json));
		}
	}

	private String getMissingFields(JsonNode json) {
		return requiredFields.stream().filter(field -> isFieldEmpty(json.get(field)))
				.collect(Collectors.joining(",", "[", "]"));
	}
	
	private boolean isFieldEmpty(JsonNode json){
		return json == null || StringUtils.isEmpty(json.asText());
	}
}
