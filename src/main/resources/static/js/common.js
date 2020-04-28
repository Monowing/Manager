/*******************************************************************************
 * 全局变量token的key
 */
var theToken = 'token';

/*******************************************************************************
 * 返回类型 success:0; error:1;
 */
var SUCCESS_CODE = 0;
var ERROR_CODE = 1;

$(function () {

    /***************************************************************************
     * 菜单跳转
     */
    $(".main_left a").on("click",
        function () {
            var address = $(this).attr("data-src");
            $("iframe").attr("src", address);
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
    isEmpty: function (str) {
        if (str == "" || str == undefined || str == null) {
            return true;
        }
        return false;
    },

    /***************************************************************************
     * 判断字符串是否为空，或者是否为空格
     */
    isBlankOrEmpty(str) {
        if (str == "" || str == undefined || str == null || (str.length > 0 && str.trim().length == 0)) {
            return true;
        }
        return false;
    }

}

var myCommonUtils = {

    /***************************************************************************
     * 手机号验证
     */
    phoneCheck: function (value) {
        var temp = /^(1[3-9])[0-9]{9}$/;
        return temp.test(value);
    },

    /***************************************************************************
     * 邮箱验证
     */
    emailCheck: function (value) {
        var temp = /^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
        return temp.test(value);
    }


}
