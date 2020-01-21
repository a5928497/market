$(function () {
    $pure_income = $(".is_pure_income");
    $fees = $(".fees");

    //选择是否春手足
    $pure_income.change(function () {
        var is_pure = $("input[name='is_pure_income']:checked").val();
        if ("1" == is_pure) {
            //若选择不是纯收租金
            $fees.hide();
        } else {
            //若选择纯收租金
            $fees.show();
        }
        $fees.find("input").each(function () {
            //若有空置则置0
            if ("" == $(this).val()) {
                $(this).val(0);
            }
        });
    });

    //返回函数
    $backBTN = $(".backBTN");
    $backBTN.click(function () {
        var uri = $backBTN.attr("back_uri");
        window.location.replace(uri);
        return false;
    })
})