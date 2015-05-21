// 负责指定对象的颜色改变
function changeColor(obj,color){
	obj.bgColor = color ;
}
// 执行删除前的确认操作
function deleteData() {
	return window.confirm("确定要删除此信息吗？") ;
} 
// 打开新的浏览窗口
function openWindow(url,width,height) {
	window.open(url,"信息查看", "width="+width+",height="+height+",scrollbars=no,resizable=no") ;
}
// 关闭窗口
function closeWindow() {
	window.close() ;
}
// 实现全选操作
function checkedAll(ckd,eleId) {
	var checkElements = document.all(eleId) ;	// 取得所有的复选框内容
	if (checkElements.length == undefined) {	// 表示不是一个数组
		checkElements.checked = ckd.checked ;	// 当前状态 
	} else {	// 它是一个数组，需要循环处理
		for (var x = 0 ; x < checkElements.length ; x ++) {
			checkElements[x].checked = ckd.checked ; 
		}
	}
} 
// 执行批量删除，需要传入要删除的url路径
function deleteBatch(url,eleId) {
	var flag = false ;
	var all = new Array() ;	// 建立一个数组
	var checkElements = document.all(eleId) ;	// 取得所有的复选框内容
	// 保证有选项被选中之后才执行删除的操作
	if (checkElements.length == undefined) {	// 表示不是一个数组
		if (checkedElements.checked == false) {
			alert("请先选择要删除的数据项！") ;
		} else {
			flag = true ;	// 可以执行删除操作
			all[0] = checkedElements.value ;	// 取出内容
		}
	} else {
		var foot = 0 ;
		for (var x = 0 ; x < checkElements.length ; x ++) {
			if(checkElements[x].checked) {
				flag = true ;
				all[foot ++] = checkElements[x].value ;
			} 
		}
		if (flag == false) {
			alert("请先选择要删除的数据项！") ;
		}
	}
	if (flag) {	// 此时包含有删除的数据项
		if (window.confirm("确定要删除这些数据吗？")) {
			if (all.length == 1) {	// 现在只有一个内容
				window.location = url + "?id=" + all[0] ;
			} else {	// 按照数组处理
				var param = "" ;	// 保存删除的数据
				for (var x = 0 ; x < all.length ; x ++) {
					param += "id=" + all[x] ;
					if (x < all.length - 1 ) {	// 后面还有内容
						param += "&" ; 
					}
				}
				window.location = url + "?" + param ;
			}
		}
	}
}

function deleteBatch2(url,eleId) {
	var flag = false ;
	var all = new Array() ;	// 建立一个数组
	var checkElements = document.all(eleId) ;	// 取得所有的复选框内容
	// 保证有选项被选中之后才执行删除的操作
	if (checkElements.length == undefined) {	// 表示不是一个数组
		if (checkedElements.checked == false) {
			alert("请先选择要删除的数据项！") ;
		} else {
			flag = true ;	// 可以执行删除操作
			all[0] = checkedElements.value ;	// 取出内容
		}
	} else {
		var foot = 0 ;
		for (var x = 0 ; x < checkElements.length ; x ++) {
			if(checkElements[x].checked) {
				flag = true ;
				all[foot ++] = checkElements[x].value ;
			} 
		}
		if (flag == false) {
			alert("请先选择要删除的数据项！") ;
		}
	}
	if (flag) {	// 此时包含有删除的数据项
		if (window.confirm("确定要删除这些数据吗？")) {
			if (all.length == 1) {	// 现在只有一个内容
				window.location = url + "&id=" + all[0] ;
			} else {	// 按照数组处理
				var param = "" ;	// 保存删除的数据
				for (var x = 0 ; x < all.length ; x ++) {
					param += "id=" + all[x] ;
					if (x < all.length - 1 ) {	// 后面还有内容
						param += "&" ; 
					}
				}
				window.location = url + "&" + param ;
			}
		}
	}
}

function deleteBatchBackSimple2(url,back,eleId) {
	var flag = false ;
	var all = new Array() ;	// 建立一个数组
	var checkElements = document.all(eleId) ;	// 取得所有的复选框内容
	// 保证有选项被选中之后才执行删除的操作
	if (checkElements.length == undefined) {	// 表示不是一个数组
		if (checkElements.checked == false) {
			alert("请先选择要删除的数据项！") ;
		} else {
			flag = true ;	// 可以执行删除操作
			all[0] = checkedElements.value ;	// 取出内容
		}
	} else {
		var foot = 0 ;
		for (var x = 0 ; x < checkElements.length ; x ++) {
			if(checkElements[x].checked) {
				flag = true ;
				all[foot ++] = checkElements[x].value ;
			} 
		}
		if (flag == false) {
			alert("请先选择要删除的数据项！") ;
		}
	}
	if (flag) {	// 此时包含有删除的数据项
		if (window.confirm("确定要删除这些数据吗？")) {
			var uri = "" ;
			if (all.length == 1) {	// 现在只有一个内容
				uri = "&id=" + all[0] ;
			} else {	// 按照数组处理
				var param = "" ;	// 保存删除的数据
				for (var x = 0 ; x < all.length ; x ++) {
					param += "id=" + all[x] ;
					if (x < all.length - 1 ) {	// 后面还有内容
						param += "&" ; 
					}
				}
				uri = "&" + param ;
			}
			window.location = url + uri + "&backurl=" + back ;
		}
	}
}

function deleteBatchBackSimple(url,back,eleId) {
	var flag = false ;
	var all = new Array() ;	// 建立一个数组
	var checkElements = document.all(eleId) ;	// 取得所有的复选框内容
	// 保证有选项被选中之后才执行删除的操作
	if (checkElements.length == undefined) {	// 表示不是一个数组
		if (checkElements.checked == false) {
			alert("请先选择要删除的数据项！") ;
		} else {
			flag = true ;	// 可以执行删除操作
			all[0] = checkedElements.value ;	// 取出内容
		}
	} else {
		var foot = 0 ;
		for (var x = 0 ; x < checkElements.length ; x ++) {
			if(checkElements[x].checked) {
				flag = true ;
				all[foot ++] = checkElements[x].value ;
			} 
		}
		if (flag == false) {
			alert("请先选择要删除的数据项！") ;
		}
	}
	if (flag) {	// 此时包含有删除的数据项
		if (window.confirm("确定要删除这些数据吗？")) {
			var uri = "" ;
			if (all.length == 1) {	// 现在只有一个内容
				uri = "?id=" + all[0] ;
			} else {	// 按照数组处理
				var param = "" ;	// 保存删除的数据
				for (var x = 0 ; x < all.length ; x ++) {
					param += "id=" + all[x] ;
					if (x < all.length - 1 ) {	// 后面还有内容
						param += "&" ; 
					}
				}
				uri = "?" + param ;
			}
			window.location = url + uri + "&backurl=" + back ;
		}
	}
}

//执行批量删除，需要传入要删除的url路径，同时设置返回路径
function deleteBatchBack(url,back,cp,ls,col,kw,eleId) {
	var flag = false ;
	var all = new Array() ;	// 建立一个数组
	var checkElements = document.all(eleId) ;	// 取得所有的复选框内容
	// 保证有选项被选中之后才执行删除的操作
	if (checkElements.length == undefined) {	// 表示不是一个数组
		if (checkedElements.checked == false) {
			alert("请先选择要删除的数据项！") ;
		} else {
			flag = true ;	// 可以执行删除操作
			all[0] = checkedElements.value ;	// 取出内容
		}
	} else {
		var foot = 0 ;
		for (var x = 0 ; x < checkElements.length ; x ++) {
			if(checkElements[x].checked) {
				flag = true ;
				all[foot ++] = checkElements[x].value ;
			} 
		}
		if (flag == false) {
			alert("请先选择要删除的数据项！") ;
		}
	}
	if (flag) {	// 此时包含有删除的数据项
		if (window.confirm("确定要删除这些数据吗？")) {
			var uri = "" ;	// 保存所有拼凑的内容
			if (all.length == 1) {	// 现在只有一个内容
				uri = "?id=" + all[0] ;
			} else {	// 按照数组处理
				var param = "" ;	// 保存删除的数据
				for (var x = 0 ; x < all.length ; x ++) {
					param += "id=" + all[x] ;
					if (x < all.length - 1 ) {	// 后面还有内容
						param += "&" ; 
					}
				}
				uri = "?" + param ;
			}
			window.location = url + uri + "&cp=" + cp + "&ls=" + ls + "&col=" + col + "&kw=" + kw + "&backurl=" + back ;
		}
	}
}

//执行批量删除，需要传入要删除的url路径，同时设置返回路径
function deleteBatchBack2(url,back,cp,ls,col,kw,eleId) {
	var flag = false ;
	var all = new Array() ;	// 建立一个数组
	var checkElements = document.all(eleId) ;	// 取得所有的复选框内容
	// 保证有选项被选中之后才执行删除的操作
	if (checkElements.length == undefined) {	// 表示不是一个数组
		if (checkedElements.checked == false) {
			alert("请先选择要删除的数据项！") ;
		} else {
			flag = true ;	// 可以执行删除操作
			all[0] = checkedElements.value ;	// 取出内容
		}
	} else {
		var foot = 0 ;
		for (var x = 0 ; x < checkElements.length ; x ++) {
			if(checkElements[x].checked) {
				flag = true ;
				all[foot ++] = checkElements[x].value ;
			} 
		}
		if (flag == false) {
			alert("请先选择要删除的数据项！") ;
		}
	}
	if (flag) {	// 此时包含有删除的数据项
		if (window.confirm("确定要删除这些数据吗？")) {
			var uri = "" ;	// 保存所有拼凑的内容
			if (all.length == 1) {	// 现在只有一个内容
				uri = "&id=" + all[0] ;
			} else {	// 按照数组处理
				var param = "" ;	// 保存删除的数据
				for (var x = 0 ; x < all.length ; x ++) {
					param += "id=" + all[x] ;
					if (x < all.length - 1 ) {	// 后面还有内容
						param += "&" ; 
					}
				}
				uri = "&" + param ;
			}
			window.location = url + uri + "&cp=" + cp + "&ls=" + ls + "&col=" + col + "&kw=" + kw + "&backurl=" + back ;
		}
	}
}

