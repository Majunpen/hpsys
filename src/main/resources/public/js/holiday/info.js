layui.use(['form', 'layer','laydate','upload'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        laydate = layui.laydate,
        upload = layui.upload
        $ = layui.jquery;


    /**
     * 请假类别信息
     */
    $.ajax({
        type:"post",
        url:ctx+"/holidayType/list",
        success:function (data){
            if (data!== null) {
                $.each(data, function(index, item) {
                    if($("#ht").val()==item.id){
                        $("#holidayType").append("<option value='"+item.id+"' selected='selected'>"+item.holidayType+"</option>");
                    }else{
                        $("#holidayType").append("<option value='"+item.id+"' >"+item.holidayType+"</option>");
                    }
                });
            }
            //重新渲染
            form.render("select")
        }
    })





});