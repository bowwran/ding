layui.use(['form', 'element','layedit'], function() {
    var form = layui.form,
        layer = layui.layer,
        element = layui.element;
    var layedit = layui.layedit
        ,$ = layui.jquery;

    //构建一个默认的编辑器
    var index = layedit.build('LAY_demo1');


    $('.site-demo-layedit').on('click', function(){
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

});
