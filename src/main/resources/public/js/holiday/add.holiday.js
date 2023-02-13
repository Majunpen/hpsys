layui.use(['form', 'layer','laydate','upload'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        laydate = layui.laydate,
        upload = layui.upload
        $ = layui.jquery;



    laydate.render({
        elem: '#time',
        type: "date",
        range: true
    });


    /**
     * 请假类别信息
     */
    $.ajax({
        type:"post",
        url:ctx+"/holidayType/list",
        success:function (data){
            if (data!== null) {
                $.each(data, function(index, item) {
                    $("#holidayType").append("<option value='"+item.id+"' >"+item.holidayType+"</option>");
                });
            }
            //重新渲染
            form.render("select")
        }
    })





    form.on("submit(addDraft)", function (data) {
        index = top.layer.msg('数据提交中，请稍候', {icon: 16, time: false, shade: 0.8});
        //弹出loading
        var url=ctx + "/holiday/saveDraft";
        $.post(url, data.field, function (res) {
            if (res.code == 200) {
                setTimeout(function () {
                    top.layer.close(index);
                    top.layer.msg("操作成功！");
                    layer.closeAll("iframe");
                    //刷新父页面
                    parent.location.reload();
                }, 500);
            } else {
                layer.msg(
                    res.message, {
                        icon: 5
                    }
                );
            }
        });
        return false;
    });










    form.on("submit(addHoliday)", function (data) {
         index = top.layer.msg('数据提交中，请稍候', {icon: 16, time: false, shade: 0.8});
        //弹出loading
        var url=ctx + "/holidayApply/save";
        $.post(url, data.field, function (res) {
            if (res.code == 200) {
                setTimeout(function () {
                    top.layer.close(index);
                    top.layer.msg("操作成功！");
                    layer.closeAll("iframe");
                    //刷新父页面
                    parent.location.reload();
                }, 500);
            } else {
                layer.msg(
                        res.message, {
                            icon: 5
                        }
                    );
            }
        });
        return false;
    });

    $("#closeDlg").click(function (){
        // iframe 页面关闭 添加parent
        parent.layer.closeAll();
    })



});