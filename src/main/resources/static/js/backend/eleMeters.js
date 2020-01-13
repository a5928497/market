$(function () {
    $changeBTN = $(".changeBTN");
    $tenant_search = $("#tenant_search");
    $degree_upload = $("#degree_upload");
    $cancelBTN = $(".cancelBTN");
    $uploadBTN = $(".uploadBTN");
    $submitBTN = $(".submitBTN");
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

    $submitBTN.click(function () {
        var $form = $(this).closest("form");
        var url = $form.attr("action");
        submit(url,$form);
    });

    function submit(url, form) {
        $.ajax({
            type: "POST",   //提交的方法
            url: url, //提交的地址
            data: form.serialize(),// 序列化表单值
            async: false,
            error: function (request) {
                alert("连接超时！");
            },
            success: function (data) {
                $tr = $("tr[meterId=\'" + data.id + "\']");
                if (null != data) {
                    $tr.children().eq(0).text(data.name);
                    $tr.children().eq(1).text(data.degree);
                    $tr.children().eq(2).text(data.update_date);
                    if (!$.isEmptyObject(data.tenant)) {
                        $tr.children().eq(3).text(data.tenant.name);
                    }
                } else {
                    console.log("false");
                }
            }
        });
        return false;
    }
})