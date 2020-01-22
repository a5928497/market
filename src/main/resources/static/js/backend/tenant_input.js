$(function () {
    $pure_income = $(".is_pure_income");
    $fees = $(".fees");
    $payment_date = $("#next_payment_date");
    $rent_style = $("#rent_style");
    $rent_cycle = $("#rent_cycle");
    $submitBTN = $(".submitBTN");

    //初始submit不可用
    $submitBTN.attr("disabled","disabled");

    //检查必填项是否都已填写，若是使保存按钮可用
    $("input,select").change(function () {
        var result = not_null($(".not_null"));
        if (result == true) {
            $submitBTN.removeAttr("disabled");
        }
        else {
            $submitBTN.attr("disabled","disabled");
        }
    });
    //租赁方式改变
    $rent_style.change(function () {
        $rent_cycle.empty();
        console.log($(this).val())
        switch ($(this).val()){
            //月缴
            case "1":
                for (var i =1;i<=6;i++) {
                    $rent_cycle.append("<option value='" + i + "'>"+ i +"</option>");
                }
                break;
            case "2":
                $rent_cycle.append("<option value='1'>1</option>");
                break;
            default:
                for (var i =1;i<=20;i++) {
                    $rent_cycle.append("<option value='" + i + "'>"+ i +"</option>");
                }
        }
    });

    //缴款日期输入验证
    $payment_date.blur(function () {
        var date = $(this).val();
        if ("" == date) {
            $(this).val(getFormattDate());
        }
        else if (legalCheck(date) == false) {
            $(this).val("");
            $(this).parent().append("<p style='color: red'>输入不能为空或有误请重新输入！</p>")
        }

    });
    $payment_date.focus(function () {
        $(this).siblings("p").remove();
    });

    //选择是否纯收租
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

    function getFormattDate() {
        var date = new Date;
        return date.getFullYear() + "-" + (date.getMonth()+1) + "-" + date.getDate();
    }

    //true合法 false输入不合法
    function legalCheck(string) {
        //日期正则表达式
        var patten = "^\\d{4}-\\d{1,2}-\\d{1,2}";
        var result = string.match(patten);
        return !(null == result);
    }

    function not_null($object) {
        var resule = true;
        $object.each(function () {
            console.log($(this).val())
            if ($(this).val() == "") {
                resule = false;
            }
        });
        return resule;
    }
})