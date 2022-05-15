$(document).ready(function()
{
if ($("#alertSuccess").text().trim() == "")
{
$("#alertSuccess").hide();
}
$("#alertError").hide();
});
// SAVE ============================================
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







// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)



{
$("#hidItemIDSave").val($(this).data("itemid"));
$("#CardType").val($(this).closest("tr").find('td:eq(1)').text());
$("#CardNumber").val($(this).closest("tr").find('td:eq(2)').text());
$("#CardHolderName").val($(this).closest("tr").find('td:eq(3)').text());
$("#CVC").val($(this).closest("tr").find('td:eq(4)').text());
$("#CardExpireDate").val($(this).closest("tr").find('td:eq(5)').text());
$("#PaymentDate").val($(this).closest("tr").find('td:eq(9)').text());
$("#BillID").val($(this).closest("tr").find('td:eq(10)').text());
});







// DELETE==========================================
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


// CLIENT-MODEL================================================================
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
function validateItemForm()
{
// CODE
if ($("#CardType").val().trim() == "")
{
return "Insert CardType";
}

if ($("#CardNumber").val().trim() == "")
{
return "Insert CardNumber";
}

if ($("#CardHolderName").val().trim() == "")
{
return "Insert CardHolderName";
}

if ($("#CVC").val().trim() == "")
{
return "Insert CVC";
}

if ($("#CardExpireDate").val().trim() == "")
{
return "Insert CardExpireDate";
}

if ($("#PaymentDate").val().trim() == "")
{
return "Insert PaymentDate";
}

if ($("#BillID").val().trim() == "")
{
return "Insert BillID";
}

//is numerical value
var CVC = $("#CVC").val().trim();
if (!$.isNumeric(CVC))
{
	return "Insert a numerical value for CVC.";
}



return true;
}