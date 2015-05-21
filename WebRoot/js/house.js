function validateCommunity() {
	return validateEmpty("house.community") ;
}

function validateAddress() {
	return validateEmpty("house.address") ;
}

function validateArea() {
	return validateNumber("house.area") ;
}

function validateTotal() {
	return validateEmpty("house.total") ;
}

function validateFloor() {
	return validateEmpty("house.floor") ;
}

function validatePrice() {
	return validateNumber("house.price") ;
}


function validateNote() {
	return validateEmpty("house.note") ;
}

function validateInsert() {
	return 	validateCommunity() && validateAddress() &&
			validateArea() && validateTotal() &&
			validateFloor() && validatePrice() &&
			validateNote() ;
}

function validateUpdate() {
	return 	validateCommunity() && validateAddress() &&
			validateArea() && validateTotal() &&
			validateFloor() && validatePrice() &&
			validateNote() ;
}