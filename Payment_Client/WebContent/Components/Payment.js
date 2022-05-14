$(document).ready(function()
		{
	if ($("#alertSuccess").text().trim() == "")
	{
		$("#alertSuccess").hide();
	}
	$("#alertError").hide();
		});


//UPDATE==========================================
//$(document).on("click", ".btnUpdate", function(event)
//{
//$("#hidItemIDSave").val($(this).closest("tr").find('#hidItemIDUpdate').val());
//$("#itemCode").val($(this).closest("tr").find('td:eq(0)').text());
//$("#itemName").val($(this).closest("tr").find('td:eq(1)').text());
//$("#itemPrice").val($(this).closest("tr").find('td:eq(2)').text());
//$("#itemDesc").val($(this).closest("tr").find('td:eq(3)').text());
//});

$(document).on("click", ".btnUpdate", function(event)
		{
	$("#hidItemIDSave").val($(this).data("itemid"));
	$("#CardType").val($(this).closest("tr").find('td:eq(0)').text());
	$("#CardNumber").val($(this).closest("tr").find('td:eq(1)').text());
	$("#CardHolderName").val($(this).closest("tr").find('td:eq(2)').text());
	$("#CVC").val($(this).closest("tr").find('td:eq(3)').text());
	$("#CardExpireDate").val($(this).closest("tr").find('td:eq(4)').text());
	$("#PaymentDate").val($(this).closest("tr").find('td:eq(5)').text());
	$("#BillID").val($(this).closest("tr").find('td:eq(6)').text());
		});
////CLIENT-MODEL================================================================
function validateItemForm()
{
//	CODE
	if ($("#CardType").val().trim() == "")
	{
		return "Insert CardType.";
	}
//	NAME
	if ($("#CardNumber").val().trim() == "")
	{
		return "Insert CardNumber.";
	}
	9
//	PRICE-------------------------------
	if ($("#CardHolderName").val().trim() == "")
	{
		return "Insert CardHolderName.";
	}
	
	if ($("#CVC").val().trim() == "")
	{
		return "Insert CVC.";
	}
	
	if ($("#CardExpireDate").val().trim() == "")
	{
		return "Insert CardExpireDate.";
	}
	
	if ($("#PaymentDate").val().trim() == "")
	{
		return "Insert PaymentDate.";
	}
	
	if ($("#BillID").val().trim() == "")
	{
		return "Insert BillID.";
	}
	/*
//	is numerical value
	var tmpPrice = $("#itemPrice").val().trim();
	if (!$.isNumeric(tmpPrice))
	{
		return "Insert a numerical value for Item Price.";
	}
//	convert to decimal price
	$("#itemPrice").val(parseFloat(tmpPrice).toFixed(2));
//	DESCRIPTION------------------------
	if ($("#itemDesc").val().trim() == "")
	{
		return "Insert Item Description.";
	}
	return true;*/
}

$(document).on("click", "#btnSave", function(event)
		{
	// Clear alerts---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();
	// Form validation-------------------
	var status = validateItemForm();
	if (status != true)
	{
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}
	// If valid------------------------
	var type = ($("#hidItemIDSave").val() == "") ? "POST" : "PUT";
	$.ajax(
			{
				url : "PaymentAPI",
				type : type,
				data : $("#formItem").serialize(),
				dataType : "text",
				complete : function(response, status)
				{
					onItemSaveComplete(response.responseText, status);
				}
			});
		});

function onItemSaveComplete(response, status)
{
	if (status == "success")
	{
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();
			$("#divItemsGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error")
		{
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error")
	{
		$("#alertError").text("Error while saving.");
		$("#alertError").show();
	} else
	{
		$("#alertError").text("Unknown error while saving..");
		$("#alertError").show();
	}

	$("#hidItemIDSave").val("");
	$("#formItem")[0].reset();
}


$(document).on("click", ".btnRemove", function(event)
		{
	$.ajax(
			{
				url : "PaymentAPI",
				type : "DELETE",
				data : "PaymentID=" + $(this).data("itemid"),
				dataType : "text",
				complete : function(response, status)
				{
					onItemDeleteComplete(response.responseText, status);
				}
			});
		});

function onItemDeleteComplete(response, status)
{
	if (status == "success")
	{
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Successfully deleted.");
			$("#alertSuccess").show();
			$("#divItemsGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error")
		{
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error")
	{
		$("#alertError").text("Error while deleting.");
		$("#alertError").show();
	} else
	{
		$("#alertError").text("Unknown error while deleting..");
		$("#alertError").show();
	}
	
	
			
}


