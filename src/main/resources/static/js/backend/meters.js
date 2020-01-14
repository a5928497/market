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
        init();
        return false
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
        submit(url, $form);
        init();
        return false;
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
                if ("0" == data.change_status) {
                    alert("更新失败，仪表读数异常，请重新尝试！");
                }
                else if ("1" == data.change_status) {
                    alert("数据更新成功！")
                }
                else
                {
                    alert("有未清缴费用，请清缴后重新尝试更改！");
                }
                $tr = $("tr[meterId=\'" + data.id + "\']");
                $tr.children().eq(0).text(data.name);
                $tr.children().eq(1).text(data.degree);
                $tr.children().eq(2).text(data.update_date);
                if (!$.isEmptyObject(data.tenant)) {
                    $tr.children().eq(3).text(data.tenant.name);
                }
            }
        });
        return false;
    }

    //初始化输入框
    function init() {
        $tenant_search.hide();
        $degree_upload.hide();
        // 清空value
        $("form input").val("");
    }
})