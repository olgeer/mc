<!-- BEGIN NOTIFICATION DROPDOWN -->
<li class="dropdown dropdown-extended dropdown-notification" id="header_notification_bar">
    <a class="dropdown-toggle ajaxify" href="message/messct/toMessList?sts=1" data-html="true" module_id="module_192" data-toggle="dropdown"
       data-hover="dropdown" data-close-others="true">
        <i class="icon-bell"></i>
        <span id="mytipscount1" class="badge badge-danger"></span>
		<!-- 有新消息的时候，变更为红色-->
    </a>
    <a class="hidden"><i class="fa fa-envelope-o"></i>未阅</a>
    <ul class="dropdown-menu">
        <li class="external">
            <h3>
                <i class="fa fa-bullhorn"></i>
                <span class="bold">您有<span id="mytipscount2" style="color: red;"></span>条待操作任务.</span>
            </h3>
        </li>
        <li>
            <ul id="mytips" class="dropdown-menu-list scroller" style="height: 136px;" data-handle-color="#637283"></ul>
        </li>
    </ul>
</li>

<script type="text/javascript">
    function loadTopMsgData() {
        $.ajax({
            type: "POST",
            url: "message/messct/messageToDoList?sEcho=1&iDisplayLength=3",
            dataType: 'json',
            success: function (rs) {
                $("#mytipscount1").html(rs.todo_count)
                $("#mytipscount2").html(rs.todo_count)

                if (rs.aaData.length == 0) {
                    $("#mytipscount1").css("display","none")
                }
                
                //填入代办
                var lis = [];
                $.each(rs.aaData, function(index, value) {
                    lis.push('<li><a class="ajaxify" module_id="module_message_view_' + value.id + '" href="message/messct/toView?id=' + value.id + '">' + value.title + '</a></li>');
                });
                $("#mytips").html(lis.join(''));
                
                $("#mytips li").on('click', function() {
                		loadTopMsgData();
                		$('.msg-submit-btn').click();
                });
            }

        });
    }
    //setInterval(loadTopMsgData, 60000);
</script>