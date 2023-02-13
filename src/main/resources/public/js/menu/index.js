layui.use(['table', 'treetable'], function () {
    var $ = layui.jquery,
        table = layui.table,
    treeTable = layui.treetable;

    // 渲染表格
    treeTable.render({
        treeColIndex: 1,
        treeSpid: 0,
        treeIdName: 'id',
        treePidName: 'parentId',
        elem: '#menu-table',
        url: ctx + '/menu/list',
        toolbar: "#toolbarDemo",
        treeDefaultClose: true,
        treeLinkage: false,
        cols: [[
            {type: 'numbers'},
            {field: 'menuName', width: 260, title: '菜单名称'},
            {
                field: 'grade', width: 80, align: 'center', templet: function (d) {
                    if (d.grade == 0) {
                        return '<span class="layui-badge layui-bg-blue">目录</span>';
                    }
                    if(d.grade==1){
                        return '<span class="layui-badge-rim">菜单</span>';
                    }
                    if (d.grade == 2) {
                        return '<span class="layui-badge layui-bg-gray">按钮</span>';
                    }
                }, title: '类型'
            },
            {field: 'optValue', width: 260, title: '权限标识'},
            {field: 'parentName', width: 100, title: '上级菜单'},
            {field: 'createDate', width: 150, title: '创建时间'},
            {field: 'updateDate', width: 150, title: '更新时间'},
            {templet: '#menu-oper', align: 'center', title: '操作'}
        ]],
        done: function () {
            layer.closeAll('loading');
        }
    });


    //监听工具条
    table.on('tool(menu-table)', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;
        if (layEvent === 'add') {
            openAddMenuDialog(parseInt(data.grade) + 1, data.id);
        } else if (layEvent === 'edit') {
            // 记录修改
            openUpdateMenuDialog(data.id);
        } else if (layEvent === 'del') {
            layer.confirm('确定删除当前菜单？', {icon: 3, title: "菜单管理"}, function (index) {
                $.post(ctx + "/menu/delete", {id: data.id}, function (data) {
                    if (data.code == 200) {
                        layer.msg("操作成功！");
                        window.location.reload();
                    } else {
                        layer.msg(data.message, {icon: 5});
                    }
                });
            })
        }
    });

    table.on('toolbar(menu-table)', function (obj) {
        switch (obj.event) {
            case "expand":
                treeTable.expandAll('#menu-table');
                break;
            case "fold":
                treeTable.foldAll('#menu-table');
                break;
            case "add":
                openAddMenuDialog(-1, 0);
                break;
        }
        ;
    });


    function openAddMenuDialog(grade, parentId) {
        if(grade==3){
            layer.msg("暂不支持添加四级菜单!", {icon: 5});
            return;
        }
        var url = ctx + "/menu/addMenuPage?grade=" + grade + "&parentId=" + parentId;
        var title = "菜单管理-添加菜单";
        layui.layer.open({
            title: title,
            type: 2,
            area: ["800px", "550px"],
            maxmin: true,
            content: url
        });
    }

    function openUpdateMenuDialog(id) {
        var url = ctx + "/menu/updateMenuPage?id="+id;
        var title = "菜单管理-更新菜单";
        layui.layer.open({
            title: title,
            type: 2,
            area: ["700px", "450px"],
            maxmin: true,
            content: url
        });
    }


})