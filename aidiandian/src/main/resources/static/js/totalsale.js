layui.config({
    version: '1605453739808' //为了更新 js 缓存，可忽略
});

layui.use(['laydate', 'laypage', 'layer', 'table', 'carousel', 'upload', 'element', 'slider'], function(){
    var layer = layui.layer //弹层
        ,table = layui.table //表格
        ,element = layui.element //元素操作


    //执行一个 table 实例
    table.render({
        elem: '#demo'
        ,height: 600
        ,url: '../../static/json/totalsale.json' //数据接口
        ,title: '用户表'
        ,page: true //开启分页
        ,toolbar: '#toolbar1' //开启工具栏，此处显示默认图标，可以自定义模板，详见文档
        // ,totalRow: true //开启合计行
        ,cellMinWidth: 200
        ,cols: [[ //表头
            {field: 'typename', title: '菜品名称', fixed: 'left'}
            ,{field: 'bookcount', title: '订购数量'}
            ,{field: 'unitprice', title: '单价（元）'}
            ,{field: 'total', title: '合计（元）', totalRow: true}

        ]]
    });

    //监听头工具栏事件
    table.on('toolbar(test)', function(obj){
        var checkStatus = table.checkStatus(obj.config.id)
            ,data = checkStatus.data; //获取选中的数据
        switch(obj.event){
            case 'add':
                layer.msg('添加');
                break;
            case 'update':
                if(data.length === 0){
                    layer.msg('请选择一行');
                } else if(data.length > 1){
                    layer.msg('只能同时编辑一个');
                } else {
                    layer.alert('编辑 [id]：'+ checkStatus.data[0].id);
                }
                break;
            case 'delete':
                if(data.length === 0){
                    layer.msg('请选择一行');
                } else {
                    layer.msg('删除');
                }
                break;
        };
    });

    //监听行工具事件
    table.on('tool(test)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
        var data = obj.data //获得当前行数据
            ,layEvent = obj.event; //获得 lay-event 对应的值
        if(layEvent === 'detail'){
            layer.msg('查看操作');
        } else if(layEvent === 'del'){
            layer.confirm('真的删除行么', function(index){
                obj.del(); //删除对应行（tr）的DOM结构
                layer.close(index);
                //向服务端发送删除指令
            });
        } else if(layEvent === 'edit'){
            layer.msg('编辑操作');
        }
    });

    //底部信息
    var footerTpl = lay('#footer')[0].innerHTML;
    lay('#footer').html(layui.laytpl(footerTpl).render({}))
        .removeClass('layui-hide');
});