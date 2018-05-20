$document.ready(function () {
  $.ajax({
    type:"get",
    url:"client/list",
    data:{

    },
    dataType:"json",
    success:function (data) {
    var $Li = $("#container>ul");
    for(var i = 0;i<data.length;i++){
      $Li.append("<li>" + data[i].client_name + "<span> 作者："
        + data[i].client_adress + " &#160;&#160;&#160;&#160; "
        + data[i].client_rtime + " &#160;&#160;&#160;&#160; "
        + "<a href='#'>修改</a> &#160;&#160;&#160;&#160; "
        + "<a href='#' onclick='return clickdel()'>删除</a>"
        + "</span></li>");
    }
    }
  })
})
