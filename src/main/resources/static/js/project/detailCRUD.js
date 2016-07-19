/**
 * Created by WPJ587 on 2016/7/8.
 */
/**
 * 选择订单
 */
function selectOrder() {
    layer.open({
        type: 2,
        area: ['95%', '95%'],
        fix: false, //不固定
        maxmin: true,
        content: '/admin/customer?isFrame=1'
    });
}
/**
 * 选择阿姨的
 */
function selectEmp() {
    layer.open({
        type: 2,
        area: ['90%', '90%'],
        fix: false, //不固定
        maxmin: true,
        content: '/admin/emp/select'
    });
}


$('#serviceOther').on('keyup', function () {
    var txtval = $('#serviceOther').val().length;
    var str = parseInt(100 - txtval);
    if (str > 0) {
        $('#num_txt2').html('剩余可输入' + str + '字');
    } else {
        $('#num_txt2').html("<span style=color>剩余可输入0字");
        $('#serviceOther').val($('#serviceOther').val().substring(0, 100)); //这里意思是当里面的文字小于等于0的时候，那么字数不能再增加，只能是600个字
    }
});
$('#serviceCallback').on('keyup', function () {
    var txtval = $('#serviceCallback').val().length;
    var str = parseInt(100 - txtval);
    if (str > 0) {
        $('#num_txt1').html('剩余可输入' + str + '字');
    } else {
        $('#num_txt1').html("<span style=color>剩余可输入0字");
        $('#serviceCallback').val($('#serviceCallback').val().substring(0, 100)); //这里意思是当里面的文字小于等于0的时候，那么字数不能再增加，只能是600个字
    }
});
function haoTime() {
    var start = new Date($("#serviceTime").val());
    var end = new Date($("#serviceEtime").val());
    if (start != null && end != null) {
        var min = (end - start) / 1000 / 60 % 60;// 分钟
        var hours = (end - start) / 1000 / 60 / 60;
        $("#serviceOther").text("时长" + "：" + parseInt(hours) + "时" + min + "分钟");
        layer.tips("共：" + parseInt(hours) + "时" + min + "分钟", '#labelEndTime', {
            tips: [2, '#3595CC'],
            time: 190000
        });

    }

}