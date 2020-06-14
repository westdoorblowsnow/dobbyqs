var uploadpath = "";
var uploaddata = "";
var updataloadreback = {};
function upload() {
    $.post(uploadpath,uploaddata,function (data) {
        if (data.status==1) updataloadreback = data.data;
        else alert(data.info);
    });
}