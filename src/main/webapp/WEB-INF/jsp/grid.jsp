<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>列表</title>
    <!-- zui -->
    <link href="zui/css/zui.min.css" rel="stylesheet">
    <link href="zui/lib/datagrid/zui.datagrid.min.css" rel="stylesheet">
    <link href="css/public.css" type="text/css" rel="stylesheet">
</head>
<body>

<div class="container">

    <div class="row" style="height: auto;margin-top: 100px">
        <div class="col-md-4 col-md-offset-4" >
            <form>
                <div class="form-group input-control has-icon-left">
                    <input id="user" type="text" class="form-control" placeholder="账    号">
                    <label for="user" class="input-control-icon-left"><i class="icon icon-user "></i></label>
                </div>
                <div class="form-group input-control has-icon-left">
                    <input id="pswd" type="text" class="form-control" placeholder="密    码">
                    <label for="pswd" class="input-control-icon-left"><i class="icon icon-key "></i></label>
                </div>

                <button type="submit" class="btn btn-primary">提交</button>
            </form>
        </div>
    </div>

    <div class="row" style="height: auto">
        <div id="myDataGrid" class="datagrid">
            <div class="datagrid-container"></div>
            <div id="myPager" class="pager" data-elements="size_menu,first,prev,goto,next,last,page_of_total_text" data-page-Size-Options="1,2,5,10,15,20" style="margin-left: 20%"></div>
        </div>
    </div>
</div>

<script src="jquery/jquery-3.2.1.min.js"></script>
<!-- ZUI Javascript组件 -->
<script src="zui/js/zui.min.js"></script>
<script src="zui/lib/datagrid/zui.datagrid.min.js"></script>
<script type="text/javascript">
    $(function(){
        $('#myDataGrid').datagrid({
            dataSource: {
                cols:[
                    {name: 'name', label: '名字', width: 0.2},
                    {name: 'address', label: '地址', width: 0.2},
                    {name: 'fee', label: '金额', width: 0.2},
                    {name: 'dates', label: '日期',width: 0.2},
                    {name: 'operate', label: '操作', width: 0.2}
                ],
                remote: function(params) {
                    return {
                        // 请求地址
                        url: '/demo/list',
                        // 请求类型
                        type: 'GET',
                        // 数据类型
                        dataType: 'json'
                    };
                }
            },
            states: {
                pager: {page: 1,recPerPage: 3}
            },
            configs: {
                C0: {},
                C5: {html:true,className:'text-center'}
            },
            checkable: false,
            checkByClickRow: false,
            showRowIndex: true

            // ... 其他初始化选项
        });

        $('#myPager').on('onPageChange', function(e, state, oldState) {
            if (state.page !== oldState.page) {
                console.log('页码从', oldState.page, '变更为', state.page);
                console.log(state.recPerPage);
            }

            // 获取分页器实例对象
            var myPager = $('#myPager').data('zui.pager');
            //myPager.render();
            // 设置当前页码为 4，并同时设置记录总数为 100， 每页记录数目为 30
            /*myPager.set({
                recTotal: 1000
            });*/
        });

    })

</script>

</body>
</html>