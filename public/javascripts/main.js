function showContent(callUrl){
		$.ajax({
			  method: "GET",
			  url: callUrl,
			}).done(
			  function( html ) {
			    $("#content").html(html);
			  }).fail(function(jqXHR, textStatus) {
				  console.log("error: " + textStatus + " " + jqXHR);
			  });
}


function showUserEdit(id,path,parentId){
	var idPure = "";
	if(parentId!==undefined){
		idPure = parentId;
		parentId="/"+parentId; 
	}else{
		parentId="";
	}
	
	$.ajax({
		  method: "GET",
		  url: "/" + path + "/" + id + parentId,
		}).done(
		  function( html ) {
		    $('#editForm'+idPure).html(html);
		  }).fail(function(jqXHR, textStatus) {
			  console.log("error: " + textStatus + " " + jqXHR);
		  });
}
function showAddEntity(path,id){
	var idPure = "";
	if(id!==undefined){
		idPure = id;
		id="/"+id; 
	}else{
		id="";
	}
	
	$.ajax({
		  method: "GET",
		  url: "/" + path + "/-1" + id,
	}).done(
		function( html ) {
			$('#editForm'+idPure).html(html);
	}).fail(
		function(jqXHR, textStatus) {
			console.log("error: " + textStatus + " " + jqXHR);
	});
}
function deleteEntity(id,path){
	if(confirm('Delete ' + path + ' with id ' + id + '?')){
		$.ajax({
		  	method: "DELETE",
		  	url: "/" + path + "/" + id,
		}).done(
		  	function( html ) {
			  showContent("/" + path);
		}).fail(
			function(jqXHR, textStatus) {
			  console.log("error: " + textStatus + " " + jqXHR);
		});
	}
}

function saveEntity(path,parentId){
	if(parentId===undefined){
		parentId="";
	}
	
	$.ajax({
		  method: "POST",
		  url: "/" + path,
		  data: $("#editForm"+parentId).serialize(),
	}).done(
		function( html ) {
			$('.modal').modal('hide');
			setTimeout(function(){showContent(path);},1000);
	}).fail(
		function(jqXHR, textStatus) {
			console.log("error: " + textStatus + " " + jqXHR);
	});
}

function search(path){
	$.ajax({
		  method: "POST",
		  url: "/" + path + "/search",
		  data: $("#searchForm").serialize(),
	}).done(
		function( html ) {
			$("#content").html(html);
	}).fail(
		function(jqXHR, textStatus) {
			console.log("error: " + textStatus + " " + jqXHR);
	});
}

function showRowData(path,id,cssId){
	$.ajax({
		  method: "GET",
		  url: "/" + path + "/" + id
	}).done(
		function( html ) {
			$(cssId).html(html);
	}).fail(
		function(jqXHR, textStatus) {
			console.log("error: " + textStatus + " " + jqXHR);
	});
}
$( document ).ready(function() {
	$(function () {
		$('.datePicker').datetimepicker({
	        sideBySide: true
	    });
	});
});
