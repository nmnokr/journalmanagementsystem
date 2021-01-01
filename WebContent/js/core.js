var authenticatedPersonel;
var isyazar = false;
var iseditor = false;
 var ishakem = false;
// rollere göre div gösterme
$(document).ready(function() {
	authenticatePersonel();
});

function authenticatePersonel() {
	$
			.get(
					"http://localhost:6219/journalmanagementsystemm/login",
					function() {
						$
								.ajax({
									type : "GET",
									url : '/journalmanagementsystemm/rest/session/getAuthenticatedPersonel',
									contentType : "application/json",
									mimeType : "application/json",
									success : function(data) {

										authenticatedPersonel = data;
										console.log('your message');
										console.log("" + data);
										console.log("" + data.personelRoles);

										value = data.personelRoles;
										console.log("" + value);

										if (value == "admin") {
											isAdmin = true;
											$("#makalekayıtdiv").show();
											$("#izintalebidiv").show();
											$("#getallpersoneldiv").show();
											$("#tümizinlerdiv").show();
											$("#aizinhakedis").show();
											$("#izinhakedisdiv").show();
											$("#getAllFirstManagerApproval")
													.show();
											$("#getAllSecondManagerApproval")
													.show();
											$("#getAllHRApproval").show();
										}

										if (value == "hakem") {
											ishakem = true;
											$("#editorslemleri").fadeOut();
											$("#aizintalebi").fadeOut();
											$("#aiziniptali").fadeOut();
											$("#aizinhakedis").fadeOut();
											$("#izinhakedisdiv").fadeOut();
											$("#izintalebidiv").fadeOut();
											$("#hakemlistediv").show();
											$("#getallpersoneldiv").fadeOut();
											$("#makalekayıtdiv").fadeOut();
											$("#tümizinlerdiv").fadeOut();
											$("#apersonellistesi").fadeOut();
											$("#editormakaleliste").fadeOut();
											$("#aiziniptali").fadeOut(); 
											$("#aizinhakedis").fadeOut();
											$("#makalelistediv").fadeOut();
											$("#izinonayislemleridiv").fadeOut();
											$("#setdepartmanmanagersdiv").fadeOut();
											$("#kendiizinlerim").fadeOut();
											$("#tarihigecmeyenizinler").fadeOut();
											$("#hakemislemleri").fadeIn();
											
										}

										if (value == "yazar") {
											console.log("geldi");
											isyazar = true;
											$("#aiziniptali").hide();
											$("#makalelistediv").hide();
									 
											$("#aizintalebi").show();
											$("#editorslemleri").hide();
											$("#hakemislemleri").hide();
											$("#aizinhakedis").show();
										}
										if (value == "FirstManager") {
											isFirstManager = true;

											window.location
													.replace('rfid.html')
										}

										if (value == "editor") {
											iseditor = true;
											$("#aiziniptali").hide();
											$("#makalelistediv").hide();
									 
											$("#aizintalebi").hide();
											$("#editorslemleri").show();
											$("#hakemislemleri").hide();
											$("#aizinhakedis").hide();
											$("#apersonellistesi").hide();
											$("#adepartmanyonetimi").hide();
											$("#akendiizinlerim").hide();
											$("#atarihigecmeyenizinler").hide();
											$("#apersonelekle").hide();
											$("#adepartmanyonetimi").fadeOut();
													$("#apersonelekle").fadeOut();
										}

									 
									},
									error : function() {
										alert("error: authenticatePersonel");

									}
								});
					});
}

function logout() {
	$.get("/journalmanagementsystemm/logout", function() {
		window.location = "/journalmanagementsystemm";
	});

}
