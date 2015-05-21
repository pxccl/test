function validateUid(){
	return validateEmpty("user.uid");
}
function validatePassword(){
	return validateEmpty("user.password");
}
function validateConfPassword(){
	return validateCompare("confpassword","user.password") ;
}

function validateCode() {
	return validateRegex("code",/^[0-9a-zA-Z]{4}$/) ;
}

function validateRegeForm() {
	if( validateUid() && validatePassword() && validateConfPassword() ){
		return true;
	}else{
		return false;
	}
	
}
function validateCode(){
	return validateEmpty("code");
}
function validateLoginForm() {
	if( validateUid() && validatePassword() && validateCode() ){
		return true;
	}else{
		return false;
	}
	
}
function validateOldPassword(){
	return validateEmpty("oldpassword");
}
function validateNewPassword(){
	return validateEmpty("newpassword");
}
function validateConfPassword(){
	return validateCompare("confpassword","newpassword") ;
}
function validateUpdateForm(){
	if( validateOldPassword() && validateNewPassword() && validateConfPassword() ){
		return true;
	}else{
		return false;
	}
}