layui.use(['element', 'layer', 'layuimini','jquery','jquery_cookie'], function () {
    var $ = layui.jquery,
        layer = layui.layer,
        $ = layui.jquery_cookie($);

    // 菜单获取
    $('#layuiminiHomeTabIframe').html('<iframe width="100%" height="100%" frameborder="0"  src="welcome"></iframe>')
    layuimini.initTab();



    //var isMarkMyTasks=false,isMarkMyCandidateTasks=false;
    /**
     * 统计我的代办标记徽章
     */
    /*$.ajax({
        type:"post",
        url:ctx+"/workBench/countMyTasks",
        success:function (data){
            if(data.code==200){
                if(data.obj>0){
                    // 追加徽章
                    $("#myTask").addClass("layui-badge").text(data.obj);
                }else {
                    $("#myTask").removeClass("layui-badge").text();
                }
                // 工作台执行标记
                resolveMark();

            }


        }
    })*/


    /**
     * 统计我的候选记录 标记徽章
     */
    /*$.ajax({
        type:"post",
        url:ctx+"/workBench/countMyCandidateTasks",
        success:function (data){
            if(data.code==200){
                if(data.obj>0){
                    // 追加徽章
                    $("#myCandidateTasks").addClass("layui-badge").text(data.obj);

                }else{
                    $("#myCandidateTasks").removeClass("layui-badge").text();
                }

                // 工作台执行标记
                resolveMark();

            }
        }
    })



    function  resolveMark(){
        $("#workBench").removeClass("layui-badge-dot");
        if($("#myCandidateTasks").hasClass("layui-badge")||$("#myTask").hasClass("layui-badge")){
            $("#workBench").addClass("layui-badge-dot");
        }
    }
*/

});