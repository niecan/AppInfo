
var saleSwitchAjax = function (appId, obj) {
    $.ajax({
        type: "PUT",
        url: appId + "/sale.json",
        dataType: "json",
        success: function (data) {
            /*
             * resultMsg:success/failed
             * errorCode:exception000001
             * appId:appId
             * errorCode:param000001
             */
            if (data.errorCode === '0') {
                if (data.resultMsg === "success") {//操作成功
                    if ("open" === obj.attr("saleSwitch")) {
                        //alert("恭喜您，【"+obj.attr("appsoftwarename")+"】的【上架】操作成功");
                        $("#appInfoStatus" + obj.attr("appinfoid")).html("已上架");
                        obj.className = "saleSwichClose";
                        obj.html("下架");
                        obj.attr("saleSwitch", "close");
                        $("#appInfoStatus" + obj.attr("appinfoid")).css({
                            'background': 'green',
                            'color': '#fff',
                            'padding': '3px',
                            'border-radius': '3px'
                        });
                        $("#appInfoStatus" + obj.attr("appinfoid")).hide();
                        $("#appInfoStatus" + obj.attr("appinfoid")).slideDown(300);
                    } else if ("close" === obj.attr("saleSwitch")) {
                        //alert("恭喜您，【"+obj.attr("appsoftwarename")+"】的【下架】操作成功");
                        $("#appInfoStatus" + obj.attr("appinfoid")).html("已下架");
                        obj.className = "saleSwichOpem";
                        obj.html("上架");
                        obj.attr("saleSwitch", "open");
                        $("#appInfoStatus" + obj.attr("appinfoid")).css({
                            'background': 'red',
                            'color': '#fff',
                            'padding': '3px',
                            'border-radius': '3px'
                        });
                        $("#appInfoStatus" + obj.attr("appinfoid")).hide();
                        $("#appInfoStatus" + obj.attr("appinfoid")).slideDown(300);
                    }
                } else if (data.resultMsg === "failed") {//删除失败
                    if ("open" === obj.attr("saleSwitch")) {
                        alert("恭喜您，【" + obj.attr("appsoftwarename") + "】的【上架】操作失败");
                    } else if ("close" === obj.attr("saleSwitch")) {
                        alert("恭喜您，【" + obj.attr("appsoftwarename") + "】的【下架】操作失败");
                    }
                }
            } else {
                if (data.errorCode === 'exception000001') {
                    alert("对不起，系统出现异常，请联系IT管理员");
                } else if (data.errorCode === 'param000001') {
                    alert("对不起，参数出现错误，您可能在进行非法操作");
                }
            }
        },
        error: function (data) {
            if ("open" === obj.attr("saleSwitch")) {
                alert("恭喜您，【" + obj.attr("appsoftwarename") + "】的【上架】操作成功");
            } else if ("close" === obj.attr("saleSwitch")) {
                alert("恭喜您，【" + obj.attr("appsoftwarename") + "】的【下架】操作成功");
            }
        }
    });
};


$(".viewApp").on("click", function () {
    var obj = $(this);
    window.location.href = "appview/" + obj.attr("appinfoid");
});

$(".deleteApp").on("click", function () {
    var obj = $(this);
    if (confirm("你确定要删除APP应用【" + obj.attr("appsoftwarename") + "】及其所有的版本吗？")) {
        $.ajax({
            type: "GET",
            url: "delapp.json",
            data: {id: obj.attr("appinfoid")},
            dataType: "json",
            success: function (data) {
                if (data.delResult == "true") {//删除成功：移除删除行
                    alert("删除成功");
                    obj.parents("tr").remove();
                } else if (data.delResult == "false") {//删除失败
                    alert("对不起，删除AAP应用【" + obj.attr("appsoftwarename") + "】失败");
                } else if (data.delResult == "notexist") {
                    alert("对不起，APP应用【" + obj.attr("appsoftwarename") + "】不存在");
                }
            },
            error: function (data) {
                alert("对不起，删除失败");
            }
        });
    }
});

	
