
/** 全局变量token的key */
var theToken = 'token';

/** 返回类型 */
var resultSuccess = "SUCCESS";
var resultError = "ERROR";

$(function() {

	/** 菜单跳转 */
	$(".main_left a").on("click", function() {
		var address = $(this).attr("data-src");
		console.log(address)
		$("iframe").attr("src", address);
	});

});

/** 管理员登出 */
function logout() {
	location.href = "/logoutWeb?token=" + localStorage.getItem(theToken);
}
