$(document).ready(
		function() {
			$("#postMakale")
					.submit(
							function() {
								 
						 alert("sad");
								var Article = {};
							 
								Article["nace"] = $("#nacecode").val();
								Article["title"] = $("#arttittle").val();
								Article["article"] = $("#exampleFormControlTextarea1").val();
								 
								
								var roles = [];
							 
								if ($('#HRRole').is(':checked')) {
									roles.push("HR");
								}
								 
								personel["personelRoles"] = roles;
								$
										.ajax({
											type : "POST",
											contentType : "application/json",
											url : "/journalmanagementsystemm/rest/Yazar/addMakale",
											data : JSON
													.stringify(Article),
											dataType : 'json',
											cache : false,
											timeout : 100000,
											success : function(data) {
												alert("SUCCESS : ",data);
											 
											
											},
											error : function(e) {
												alert("ERROR : ",e);
											}
										});

							});
		});
