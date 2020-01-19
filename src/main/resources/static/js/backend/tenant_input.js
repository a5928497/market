$(function () {
    $pure_income = $(".is_pure_income");

    $pure_income.change(function () {
        var is_pure = $("input[name='is_pure_income']:checked").val();
       if ("0" == is_pure) {
           //若选择
       }else {

       }
    });

    //返回函数
    $backBTN = $(".backBTN");
    $backBTN.click(function () {
        var uri = $backBTN.attr("back_uri");
        window.location.replace(uri);
        return false;
    })
})