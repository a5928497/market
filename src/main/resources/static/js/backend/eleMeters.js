$(function() {
    $changeBTN = $(".changeBTN");
    $tenant_search =$("#tenant_search");
    $cancelBTN = $(".cancelBTN");
    $('.chosen-select').chosen({width: "100%"});
    $form = $("form")

    //点击取消隐藏弹框
    $cancelBTN.click(function () {
        $tenant_search.hide();
        // 清空value
        $("form").children("input[name='id']").removeAttr("value");
        return false;
    })

    //点击弹出弹框
    $changeBTN.click(function () {
        $tenant_search.show();
        $("form").children("input[name='id']").val($(this).attr("meterId"));
    });

})