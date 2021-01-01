$(document).ready(
		function() {
			function update() {
				$("#rfidlistetable").html("");
				var TDEKLE = '</th><td>';

				$.getJSON("/journalmanagementsystemm/rest/Rfid/GetAllRfid",
						function(result) {
							$.each(result, function(i, Rfid) {
								console.log("" + Rfid);
								console.log('<tr><th>' + Rfid.rfidno);
								var uyari;
								if (Rfid.durum == 0) {
									uyari = "dısarda";
								} else {
									uyari = "icesrde";
									$("Label").css("color", "red");
								}
								$("#rfidlistetable")
										.append(
												'<tr><th>' + Rfid.rfidno
														+ TDEKLE + uyari
														+ TDEKLE
														+ Rfid.departman
														+ '</td></tr>');

							});

						});
			}

			setInterval(function() {
				$("#rfidlistetable").html("");
				var TDEKLE = '</th><td>';

				$.getJSON("/journalmanagementsystemm/rest/Rfid/GetAllRfid",
						function(result) {
							$.each(result, function(i, Rfid) {
								var uyari;
								if (Rfid.durum == 0) {
									uyari = "DISARDA";
								} else {
									uyari = "ICERDE";
									$("Label").css("color", "red");
								}
								$("#rfidlistetable")
										.append(
												'<tr><th>' + Rfid.rfidno
														+ TDEKLE + uyari
														+ TDEKLE
														+ Rfid.departman
														+ '</td></tr>');

							});

						});
			}, 2000);
			update();
		});

$(document).ready(function() {
	$("#gonderrfid").click(function() {
		var Article = {};

		Rfid["ad"] = $("#rfidad").val();
		Rfid["departman"] = $("#departman").val();
		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "/journalmanagementsystemm/rest/Rfid/addRfid",
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
