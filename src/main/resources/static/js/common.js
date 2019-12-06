/*******************************************************************************
 * 全局变量token的key
 */
var theToken = 'token';

/*******************************************************************************
 * 返回类型 success:0; error:1;
 */
var SUCCESS_CODE = 0;
var ERROR_CODE = 1;

$(function() {

    /***************************************************************************
	 * 菜单跳转
	 */
    $(".main_left a").on("click",
    function() {
        var address = $(this).attr("data-src");
        console.log(address) $("iframe").attr("src", address);
    });

});

/*******************************************************************************
 * 管理员登出
 */
function logout() {
    location.href = "/logoutWeb?token=" + localStorage.getItem(theToken);
}


/*******************************************************************************
 * Custom Utils
 */
var myStringUtils = {

    /***************************************************************************
	 * 判断字符串是否为空
	 */
    isEmpty: function(str) {
        if (str  ==  ""  ||  str  ==  undefined  ||  str  ==  null) {
            return true;
        }
        return false;
    },
    
    /***************************************************************************
	 * 判断字符串是否为空，或者是否为空格
	 */
    inputCheckEmpty(str) { 
        if (str  ==  ""  ||  str  ==  undefined  ||  str  ==  null ||   (str.length > 0  &&  str.trim().length  ==  0)) {
            return true;
        }
        return false;
    }

}