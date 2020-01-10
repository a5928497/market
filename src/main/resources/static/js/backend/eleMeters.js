$(function() {
    $changeBTN = $(".changeBTN");
    $tenant_search =$("#tenant_search");
    $degree_upload = $("#degree_upload");
    $cancelBTN = $(".cancelBTN");
    $uploadBTN = $(".uploadBTN");
    $('.chosen-select').chosen({width: "100%"});
    $form = $("form")

    //点击取消隐藏所有弹框
    $cancelBTN.click(function () {
        $tenant_search.hide();
        $degree_upload.hide();
        // 清空value
        $("form").children("input[name='id']").removeAttr("value");
        return false;
    })

    //点击弹出更改租户弹框
    $changeBTN.click(function () {
        $tenant_search.show();
        $("#tenant_search form").children("input[name='id']").val($(this).attr("meterId"));
        return false;
    });

    $uploadBTN.click(function () {
       $degree_upload.show();
        $("#degree_upload form").children("input[name='id']").val($(this).attr("meterId"));
       return false;
    });
})