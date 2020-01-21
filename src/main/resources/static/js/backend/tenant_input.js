$(function () {
    $pure_income = $(".is_pure_income");
    $fees = $(".fees");
    $pure_income.change(function () {
        var is_pure = $("input[name='is_pure_income']:checked").val();
        if ("0" == is_pure) {
            //若选择不是纯收租金
            $fees.children("input").val(0);
            $fees.hide();
        } else {
            //若选择纯收租金
            $fees.show();
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