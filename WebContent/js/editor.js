var list;

$(document)
		.ready(
				function() {
					var TDEKLE = '</th><td>';
					var status = "";
					$
							.getJSON(
									"/journalmanagementsystemm/rest/Editor/WaitingGetAllMakale",
									function(result) {
										$
												.each(
														result,
														function(i, Article) {
															console.log(""
																	+ Article);
															console
																	.log("1. nace"
																			+ Article.nace);
															console
																	.log('<tr><th>'
																			+ Article.arid);
															if (Article.status == 0) {
																status = "ONAYINIZ BEKLİYOR";
															}
															$(
																	"#editormakalelistetable")
																	.append(
																			'<tr><th>'
																					+ Article.arid
																					+ TDEKLE
																					+ Article.nace
																					+ TDEKLE
																					+ Article.title
																					+ TDEKLE
																					+ status
																					+ TDEKLE
																					+ '<button  onclick="getAutoReferee('
																					+ Article.nace
																					+ ')"  type="button" class="btn btn-info btn-sm" data-toggle="modal" data-target="#editorAutoReferee">Atanan Hakemler</button>'
																					+ TDEKLE
																					+ '<button  onclick="getArticleInfo('
																					+ Article.arid
																					+ ')"  type="button" class="btn btn-info btn-sm" id="asd"   data-toggle="modal" data-target="#editorMakaleDetay">Göster</button>'
																					+ '</td></tr>');

														});
									});

				});

function getArticleInfo(id) {
	$("#deneme").text(id);
	$.ajax({
		type : "POST",
		url : '/journalmanagementsystemm/rest/Yazar/searchMakaleArid',
		contentType : "application/json",
		mimeType : "application/json",
		data : JSON.stringify(id),
		success : function(Article) {

			$("#article").text(Article.article);

			$("#id").text(Article.arid);

		},
		error : function() {
			alert("error : searchMakaleArid");

		}
	});
}

function getAutoReferee(nace) {
	list = new Array();

	$("#numan").empty();
	var TDEKLE = '</th><td>';
	console.log(">>" + nace);
	$.ajax({
		type :

		"POST",
		url : '/journalmanagementsystemm/rest/Editor/searchRefereeArid',

		contentType : "application/json",
		mimeType : "application/json",
		data :

		JSON.stringify(nace),
		success : function(result) {
			$.each(result, function(i, Author) {
				list.push(Author.aid);
				console.log(".........." + Author.aid);

				$("#numan").append(
						'<tr><th>' + Author.aid + TDEKLE + Author.surname
								+ TDEKLE + Author.name + TDEKLE + Author.nace
								+ TDEKLE + '</td></tr>');

			});

		},
		error : function() {
			alert("error : getAutoReferee");

		}
	});
}

/*
 * function getAutoReferee(nace) { console.log("geldi");
 * 
 * $.getJSON("/journalmanagementsystemm/rest/Editor/searchRefereeArid",
 * function(result) { $.each(result, function(i, Author) {
 * 
 * $("#hakemId").text(Author.aid);
 * 
 * $("#hakemSurnameName").text(Author.surname);
 * $("#hakemName").text(Author.name);
 * 
 * }); }); }
 */
function onayMakaleEditor(permissionId) {
	var obj = {};
	obj["arid"] = permissionId;
	obj["comment"] = $("#yorum").val();
	console.log(list.length);
	obj["referes"] = list;
	console.log("obje" + obj);
	$.ajax({
		type : "POST",
		url : '/journalmanagementsystemm/rest/Editor/onayMakaleEditor',
		contentType : "application/json",
		mimeType : "application/json",
		data : JSON.stringify(obj),
		success : function(result) {
			alert("Editor  : ");
			window.opener.popupCallback('pop some data'); // Call callback
			// function
			$("#editorMakaleDetay").modal({
				show : 'false'
			});
		},
		success : function() {

			$('#editorMakaleDetay').modal('hide');
		},
		error : function() {
			alert("error: onayMakaleEditor");
			$('#editorMakaleDetay').modal('hide');
		}

	});
}

function redMakaleEditor(permissionId) {
	var obj = {};
	obj["arid"] = permissionId;
	obj["comment"] = $("#yorum").val();

	$.ajax({
		type : "POST",
		url : '/journalmanagementsystemm/rest/Editor/redMakaleEditor',
		contentType : "application/json",
		mimeType : "application/json",
		data : JSON.stringify(obj),
		success : function(result) {
			alert("SUCCESS : ", data);
			$('#editorMakaleDetay').modal('hide');
		},
		error : function() {
			alert("error : redMakaleEditor");
			$('#editorMakaleDetay').modal('hide');
		}
	});
}
