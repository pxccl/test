// 验证给定的元素名称的内容是否为空，如果空返回false，否则返回true
function validateEmpty(eleName) {	// 元素名称
	var obj = document.getElementById(eleName) ;
	if (validateElementNull(obj)) {
		return false ;
	}
	var objSpan = document.getElementById(eleName + "Span") ;
	if (obj.value.length > 0) {
		setSuccess(obj,objSpan) ;
		return true ;
	} else {
		setFailure(obj,objSpan) ;
		return false ;
	}
}
//判断两个元素的内容是否相同，其中eleNameA表示的是确认元素，eleNameB表示的确认数据
function validateCompare(eleNameA,eleNameB) {	// 元素名称
	if (validateEmpty(eleNameB)) {	// 保证数据已经正常输入了
		var objA = document.getElementById(eleNameA) ;
		var objB = document.getElementById(eleNameB) ;
		if (validateElementNull(objA) && validateElementNull(objB)) {
			return false ;
		}
		var objSpanA = document.getElementById(eleNameA + "Span") ;
		if (objA.value == objB.value) {	// 内容相同
			setSuccess(objA,objSpanA) ;
			return true ;
		} else {
			setFailure(objA,objSpanA) ;
			return false ;
		}
	} else {
		setFailure(obj,objSpan) ;
		return false ;
	}
	
}
// 执行数字验证
function validateNumber(eleName) {	// 元素名称
	var obj = document.getElementById(eleName) ;
	if (validateElementNull(obj)) {
		return false ;
	}
	var objSpan = document.getElementById(eleName + "Span") ;
	if (obj.value == "") {	// 如果为空数据那么就没有必要验证了
		return false ;
	}
	if (/^\d+(\.\d+)?$/.test(obj.value)) {
		setSuccess(obj,objSpan) ;
		return true ;
	} else {
		setFailure(obj,objSpan) ;
		return false ;
	}
}
// 执行正则验证
function validateRegex(eleName,regex) {
	var obj = document.getElementById(eleName) ;
	if (validateElementNull(obj)) {
		return false ;
	}
	var objSpan = document.getElementById(eleName + "Span") ;
	if (obj.value.length > 0) {
		if (regex.test(obj.value)) {
			setSuccess(obj,objSpan) ;
			return true ;
		} else {
			setFailure(obj,objSpan) ;
			return false ;
		}		
	} else {
		setFailure(obj,objSpan) ;
		return false ;
	}
}
function validateElementNull(obj) {
	return obj == null ;
}
// 此函数的功能主要是进行成功的表单的样式设置
function setSuccess(obj,objSpan) {
	obj.className = "success" ;
	if(objSpan != null) {
		objSpan.className = "right" ;
	}
}
// 此函数的功能主要是进行失败的表单的样式设置
function setFailure(obj,objSpan) {
	obj.className = "failure" ;
	if(objSpan != null) {
		objSpan.className = "wrong" ;
	}
}