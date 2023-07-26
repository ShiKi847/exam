// 启动导入题库的对话框
function preUpload(paId) {
    $('#upload').modal();
    $('#btnUp').attr('onclick', 'upload('+paId+')')
}

// 文件上传
function upload(paId) {
    var file = $('#up').val();
    if (file.endsWith('.xls') || file.endsWith('.xlsx')) {
        // 实现ajax的文件上传
        var formData = new FormData();
        var up = document.getElementById('up');
        formData.append('up', up.files[0]); // 相当于以前name是up
        $.ajax({
            url: window.contextPath + "upload/" + paId,
            type: 'post',
            data: formData,
            contentType: false, // 让jQuery帮助我们填写
            processData: false, // 让jQuery帮助我们填写
            success: function (flag) {
                var type = typeof flag; // 正常应该是返回布尔类型，过期不正常是返回其它数据类型(string)
                if (type == 'boolean') {
                    if (flag) {
                        // 成功
                        // 找到匹配的tr
                        var that;
                        $("table tbody tr").each(function () {
                            var idText = $(this).children(':first').text();
                            if (idText == paId) {
                                // 代表找到了
                                that = this;
                            }
                        });
                        $(that).children(":eq(3)").text('绿灯');
                        $(that).children(":eq(5)").text(new Date());
                        $("#tip").text("上传成功！");
                    } else {
                        // 失败
                        $("#tip").text("上传失败！");
                    }
                }
            },
            error: function(xhr, status, error) {
                window.location.href = window.contextPath; // js重定向到首页
            }
        });
        $("#tip").text("导入成功！");
    } else {
        $("#tip").text("请选择正确的excel文件！");
    }
    $('#upload').modal('hide'); // 隐藏
}

// 伪删除试卷
function del(paId) {
    $.ajax({
        type: "GET",
        url: window.contextPath+"del/"+paId,
        success: function(flag){
            var type = typeof flag; // 正常应该是返回布尔类型，过期不正常是返回其它数据类型(string)
            if (type == 'boolean') {
                if (flag) {
                    // 成功
                    // 找到匹配的tr，进行删除
                    var that;
                    $("table tbody tr").each(function () {
                        var idText = $(this).children(':first').text();
                        if (idText == paId) {
                            // 代表找到了
                            that = this;
                        }
                    });
                    $(that).remove();
                    $("#tip").text("删除成功！");
                } else {
                    // 失败
                    $("#tip").text("删除失败！");
                }
            } else {
                // 失败
                window.location.href = window.contextPath; // js重定向到首页
            }

        },
        error: function(xhr, status, error) {
            window.location.href = window.contextPath; // js重定向到首页
        }
    });
}