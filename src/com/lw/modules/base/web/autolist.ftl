${config_iframe}
<script type="text/javascript">
/**
*表单的高度,表单的宽度
**/
var ${config_id}Fw = 700,${config_id}Fh = 400;

$(function(){
	$.get("cgFormHeadController.do?checkIsExit&checkIsTableCreate&name=${config_id}",
	function(data){
		data = $.parseJSON(data);
		if(data.success){
			createDataGrid${config_id}();
		}else{
			alertTip('表:<span style="color:red;">${config_id}</span>还没有生成,请到表单配置生成表');
		}
	});
});

function createDataGrid${config_id}(){
	var initUrl = 'cgAutoListController.do?datagrid&configId=${config_id}&field=${fileds}${initquery}';
	initUrl = encodeURI(initUrl);
	$('#${config_id}List').datagrid(
		{
			url:initUrl,
			idField: 'id', 
			title: '${config_name}',
			fit:true,
			fitColumns:true,
			striped:true,
			autoRowHeight:true,
			pageSize: 10,
			<#if config_ispagination =="Y">pagination:true,</#if>
				singleSelect:true,
			<#if fileds?index_of("create_datetime")!=-1 >
				sortName:'create_datetime',
			<#elseif fileds?index_of("create_date")!=-1 && fileds?index_of("create_datetime")==-1>
				sortName:'create_date',
			<#else>
				sortName:'id',
			</#if>
			pageList:[10,30,50,100],
			sortOrder:'desc',
			rownumbers:true,
			showFooter:true,
			frozenColumns:[[]],
			columns:[
				[	
							<#list config_fieldList  as x>  
								 {	field:'${x['field_id']}',
								 	title:'${x['field_title']}',
								 	<#if x['field_isShow'] == "N" >hidden:true,
								 	</#if>
								 	<#if x['field_href'] != "">
								 	formatter:function(value,rec,index){
								 		var href='';
								 		href+=applyHref('字段链接','${x['field_href']}',value,rec,index);
								 		return href;
								 	},
								 	</#if>
								 	sortable:true,
								 	width:${x['field_length']}
								 },
							</#list>
				]
			],
			onLoadSuccess:function(data){
				$("#${config_id}List").datagrid("clearSelections");
			},
			onClickRow:function(rowIndex,rowData)
				{rowid=rowData.id;gridname='${config_id}List';}
			});
	$('#${config_id}List').datagrid('getPager').pagination({beforePageText:'',afterPageText:'/{pages}',displayMsg:'{from}-{to}共{total}条',showPageList:true,showRefresh:true});
	$('#${config_id}List').datagrid('getPager').pagination({onBeforeRefresh:function(pageNumber, pageSize){ $(this).pagination('loading');$(this).pagination('loaded'); }});

	}
	//列表刷新
	function reloadTable(){	
		try{
			$('#'+gridname).datagrid('reload');	
		}catch(ex){
			//donothing
		}
	}
	//列表刷新-推荐使用
	function reload${config_id}List(){
		$('#${config_id}List').datagrid('reload');
	}
	/**
	 * 获取列表中选中行的数据-推荐使用
	 * @param field 数据中字段名
	 * @return 选中行的给定字段值
	 */
	function get${config_id}ListSelected(field){
		var row = $('#${config_id}List').datagrid('getSelected');
		if(row!=null){value= row[field];
		}else{
			value='';
		}
		return value;
	}
	/**
	 * 获取列表中选中行的数据
	 * @param field 数据中字段名
	 * @return 选中行的给定字段值
	 */
	function getSelected(field){
		var row = $('#'+gridname).datagrid('getSelected');
		if(row!=null){value= row[field];
		}else{
			value='';
		}
		return value;
	}
	/**
	 * 获取列表中选中行的数据（多行）
	 * @param field 数据中字段名-不传此参数则获取全部数据
	 * @return 选中行的给定字段值，以逗号分隔
	 */
	function get${config_id}ListSelections(field){
		var ids = '';
		var rows = $('#${config_id}List').datagrid('getSelections');
		for(var i=0;i<rows.length;i++){
			ids+=rows[i][field];
			ids+=',';
		}
		ids = ids.substring(0,ids.length-1);
		return ids;
	}
	/**
	 * 列表查询
	 */
	function ${config_id}Listsearch(){
		var queryParams=$('#${config_id}List').datagrid('options').queryParams;
		$('#${config_id}Listtb').find('*').each(
			function(){
			queryParams[$(this).attr('name')]=$(this).val();});
			$('#${config_id}List').datagrid({url:'cgAutoListController.do?datagrid&configId=${config_id}&field=${fileds}',pageNumber:1});
	}
	function dosearch(params){
		var jsonparams=$.parseJSON(params);
		$('#${config_id}List').datagrid({url:'cgAutoListController.do?datagrid&configId=${config_id}&field=${fileds},',queryParams:jsonparams});
	}
	function ${config_id}Listsearchbox(value,name){
		var queryParams=$('#${config_id}List').datagrid('options').queryParams;
		queryParams[name]=value;
		queryParams.searchfield=name;
		$('#${config_id}List').datagrid('reload');
	}
	$('#${config_id}Listsearchbox').searchbox({
		searcher:function(value,name){
			${config_id}Listsearchbox(value,name);
		},
		menu:'#${config_id}Listmm',
		prompt:'请输入查询关键字'
	});
	//查询重置
	function ${config_id}searchReset(name){ 
		$("#"+name+"tb").find("input[type!='hidden']").val("");
		${config_id}Listsearch();
	}
	//将字段href中的变量替换掉
	function applyHref(tabname,href,value,rec,index){
		//addOneTab(tabname,href);
		var hrefnew = href;
		var re = "";
		var p1 = /\#\{(\w+)\}/g;
		try{
			var vars =hrefnew.match(p1); 
			for(var i=0;i<vars.length;i++){
				var keyt = vars[i];
				var p2 = /\#\{(\w+)\}/g;
				var key = p2.exec(keyt);
				 hrefnew =  hrefnew.replace(keyt,rec[key[1]]);
			}
		}catch(ex){
		}
		re += "<a href = '#' onclick=\"addOneTab('"+tabname+"','"+ hrefnew+"')\" ><u>"+value+"</u></a>";
		return re;
	}
	//SQL增强入口-按钮
	function doBusButton(url,content,gridname){
		var rowData = $('#'+gridname).datagrid('getSelected');
		if (!rowData) {
			tip('请选择一条信息');
			return;
		}	
		url = url + '&id='+rowData.id;
		createdialog('确认 ', '确定'+content+'吗 ?', url,gridname);
	}
	//SQL增强入口-操作列里的链接
	function doBusButtonForLink(url,content,gridname,rowData){
		if (!rowData) {
			tip('请选择一条信息');
			return;
		}	
		url = url + '&id='+rowData;
		createdialog('确认 ', '确定'+content+'吗 ?', url,gridname);
	}
	//新增
	function ${config_id}add(){
		add('${config_name}录入','cgFormBuildController.do?ftlForm&tableName=${config_id}','${config_id}List',${config_id}Fw,${config_id}Fh);
	}
	//修改
	function ${config_id}update(){
		update('${config_name}编辑','cgFormBuildController.do?ftlForm&tableName=${config_id}','${config_id}List',${config_id}Fw,${config_id}Fh);
	}
	//查看
	function ${config_id}view(){
		detail('查看','cgFormBuildController.do?ftlForm&tableName=${config_id}&mode=read','${config_id}List',${config_id}Fw,${config_id}Fh);
	}
	
	//批量删除
	function ${config_id}delBatch(){
		//获取选中的ID串
		var ids = get${config_id}ListSelections('id');
		if(ids.length<=0){
			tip('请选择至少一条信息');
			return;
		}
		$.dialog.confirm('确定删除吗?', function(r) {
			if(!r){return;}
			$.ajax({
			    url:"cgAutoListController.do?delBatch",
			    data:{'ids':ids,'configId':'${config_id}'},
				type:"Post",
			    dataType:"json",
			    success:function(data){
					tip(data.msg);
					reload${config_id}List();
			    },
				error:function(data){
					$.messager.alert('错误',data.msg);
				}
			});
			}
		);
	}

	function ${config_id}ExportExcel(){
		var queryParams = $('#${config_id}List').datagrid('options').queryParams;
		$('#${config_id}Listtb').find('*').each(function() {
		    queryParams[$(this).attr('name')] = $(this).val();
		});
		var params = '&';
		$.each(queryParams, function(key, val){
			params+='&'+key+'='+val;
		}); 
		var fields = '&field=';
		$.each($('#${config_id}List').datagrid('options').columns[0], function(i, val){
			if(val.field != 'opt'&&val.field != 'ck'){
				fields+=val.field+',';
			}
		}); 
		window.location.href = "excelTempletController.do?exportXls&tableName=${config_id}"+encodeURI(params+fields)
	}
</script>
<table width="100%"   id="${config_id}List" toolbar="#${config_id}Listtb"></table>
<div id="${config_id}Listtb" style="padding:3px; height: auto">
	<div style="height:30px;" class="datagrid-toolbar">
	<span style="float:left;" >
	
	<a  id="add" href="#"  class="easyui-linkbutton" plain="true"  icon="icon-add" onclick="${config_id}add()">录入</a>
	<a  id="update" href="#"  class="easyui-linkbutton" plain="true"  icon="icon-edit" onclick="${config_id}update()">编辑</a>
	<a id="delete" href="#" class="easyui-linkbutton" plain="true"  icon="icon-remove" onclick="${config_id}delBatch()">批量删除</a>
	<a id="detail" href="#" class="easyui-linkbutton" plain="true"  icon="icon-search" onclick="${config_id}view()">查看</a>
	<a id="import" href="#"  class="easyui-linkbutton" plain="true"  icon="icon-put" onclick="add('${config_name}Excel数据导入','excelTempletController.do?goImplXls&tableName=${config_id}','${config_id}List')">Excel数据导入</a>
	<a id="excel" href="#" class="easyui-linkbutton" plain="true" onclick="${config_id}ExportExcel()"  icon="icon-putout">Excel导出</a>
	
	</span>
	
	</div>
</div>
