// CLIENT-MODEL================================================================
function validatePaymentForm()
{
// NAME
if ($("#name").val().trim() == "")
{
return "Insert Account Holder Name.";
}
// ACCOUNT_NUMBER
if ($("#accNumber").val().trim() == "")
{
return "Insert Account Number.";
}
// CVV
if ($("#cvv").val().trim() == "")
{
return "Insert CVV.";
}
// EXP_DATE
if ($("#expDate").val().trim() == "")
{
return "Insert Expire Date.";
}
// PHONE-------------------------------
if ($("#phone").val().trim() == "")
{
return "Insert Phone Number.";
}
// EMAIL------------------------
if ($("#email").val().trim() == "")
{
return "Insert Email Address.";
}
return true;
}


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
var status = validatePaymentForm();
if (status != true)
{
$("#alertError").text(status);
$("#alertError").show();
return;
}
// If valid------------------------
$("#formPayment").submit();
});







// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
{
$("#hidPaymentIDSave").val($(this).closest("tr").find('#hidPaymentIDUpdate').val());
$("#name").val($(this).closest("tr").find('td:eq(0)').text());
$("#accNumber").val($(this).closest("tr").find('td:eq(1)').text());
$("#cvv").val($(this).closest("tr").find('td:eq(2)').text());
$("#expDate").val($(this).closest("tr").find('td:eq(3)').text());
$("#phone").val($(this).closest("tr").find('td:eq(4)').text());
$("#email").val($(this).closest("tr").find('td:eq(5)').text());
});
// CLIENT-MODEL================================================================
function validatePaymentForm()
{
// NAME
if ($("#name").val().trim() == "")
{
return "Insert Account Holder Name.";
}
// ACCOUNT NUMBER
if ($("#accNumber").val().trim() == "")
{
return "Insert Account Number.";
}

// CVV-------------------------------
if ($("#cvv").val().trim() == "")
{
return "Insert CVV.";
}
// EXPDATE-------------------------------
if ($("#expDate").val().trim() == "")
{
return "Insert Expire Date.";
}
// PHONE-------------------------------
if ($("#phone").val().trim() == "")
{
return "Insert Phone Number.";
}
// EMAIL------------------------
if ($("#email").val().trim() == "")
{
return "Insert Email Address.";
}
return true;
}







$(document).on("click", "#btnSave", function(event) 
{ 
// Clear alerts---------------------
 $("#alertSuccess").text(""); 
 $("#alertSuccess").hide(); 
 $("#alertError").text(""); 
 $("#alertError").hide(); 
// Form validation-------------------
var status = validatePaymentForm(); 
if (status != true) 
 { 
 $("#alertError").text(status); 
 $("#alertError").show(); 
 return; 
 } 
// If valid------------------------
var type = ($("#hidPaymentIDSave").val() == "") ? "POST" : "PUT"; 
 $.ajax( 
 { 
 url : "PaymentsAPI", 
 type : type, 
 data : $("#formPayment").serialize(), 
 dataType : "text", 
 complete : function(response, status) 
 { 
 onPaymentSaveComplete(response.responseText, status); 
 } 
 }); 
});


function onPaymentSaveComplete(response, status) 
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully saved."); 
 $("#alertSuccess").show(); 
 $("#divPaymentsGrid").html(resultSet.data); 
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

 $("#hidPaymentIDSave").val(""); 
 $("#formPayment")[0].reset(); 
}


$(document).on("click", ".btnRemove", function(event) 
{ 
 $.ajax( 
 { 
 url : "PaymentsAPI", 
 type : "DELETE", 
 data : "paymentID=" + $(this).data("paymentid"),
 dataType : "text", 
 complete : function(response, status) 
 { 
 onPaymentDeleteComplete(response.responseText, status); 
 } 
 }); 
});


function onPaymentDeleteComplete(response, status) 
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully deleted."); 
 $("#alertSuccess").show(); 
 $("#divPaymentsGrid").html(resultSet.data); 
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