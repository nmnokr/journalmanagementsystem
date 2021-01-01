//departman yöneticilerini ayarlama
var hakemno;
var aid;
$(document).ready(function() {
	$("#gondermakale").click(function() {
		var Article = {};
Article["aid"]=aid;
		Article["nace"] = $("#nacecode").val();
		Article["title"] = $("#arttittle").val();
		Article["article"] = $("#exampleFormControlTextarea1").val();
		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "/journalmanagementsystemm/rest/Yazar/addMakale",
			data : JSON.stringify(Article),
			dataType : 'json',
			cache : false,
			timeout : 100000,
			success : function(data) {
				alert("SUCCESS : ", data);
			 
			},
			error : function(e) {
				alert("ERROR : ", e);
			}
		});

	});
});

/*
 * $(document).ready(function(){ var TDEKLE='</th><td>';
 * 
 * $.getJSON("/journalmanagementsystemm/rest/Yazar/getAllIdMakale",
 * function(result){ $.each(result, function(i, Article){
 * console.log(""+Article); console.log('<tr><th>'+Article.arid);
 * $("#iziniptalsonuclari").append('<tr><th>'+Article.arid+TDEKLE+Article.nace+TDEKLE+Article.title+TDEKLE+Article.status+TDEKLE+'</th></tr>');
 * 
 * }); }); });
 */

var formfiller;
$(document)
		.ready(
				function() {
					$
							.getJSON(
									"/journalmanagementsystemm/rest/session/getAuthenticatedPersonel",
									function(personel) {
										$
												.each(
														personel.personelRoles,
														function(key, value) {
															/*
															 * if (value
															 * =="personel"){
															 * $("#dropboxgetirme").hide();
															 * 
															 * getDepartman(personel.department);
															 * $("#sicilno").text(personel.sicilno);
															 * $("#isebaslama").text(personel.isebaslangictarihi);
															 * getRightOfPermission(personel.sicilno);
															 * isPersonel=true; }
															 * 
															 * if (value
															 * =="FirstManager"){
															 * getFirstManagerApproval(personel.department); }
															 * if (value
															 * =="HR"){
															 * getHRApproval(); }
															 * if (value
															 * =="SecondManager"){
															 * getSecondManagerApproval(personel.department); }
															 * 
															 * 
															 */
															aid=personel.sicilno;
															if (value == "hakem") {
																Getallrefereesapproval(personel.sicilno)
																console
																		.log("hakem gidiyor"
																				+ formfiller);
																hakemno = personel.sicilno;

															} else if (value == "yazar") {

																console
																		.log("yazar geldi"
																				+ personel.sicilno);
																getnuman(personel.sicilno);
															} else {

															}

														});

									});
				});
/*
function getnuman(id) {
var durum="";
	 
	var TDEKLE = '</th><td>';
	console.log(">>" + id);
	$.ajax({
		type :

		"POST",
		url : '/journalmanagementsystemm/rest/Yazar/searchMakaleaid',

		contentType : "application/json",
		mimeType : "application/json",
		data :

		JSON.stringify(id),
		success : function(result) {
			$.each(result, function(i, Article) {
			 
				console.log(".........." + Article.aid);
if(Article.status==0){
	durum="Editor Bekleme Aşamasinda"
}else if(Article.status==1){
	durum="Editor Onay Verdi Hakem Aşamasinda";
}
else if(Article.status==2){
	durum="Editor Red Verdi Lütfen Tekrar Deneyiniz";
}
else if(Article.status==3){
	durum="Hakem Onay Verdi  Basım Aşamasinda";
}
else if(Article.status==4){
	durum="Hakem Red Verdi Lütfen Tekrar Deneyiniz ";
}
				$("#iziniptalsonuclari").append(
						'<tr><th>' + Article.nace + TDEKLE  + Article.title + TDEKLE + durum + TDEKLE   + '</td></tr>');

			});

		},
		error : function() {
			alert("error : getAutoReferee");

		}
	});
}*/
function getnuman(id) {
	var TDEKLE = '</td><td>';
	var durum = 'Henüz İncelenmedi';
	var sonuc = "";
	var status = "";
	$
			.ajax({
				type : "POST",
				url : '/journalmanagementsystemm/rest/Yazar/getAllIdMakale',
				contentType : "application/json",
				mimeType : "application/json",
				data : JSON.stringify(id),
				success : function(result) {
					console.log("sadsdadas" + id);
					$
							.each(
									result,
									function(i, Article) {

										console.log("sdasd" + Article);
										console.log("1. nace" + Article.nace);
										console.log('<tr><th>' + Article.arid);
										if(Article.status==0){
											durum="Editor Bekleme Aşamasinda"
										}else if(Article.status==1){
											durum="Editor Onay Verdi Hakem Aşamasinda";
										}
										else if(Article.status==2){
											durum="Editor Red Verdi Lütfen Tekrar Deneyiniz";
										}
										else if(Article.status==3){
											durum="Hakem Onay Verdi  Basım Aşamasinda";
										}
										else if(Article.status==4){
											durum="Hakem Red Verdi Lütfen Tekrar Deneyiniz ";
										}
													
										$("#iziniptalsonuclari").append(
												'<tr><th>' + Article.nace + TDEKLE  + Article.title + TDEKLE + durum + TDEKLE   + '</td></tr>');

									});

				},
				error : function() {
					alert("error : getFirstManagerApproval");

				}
			});
}

function Getallrefereesapproval(Aid) {
	var TDEKLE = '</td><td>';
	var durum = 'Henüz İncelenmedi';
	var sonuc = "";
	var status = "";
	$
			.ajax({
				type : "POST",
				url : '/journalmanagementsystemm/rest/Yazar/Getallrefereesapproval',
				contentType : "application/json",
				mimeType : "application/json",
				data : JSON.stringify(Aid),
				success : function(result) {
					console.log("sadsdadas" + result);
					$
							.each(
									result,
									function(i, Article) {

										console.log("sdasd" + Article);
										console.log("1. nace" + Article.nace);
										console.log('<tr><th>' + Article.arid);
										if (Article.status == 0) {
											status = "ONAYINIZ BEKLİYOR";
										}
										$("#hakemliste")
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

																+ '<button  onclick="getArticleInfo('
																+ Article.arid
																+ ')"  type="button" class="btn btn-info btn-sm" id="asd"   data-toggle="modal" data-target="#HakemMakaleDetay">Göster</button>'
																+ '</td></tr>');
									});

				},
				error : function() {
					alert("error : getFirstManagerApproval");

				}
			});
}

function onayMakaleHakem(permissionId) {

	$.ajax({
		type : "POST",
		url : '/journalmanagementsystemm/rest/Hakem/onayMakaleHakem',
		contentType : "application/json",
		mimeType : "application/json",
		data : JSON.stringify(hakemno),
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

function redMakaleHakem(permissionId) {

	$.ajax({
		type : "POST",
		url : '/journalmanagementsystemm/rest/Hakem/redMakaleHakem',
		contentType : "application/json",
		mimeType : "application/json",
		data : JSON.stringify(hakemno),
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

/*
 * $(document).ready(function(){ var TDEKLE='</th><td>';
 * 
 * $.getJSON("/journalmanagementsystemm/rest/Editor/WaitingGetAllMakale",
 * function(result){ $.each(result, function(i, Article){
 * console.log(""+Article); console.log('<tr><th>'+Article.arid);
 * $("#editormakalelistetable").append('<tr><th>'+Article.arid+TDEKLE+Article.nace+TDEKLE+Article.title+TDEKLE+Article.status+TDEKLE+'<button
 * onclick="getArticleInfo('+Article.arid+')" type="button" class="btn btn-info
 * btn-sm" data-toggle="modal" data-target="#editorMakaleDetay">Göster</button>'+'</td></tr>');
 * 
 * }); }); });
 * 
 */

