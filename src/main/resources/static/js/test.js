var cur, paId; // 存储当前题号和试卷id

// 检查试卷
function check() {
    // 获取试卷id和密码
    var paId = $('#paId').val()
    var paPassword = $('#paPassword').val()
    $.ajax({
        url: window.contextPath + "check",
        type: 'get',
        data: {paId: paId, paPassword: paPassword},
        success: function (jsonResult) {
            var type = typeof jsonResult; // 正常应该是返回对象类型，过期不正常是返回其它数据类型(string)
            if (type == 'object') {
                if (jsonResult.status == 200) {
                    $("#tip").text("试卷名：" + jsonResult.data.paName);
                    // 发出查询第一题的ajax
                    queryProblem(paId, 1);
                    window.cur = 1;
                    window.paId = paId;
                    // 显示题目
                    $('#article').removeClass("hidden");
                } else {
                    $("#tip").text("试卷" + jsonResult.message);
                }
            } else {
                window.location.href = window.contextPath; // js重定向到首页
            }
        },
        error: function(xhr, status, error) {
            window.location.href = window.contextPath; // js重定向到首页
        }
    });
}

// 查询题目
function queryProblem(paId, pos) {
    $.ajax({
        url: window.contextPath + "queryProblem",
        type: 'get',
        data: {paId: paId, pos: pos},
        success: function (jsonResult) {
            var type = typeof jsonResult; // 正常应该是返回对象类型，过期不正常是返回其它数据类型(string)
            if (type == 'object') {
                if (jsonResult.status == 201) { // 单选
                    var single = jsonResult.data;
                    var html = `
                        <h3>`+single.sinPos+`. `+single.sinCaption+`</h3>
                        <h4><input type="radio" value="a" name="answer" onchange="change(this)"/> `+single.sinA+`</h4>
                        <h4><input type="radio" value="b" name="answer" onchange="change(this)"/> `+single.sinB+`</h4>
                        <h4><input type="radio" value="c" name="answer" onchange="change(this)"/> `+single.sinC+`</h4>
                        <h4><input type="radio" value="d" name="answer" onchange="change(this)"/> `+single.sinD+`</h4>
                    `;
                    $('#section').empty();
                    $('#section').html(html);
                    if (single.sinStandard != null && single.sinStandard != '') {
                        $('#section h4 input[value='+single.sinStandard+']').attr("checked", true);
                    }
                } else if (jsonResult.status == 202) { // 判断
                    var yesno = jsonResult.data;
                    var html = `
                        <h3>`+yesno.ynPos+`. `+yesno.ynCaption+`</h3>
                        <h4><input type="radio" value="y" name="answer" onchange="change(this)"/> 对</h4>
                        <h4><input type="radio" value="n" name="answer" onchange="change(this)"/> 错</h4>
                    `;
                    $('#section').empty();
                    $('#section').html(html);
                    if (yesno.ynStandard != null && yesno.ynStandard != '') {
                        $('#section h4 input[value='+yesno.ynStandard+']').attr("checked", true);
                    }
                } else {
                    $("#tip").text(jsonResult.message);
                }
            } else {
                window.location.href = window.contextPath; // js重定向到首页
            }
        },
        error: function(xhr, status, error) {
            window.location.href = window.contextPath; // js重定向到首页
        }
    });
}

// 上一题
function prev() {
    queryProblem(paId, --cur);
}

// 下一题
function next() {
    queryProblem(paId, ++cur);
}

// 答题
function change(that) {
    $.ajax({
        url: window.contextPath + "answer/"+paId+"/"+cur+"/"+that.value,
        type: 'get',
        success: function (jsonResult) {
            var type = typeof jsonResult; // 正常应该是返回对象类型，过期不正常是返回其它数据类型(string)
            if (type == 'boolean') {
                $("#tip").text('答题中...');
            } else {
                window.location.href = window.contextPath; // js重定向到首页
            }
        },
        error: function(xhr, status, error) {
            window.location.href = window.contextPath; // js重定向到首页
        }
    });
}

// 交卷
function hand() {
    $.ajax({
        url: window.contextPath + "hand/"+paId,
        type: 'get',
        success: function (jsonResult) {
            var type = typeof jsonResult; // 正常应该是返回对象类型，过期不正常是返回其它数据类型(string)
            if (type == 'boolean') {
                $("#tip").text('交卷成功...');
            } else {
                window.location.href = window.contextPath; // js重定向到首页
            }
        },
        error: function(xhr, status, error) {
            window.location.href = window.contextPath; // js重定向到首页
        }
    });
}