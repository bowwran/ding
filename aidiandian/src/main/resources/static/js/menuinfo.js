layui.use(['table', 'element', 'layer','form'], function() {
	var table = layui.table,
		element = layui.element,
		layer = layui.layer,
		form = layui.form;
	var $ = layui.$;

	//监听提交
	form.on('submit(formDemo)', function(data){
		layer.msg(JSON.stringify(data.field));
		return false;
	});

	table.render({
		elem: '#test',
		url: '../../static/json/menuinfo.json',
		method:"post",
		// autoSort: false,
		defaultToolbar:['filter'],
		where:{
			field:'applyId',
			order:''
		},
		toolbar: '#toolbarDemo', //开启头部工具栏，并为其绑定左侧模板
		cols: [
			[{
				type: 'checkbox',
				fixed: 'left'
			},
				{
					field: 'Id',
					title: '编号',
					align:'center',
					sort:true
				},
				{
					field: 'Name',
					title: '菜品名称',
					align: "center",
					sort:true
				},
				{
					field: 'Type',
					title: '菜品类型',
					align: "center",
					sort:true
				},
				{
					field: 'Introduction',
					title: '说明',
					align: 'center',
					templet:function (d) {
						return '<p class="layui-layer-own1 alyui-showDetails" aa="'+d.Introduction+'">'+d.Introduction+'</p>';
					}
				},
				{
					field: 'material',
					title: '原料',
					align: "center"
				},
				{
					field:"price",
					title:"市场价格",
					align:"center",
					sort:true
				},
				{
					field:"vipprice",
					title:"会员价格",
					align:"center",
					sort:true
				},
				{
					field:"sellaccount",
					title:"销售数量",
					align:"center",
					sort:true
				},
				{
					field:"remain",
					title:"剩余数量",
					align:"center",
					sort:true
				}
			]
		],
		done: function(res, curr, count) {
			//查看详细信息
			$('.alyui-showDetails').each(function(i) {
				this.onclick = function() {
					imgArrStr = this.getAttribute("aa");
					layer.open({
						content: imgArrStr
					});
				}
			});
		}
	});

	//头工具栏事件
	table.on('toolbar(test)', function(obj) {
		var checkStatus = table.checkStatus(obj.config.id);
		var data = checkStatus.data;

		switch (obj.event) {
			case 'addmenu':
				layer.msg("添加菜品");
				window.location.href='./addnewmenu.html';
				window.location.
					break;
			case 'editmenu':
				layer.msg("编辑菜品");
				window.location.href='';//缺少
				window.location.
					break;
			case 'removemenu':
				layer.msg("删除菜品");
				break;

		};
	});

	// //监听行工具事件
	// table.on('tool(test)', function(obj) {
	// 	var data = obj.data;
	// 	if (obj.event === 'closecourseselection') {
	// 		layer.msg("课程");
	// 	}
	// });

	//sort
	table.on('sort(test)', function(obj) {
		table.reload('test', {
			initSort: obj, //记录初始排序，如果不设的话，将无法标记表头的排序状态。 layui 2.1.1 新增参数
			where: { //请求参数（注意：这里面的参数可任意定义，并非下面固定的格式）
				field: obj.field, //排序字段   在接口作为参数字段  field order
				order: obj.type , //排序方式   在接口作为参数字段  field order
				page:1
			}
		});
	});






});
