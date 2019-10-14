

/*页面刷新跳转
*/
function reloadUrl() {
    window.location.href = location.href;
}

/*页面刷新跳转
 *id_field 参数id
 *id_field_value 参数值
*/
function jumpUrl(url,id_field,id_field_value) {
    if(id_field==null||id_field_value==null)
    {
        window.location.href=""+url+"?"+id_field+"="+id_field_value;
        return false;
    }else{
        window.location.href=""+url+"";
        return false;
    }
}

/*获取图片信息
 *picurl 图片路径
 *title 标题
 *width int 宽度百分比
 *height int 高度百分比
*/
function getPicur(picurl,title,width,height) {
    if (width == null || width == '') {
        width=50;
    };
    if (height == null || height == '') {
        height=50;
    };
    if (title == null || title == '') {
        title='图片';
    };
    layer.open({
        type: 1,
        title: ''+title+'',
        shadeClose: true,
        shade: false,
        skin: 'layui-layer-rim', //加上边框
        area: [''+width+'%', ''+height+'%'], //宽高
        content: "<img src='' id='getPicurId' border='0' style='width:auto; height:auto;display:block;margin:auto;' />"
    });
    $("#getPicurId").attr('src', picurl);
}

/*
 * 提示方法
 * @param string msg 信息
 * @param id string ID
 * @param posi int 位置  上1 右2 下3 左4
*/
function pushTips(msg,id,posi) {
    layer.tips(''+msg+'', '#'+id+'', {
        tips: [posi, '#3595CC'],
        time: 5000
    });
}

/*
 * 错误调试方法
 * @param string msg 错误信息
 * @param type string 样式
 * @param is_open int 开关
*/
function debug(msg,type,is_open) {
    if(is_open==1){
        if(type==0||type==null){
            alert(msg);
        }else{
            layer.msg(msg, {
                time: 20000, //20s后自动关闭
                btn: ['关闭窗口']
            });
        }
    }
}

/*
 * 删除表格行
 * @param string data 数值
 * @param id string 前缀
*/
function deleteRows(data, id) { //(数据id,返回id标识)
    $("#" + id + data).remove();
}

//修改开关
function switchOneField(title,field_value,back_title,field_name,table_name,id_field,id_value,id) {
    var title = encodeURIComponent(title);
    var back_title = encodeURIComponent(back_title);
    var field_value = encodeURIComponent(field_value);
    var field_name = encodeURIComponent(field_name);
    var table_name = encodeURIComponent(table_name);
    var id_field = encodeURIComponent(id_field);
    var id_value = encodeURIComponent(id_value);
    $.ajax({
        type: "POST",
        url: "conf/operation/front.switchOneFieldOperation.php?title=" + title + "&back_title=" + back_title + "&field_value=" + field_value + "&field_name=" + field_name + "&table_name=" + table_name + "&id_field=" + id_field + "&id_value=" + id_value+ "&id=" + id + "",
        dataType: "html",
        success: function(msg) {
            $("#"+id+id_value).html(msg);
        }
    });

}

//修改单字段
function updateOneField(field_value,field_name,table_name,id_field,id_value,id,w_size) {
    var field_value = encodeURIComponent(field_value);
    var field_name = encodeURIComponent(field_name);
    var table_name = encodeURIComponent(table_name);
    var id_field = encodeURIComponent(id_field);
    var id_value = encodeURIComponent(id_value);
    $.ajax({
        type: "POST",
        url: "conf/operation/front.updateOneFieldOperation.php?field_value=" + field_value + "&field_name=" + field_name + "&table_name=" + table_name + "&id_field=" + id_field + "&id_value=" + id_value+ "&id=" + id + "&w_size=" + w_size + "",
        dataType: "html",
        success: function(msg) {
            $("#"+id+id_value).html(msg);
        }
    });

}

/*  弹出层
	title	标题
	url		请求的url
	width		弹出层宽度（缺省调默认值）
	height		弹出层高度（缺省调默认值）
	type - 基本层类型 0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
	fixed - 即鼠标滚动时 层是否固定在可视区域
	maxmin - 最大最小化
	shadeClose - 是否点击遮罩关闭
	anim - 弹出动画
	isOutAnim - 关闭动画
	offset - 坐标
	shade - 遮罩
	id		需要操作的数据id
*/
function showLayerWindow(title,url,width,height,type,fix,maxmin,anim,isOutAnim,offset,shadeClose,shade,id){
    if (title == null || title == '') {
        title=false;
    };
    if (url == null || url == '') {
        url="404.html";
    };
    if (width == null || width == '') {
        width=90;
    };
    if (height == null || height == '') {
        height=($(window).height() - 50);
    };
    layer.open({
        id: 'showLayerWindow'+id,
        type: type,
        area: [width+'%', height +'%'],
        offset: ''+offset+'%',
        anim:anim,
        isOutAnim:isOutAnim,
        fix: fix, //不固定
        maxmin: maxmin,
        shadeClose: shadeClose,
        shade:shade,
        title: title,
        content: url
    });
}

function closeAll(){
    layer.closeAll();
}

function reloadData(){
    layer.closeAll();
    window.location.href = location.href;
}
function getAjax(url, data, callBack) {
    $.ajax({
        type: "get",
        url: url,
        data: data,
        dataType: "html",
        success: function (msg) {
            callBack(msg);
        }

    });
}

function postAjax(url, data, callBack) {
    $.ajax({
        type: "post",
        url: url,
        data: data,
        dataType: "html",
        success: function (msg) {
            callBack(msg);
        }

    });
}