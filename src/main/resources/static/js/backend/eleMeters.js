$(function() {
    $changeBTN = $(".changeBTN");
    $tenant_search =$("#tenant_search");

    $('.chosen-select').chosen({width: "100%"});

    //点击弹出弹框
    $changeBTN.click(function () {
        $tenant_search.show();
    });
})